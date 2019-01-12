package com.mahmoud.server.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mahmoud.model.entity.PhoneNumber;
import com.mahmoud.model.entity.Service;

public class CustDataProvider {

	private Map<Integer, Character> phoneAuthMap = new HashMap<>();
	
	private List<Service> servicesList;

	private static CustDataProvider instance;

	public static CustDataProvider getInstance() {
		if (instance == null)
			instance = new CustDataProvider();

		return instance;
	}

	public void saveAuthCharacter(char character, PhoneNumber number) {
		phoneAuthMap.put(number.getNumber(), character);
	}
	
	public boolean testNumberAuth(int number, char key) {
		return phoneAuthMap.get(number) == key;
	}

	public List<Service> getServices() {
		if (servicesList == null)
			initPhoneNumber();
		return servicesList;

	}

	private void initPhoneNumber() {
		servicesList = new ArrayList<>();

		servicesList.add(new Service("Check Balance", "--1--"));
		servicesList.add(new Service("Transfer Data", "--2--"));

	}
	

}
