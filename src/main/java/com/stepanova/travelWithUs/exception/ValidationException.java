package com.stepanova.travelWithUs.exception;

import javax.servlet.http.HttpServletResponse;

public class ValidationException extends AbstractApplicationException {
	
	private static final long serialVersionUID = 7938953764482318278L;

	public ValidationException(String s) {
		super(s, HttpServletResponse.SC_BAD_REQUEST);
	}
}