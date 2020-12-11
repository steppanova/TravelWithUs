package com.stepanova.travelWithUs.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stepanova.travelWithUs.Constants;
import com.stepanova.travelWithUs.service.TourService;
import com.stepanova.travelWithUs.service.impl.ServiceManager;

public class CountryCityFilter extends AbstractFilter {

	private TourService tourService;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		tourService = ServiceManager.getInstance(filterConfig.getServletContext()).getTourService();
	}
	
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setAttribute(Constants.COUNTRY_LIST, tourService.listAllCountries());
		request.setAttribute(Constants.CITY_LIST, tourService.listAllCities());
		chain.doFilter(request, response);
	}
}
