package com.stepanova.travelWithUs.servlet.page;

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

@WebServlet("/tours/*")
public class ToursByCountryController extends AbstractController {
	private static final long serialVersionUID = 7628481811908537001L;
	private static final int SUBSTRING_INDEX = "/tours".length();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String countryUrl = req.getRequestURI().substring(SUBSTRING_INDEX);
		List<Tour> tours = getTourService().listToursByCountry(countryUrl, 1, Constants.MAX_TOURS_PER_HTML_PAGE);
		req.setAttribute("tours", tours);
		int totalCount = getTourService().countToursByCountry(countryUrl);
		req.setAttribute("pageCount", getPageCount(totalCount, Constants.MAX_TOURS_PER_HTML_PAGE));
		req.setAttribute("selectedCountryUrl", countryUrl);
		RoutingUtils.forwardToPage("tours.jsp", req, resp);
	}
}
