package com.jdbc.exception;

public class AgeLimitedException extends Exception{
	public AgeLimitedException() {
		this("미성년자는 가입이 불가합니다.");
	}

	public AgeLimitedException(String message) {
		super(message);
	}
	
}
