package com.mahmoud.server.serviceImpl;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.mahmoud.model.entity.MessageFormat;
import com.mahmoud.model.entity.MessageTO;
import com.mahmoud.model.entity.PhoneNumber;
import com.mahmoud.model.service.AuthenticationService;
import com.mahmoud.model.service.ICustomerService;
import com.mahmoud.server.data.DataProvider;

public class AuthenticationServiceImpl extends UnicastRemoteObject implements AuthenticationService{

	private static final long serialVersionUID = 1L;

	public AuthenticationServiceImpl() throws RemoteException {
		super();
	}

	public MessageTO getAuthCode(String message) throws RemoteException {
		String[] messageArr = message.split(":");		
		int phoneNumber = Integer.parseInt(messageArr[1]);
		PhoneNumber phoneNumberByID = findPhoneNumber(phoneNumber);
		if (phoneNumberByID == null) {
			throw new RemoteException("Phone Number is inactive");
		}
		Registry myreg = LocateRegistry.getRegistry("127.0.0.1", 2002);
		ICustomerService customerService = null;
		String numberMessage= "";
		try {
			customerService = (ICustomerService) (myreg.lookup("CustomerService"));
			numberMessage = customerService.sendNumberDetails(phoneNumberByID);
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	
		return new MessageTO(" ", numberMessage, MessageFormat.AUTH_FORMAT.getFormat(), "Sending Services");
	}
	
	@Override
	public PhoneNumber findPhoneNumber(int phoneNumber) {
		PhoneNumber phoneNumberByID = DataProvider.findPhoneNumberByID(phoneNumber);
		return phoneNumberByID;
	}

	@Override
	public String proccedTransfer(String message) {
		String[] messageArr = message.split(":");
		double amount = Double.parseDouble(messageArr[2]);
		PhoneNumber fromNumber = findPhoneNumber(Integer.parseInt(messageArr[0]));
		PhoneNumber toNumber = findPhoneNumber(Integer.parseInt(messageArr[1]));
		fromNumber.setBalance(fromNumber.getBalance() - amount);
		toNumber.setBalance(toNumber.getBalance() + amount);
		DataProvider.savePhoneNumber(fromNumber);
		DataProvider.savePhoneNumber(toNumber);
		return "Transfer Successfully";
	}

}
