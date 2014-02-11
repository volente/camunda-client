package com.rocksbook.camunda.client.api.exception;

import lombok.Getter;

@Getter
public class APIException extends RuntimeException {

	private static final long serialVersionUID = -6007988677778581008L;

	private String code;

	private int statusCode;

	private String reason;
	
	public APIException(String code, String msg, int statusCode, String reasen) {
		super(msg);
		this.code = code;
	}
	
	public APIException(String code, String msg, Throwable cause) {
		super(msg);
		this.code = code;
	}
}
