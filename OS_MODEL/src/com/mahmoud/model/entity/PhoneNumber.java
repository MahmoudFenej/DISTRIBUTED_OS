package com.mahmoud.model.entity;

import java.io.Serializable;
import java.util.Date;

public class PhoneNumber implements Serializable {

	private static final long serialVersionUID = 1L;

	private int number;
	private double balance;
	private Date expirationDate;

	public PhoneNumber() {
	}

	public PhoneNumber(int number, double balance, Date expirationDate) {
		super();
		this.number = number;
		this.balance = balance;
		this.expirationDate = expirationDate;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public String toString() {
		return "[balance=" + balance + ", expirationDate=" + expirationDate + "]";
	}
	

}
