package com.mahmoud.server.data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.mahmoud.model.entity.PhoneNumber;

public class DataProvider {

	private static Map<Integer, PhoneNumber> phoneNumbers;

	public static Map<Integer, PhoneNumber> getPhoneNumbers() {
		if (phoneNumbers == null)
			initPhoneNumber();

		return phoneNumbers;

	}

	@SuppressWarnings("deprecation")
	private static void initPhoneNumber() {
		phoneNumbers = new HashMap<>();

		phoneNumbers.put(76131027, new PhoneNumber(76131027, 20, new Date(2019, 1, 1)));
		phoneNumbers.put(76131028, new PhoneNumber(76131028, 30, new Date(2019, 1, 1)));
		phoneNumbers.put(76131029, new PhoneNumber(76131029, 40, new Date(2019, 1, 1)));

	}
	
	public static PhoneNumber findPhoneNumberByID(int number) {
		return getPhoneNumbers().get(number);
	}
	
	public static void savePhoneNumber(PhoneNumber phoneNumber) {
		phoneNumbers.put(phoneNumber.getNumber(), phoneNumber);
	}

}
