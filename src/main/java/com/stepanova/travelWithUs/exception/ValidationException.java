package com.stepanova.travelWithUs.exception;

public class ValidationException extends IllegalArgumentException {

	private static final long serialVersionUID = -7185393923997687128L;

	public ValidationException(String s) {
		super(s);
	}
}
