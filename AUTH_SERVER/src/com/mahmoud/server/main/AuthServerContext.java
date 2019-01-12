package com.mahmoud.server.main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.mahmoud.server.serviceImpl.AuthenticationServiceImpl;

public class AuthServerContext {
	public static void main(String[] args) {
		try {
			Registry reg = LocateRegistry.createRegistry(2001);

			AuthenticationServiceImpl authenticationService = new AuthenticationServiceImpl();
			reg.rebind("AuthenticationService", authenticationService);
			System.out.println("Authentication Server is Ready");
		} catch (RemoteException RE) {
			System.out.println("Remote Server Error:" + RE.getMessage());

		}
	}
}