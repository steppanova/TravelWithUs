package com.stepanova.travelWithUs.exception;

import javax.servlet.http.HttpServletResponse;

public class AccessDeniedException extends AbstractApplicationException {

	private static final long serialVersionUID = 78793043064474642L;

	public AccessDeniedException(String s) {
		super(s, HttpServletResponse.SC_FORBIDDEN);
	}
}
