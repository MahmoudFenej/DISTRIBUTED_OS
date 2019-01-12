package com.mahmoud.model.entity;

public enum MessageFormat {
	AUTH_FORMAT("Log:%d:*11#"), SEND_OPTION("%d:key:code"), TRANSFER_CREDIT("transfer:%d:%d:%d");

	private final String format;

	MessageFormat(String format) {
		this.format = format;
	}

	public String getFormat() {
		return format;
	}
}
