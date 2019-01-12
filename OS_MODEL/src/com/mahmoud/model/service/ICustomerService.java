package com.mahmoud.model.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.mahmoud.model.entity.MessageTO;
import com.mahmoud.model.entity.PhoneNumber;

public interface ICustomerService extends Remote{
	
	public String sendNumberDetails(PhoneNumber phoneNumber) throws RemoteException;

	public MessageTO sendServiceMessage(String code, String message, String currentAction, String authOutput) throws RemoteException;

	public PhoneNumber getPhoneNumber(int phoneNumber) throws RemoteException;

	public String getServices() throws RemoteException;




}
