package com.mahmoud.server.serviceImpl;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.stream.Collectors;

import com.mahmoud.model.entity.MessageFormat;
import com.mahmoud.model.entity.MessageTO;
import com.mahmoud.model.entity.PhoneNumber;
import com.mahmoud.model.service.AuthenticationService;
import com.mahmoud.model.service.ICustomerService;
import com.mahmoud.server.data.CustDataProvider;

public class CustomerServiceImpl extends UnicastRemoteObject implements ICustomerService {

	private static final long serialVersionUID = 1L;

	public CustomerServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public String sendNumberDetails(PhoneNumber phoneNumber) throws RemoteException {
		if (phoneNumber == null)
			return "Number not found";
		Random random = new Random();
		char randomChar = (char) (random.nextInt(26) + 'A');
		CustDataProvider.getInstance().saveAuthCharacter(randomChar, phoneNumber);
		String message = "yes:" + phoneNumber.getNumber() + ":" + randomChar;
		return message;
	}

	public String getServices() throws RemoteException {
		return CustDataProvider.getInstance().getServices().stream().map(e -> e.toString())
				.collect(Collectors.joining("\n"));
	}

	@Override
	public MessageTO sendServiceMessage(String code, String message, String currentAction, String authOutput)
			throws RemoteException {
		String[] authOutputArr = authOutput.split(":");
		int phoneNumber = Integer.parseInt(authOutputArr[1]);
		char key = authOutputArr[2].charAt(0);
		if (currentAction.equals("Sending Services")) {
			boolean testNumberAuth = CustDataProvider.getInstance().testNumberAuth(phoneNumber, key);
			if (!testNumberAuth)
				throw new RemoteException("Invalid login key");

			return new MessageTO("Enter Service Number", getServices(), null, "getting services");
		}

		if (currentAction.equals("getting To Number")) {
			int toNumber = Integer.parseInt(code);
			PhoneNumber toPhoneNumber = getPhoneNumber(toNumber);
			if (toPhoneNumber == null) {
				throw new RemoteException("number inactive");
			}

			String format = message.replaceFirst("%d", "" + code);
			return new MessageTO("Enter Amount to Transfer", toNumber + " is Valid", format, "getting amount balance");

		}
		if (currentAction.equals("getting amount balance")) {
			String format = message.replaceFirst("%d", "" + code);
			return new MessageTO("", transferCredit(format), format, "");
		}

		if (code.equals("1")) {
			return new MessageTO("", getPhoneNumber(phoneNumber).toString(), null, "");
		}

		if (code.equals("2")) {
			String format = MessageFormat.TRANSFER_CREDIT.getFormat().replaceFirst("%d", "" + phoneNumber);
			return new MessageTO("Enter phone number to transfer credits to:", "", format, "getting To Number");

		}

		return new MessageTO();
	}

	@Override
	public PhoneNumber getPhoneNumber(int phoneNumber) throws RemoteException {
		Registry myreg = LocateRegistry.getRegistry("127.0.0.1", 2001);
		try {
			AuthenticationService authenticationService = (AuthenticationService) (myreg
					.lookup("AuthenticationService"));

			return authenticationService.findPhoneNumber(phoneNumber);
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String transferCredit(String message) throws RemoteException {
		String[] messageArr = message.split(":");
		String fromNumber = messageArr[1];
		int toNumber = Integer.parseInt(messageArr[2]);
		double amount = Double.parseDouble(messageArr[3]);
		Registry myreg = LocateRegistry.getRegistry("127.0.0.1", 2001);
		try {
			AuthenticationService authenticationService = (AuthenticationService) (myreg
					.lookup("AuthenticationService"));

			PhoneNumber toPhoneNumber = authenticationService.findPhoneNumber(toNumber);
			if (toPhoneNumber == null) {
				throw new RemoteException("number inactive");
			}

			if (amount > toPhoneNumber.getBalance()) {
				throw new RemoteException("not enough credit");
			}
			return authenticationService.proccedTransfer(fromNumber + ":" + toNumber + ":" + amount);

		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		return null;
	}

}
