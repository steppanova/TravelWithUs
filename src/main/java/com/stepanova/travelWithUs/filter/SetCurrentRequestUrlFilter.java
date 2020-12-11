package com.stepanova.travelWithUs.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stepanova.travelWithUs.Constants;
import com.stepanova.travelWithUs.util.WebUtils;

@WebFilter(filterName="SetCurrentRequestUrlFilter")
public class SetCurrentRequestUrlFilter extends AbstractFilter {
	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
		req.setAttribute(Constants.CURRENT_REQUEST_URL, WebUtils.getCurrentRequestUrl(req));
		chain.doFilter(req, resp);
	}
}
