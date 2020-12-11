package com.stepanova.travelWithUs.exception;

public class InternalServerErrorException extends RuntimeException {

	private static final long serialVersionUID = 1275691220487091019L;

	public InternalServerErrorException(String message) {
		super(message);
	}

	public InternalServerErrorException(Throwable cause) {
		super(cause);
	}

	public InternalServerErrorException(String message, Throwable cause) {
		super(message, cause);
	}

}
