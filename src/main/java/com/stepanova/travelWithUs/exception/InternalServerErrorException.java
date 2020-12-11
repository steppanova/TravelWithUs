package com.stepanova.travelWithUs.exception;

import javax.servlet.http.HttpServletResponse;

public class InternalServerErrorException extends AbstractApplicationException {

	private static final long serialVersionUID = 1275691220487091019L;

	public InternalServerErrorException(String message) {
		super(message, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}

	public InternalServerErrorException(Throwable cause) {
		super(cause, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}

	public InternalServerErrorException(String message, Throwable cause) {
		super(message, cause, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}


}
