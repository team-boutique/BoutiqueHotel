package com.jdbc.exception;

public class InvalidBookingException extends Exception{
	public InvalidBookingException() {
		this("유효한 예약이 아닙니다.");
	}

	public InvalidBookingException(String message) {
		super(message);
	}
	
}
