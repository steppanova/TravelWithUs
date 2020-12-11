package com.stepanova.travelWithUs.exception;

import javax.servlet.http.HttpServletResponse;

public class ResourceNotFoundException extends AbstractApplicationException {
	
	private static final long serialVersionUID = 3004525529411768870L;

	public ResourceNotFoundException(String s) {
		super(s, HttpServletResponse.SC_NOT_FOUND);
	}
}
