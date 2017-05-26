package com.heena.entity;

public class ErrorMessage {
	
	private int code;
    private String message;

    public ErrorMessage() {
    }

    public ErrorMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
