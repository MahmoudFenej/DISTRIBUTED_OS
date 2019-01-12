package com.mahmoud.client.main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import com.mahmoud.client.consoleView.JavaConsole;
import com.mahmoud.model.entity.MessageTO;
import com.mahmoud.model.service.AuthenticationService;
import com.mahmoud.model.service.ICustomerService;

public class ClientWithConsole {

	public static void main(String[] args) {

		new JavaConsole();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		try {

			Registry authRegistry = LocateRegistry.getRegistry("127.0.0.1", 2001);
			Registry custRegistry = LocateRegistry.getRegistry("127.0.0.1", 2002);

			AuthenticationService authenticationService = (AuthenticationService) (authRegistry
					.lookup("AuthenticationService"));

			ICustomerService customerService = (ICustomerService) (custRegistry.lookup("CustomerService"));

			String replyMessage = "";
			String outputMessage = "";
			String messageFormat = "";
			String currentAction = "";
			String input = "";
			char annotherOpResponse = 'y';
			boolean needToReply = false;
			while (annotherOpResponse != 'n') {
				System.out.println("Please Enter Your Number:");

				String phoneNumber = scanner.nextLine();
				MessageTO authCode = authenticationService.getAuthCode("Log:" + phoneNumber + ":*11#");

				needToReply = authCode.isNeedToReply();
				outputMessage = authCode.getOutputMessage();
				replyMessage = authCode.getReplyMessage();
				messageFormat = authCode.getMessageFormat();
				currentAction = authCode.getCurrentAction();

				while (needToReply) {

					if (!currentAction.equals("Sending Services"))
						input = scanner.nextLine();

					MessageTO resultMessage = customerService.sendServiceMessage(input, messageFormat, currentAction,
							authCode.getOutputMessage());

					needToReply = resultMessage.isNeedToReply();
					outputMessage = resultMessage.getOutputMessage();
					replyMessage = resultMessage.getReplyMessage();
					messageFormat = resultMessage.getMessageFormat();
					currentAction = resultMessage.getCurrentAction();

					System.out.println(outputMessage);
					System.out.println(replyMessage);

				}
				System.out.println("Do You want to do another operation [y|n]?");
				annotherOpResponse = scanner.nextLine().charAt(0);

			}
			System.exit(0);
		} catch (Exception ex) {
			System.out.println("Remote Server Error:" + ex.getMessage());
		}

	}
}
