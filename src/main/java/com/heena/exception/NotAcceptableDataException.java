package com.heena.exception;

public class NotAcceptableDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String msg;

	public NotAcceptableDataException(String message) {
		msg=message;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}