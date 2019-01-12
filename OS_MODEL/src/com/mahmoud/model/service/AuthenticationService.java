package com.mahmoud.model.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.mahmoud.model.entity.MessageTO;
import com.mahmoud.model.entity.PhoneNumber;

public interface AuthenticationService extends Remote{

	public MessageTO getAuthCode(String message) throws RemoteException;
	
	public PhoneNumber findPhoneNumber(int phoneNumber) throws RemoteException;
	
	public String proccedTransfer(String message) throws RemoteException;


}
