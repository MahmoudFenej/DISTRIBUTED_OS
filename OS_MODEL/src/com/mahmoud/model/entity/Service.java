package com.mahmoud.model.entity;

import java.io.Serializable;

public class Service implements Serializable {

	private static final long serialVersionUID = 1L;

	private String serviceName;
	private String code;

	public Service() {
		// TODO Auto-generated constructor stub
	}
	
	public Service(String serviceName, String code) {
		super();
		this.serviceName = serviceName;
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@Override
	public String toString() {
		return "[serviceName=" + serviceName + ", code=" + code + "]";
	}
	
	

}
