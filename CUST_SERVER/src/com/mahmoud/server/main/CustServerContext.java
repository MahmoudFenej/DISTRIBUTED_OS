package com.mahmoud.server.main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.mahmoud.server.serviceImpl.CustomerServiceImpl;

public class CustServerContext {
	public static void main(String[] args) {
		try {

			Registry reg = LocateRegistry.createRegistry(2002);

			CustomerServiceImpl customerService = new CustomerServiceImpl();
			reg.rebind("CustomerService", customerService);

			System.out.println("CustomerService Server is Ready");
		} catch (RemoteException RE) {
			System.out.println("Remote Server Error:" + RE.getMessage());

		}
	}
}