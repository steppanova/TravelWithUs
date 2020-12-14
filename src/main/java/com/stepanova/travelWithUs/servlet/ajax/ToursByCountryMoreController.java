package com.stepanova.travelWithUs.servlet.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stepanova.travelWithUs.Constants;
import com.stepanova.travelWithUs.entity.Tour;
import com.stepanova.travelWithUs.servlet.AbstractController;
import com.stepanova.travelWithUs.util.RoutingUtils;

@WebServlet("/ajax/html/more/tours/*")
public class ToursByCountryMoreController extends AbstractController {
	
	private static final long serialVersionUID = 8118020719471773331L;
	private static final int SUBSTRING_INDEX = "/ajax/html/more/tours".length();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String countryUrl = req.getRequestURI().substring(SUBSTRING_INDEX);
		List<Tour> tours = getTourService().listToursByCountry(countryUrl, getPage(req), Constants.MAX_TOURS_PER_HTML_PAGE);
		req.setAttribute("tours", tours);
		RoutingUtils.forwardToFragment("tour-list.jsp", req, resp);
	}
}