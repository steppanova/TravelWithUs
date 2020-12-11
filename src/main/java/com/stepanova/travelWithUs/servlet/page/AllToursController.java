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

@WebServlet("/tours")
public class AllToursController extends AbstractController {

	private static final long serialVersionUID = -1143866584161262775L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Tour> tours = getTourService().listAllTours(1, Constants.MAX_TOURS_PER_HTML_PAGE);
		req.setAttribute("tours", tours);
		int totalCount = getTourService().countAllTours();
		req.setAttribute("pageCount", getPageCount(totalCount, Constants.MAX_TOURS_PER_HTML_PAGE));
		RoutingUtils.forwardToPage("tours.jsp", req, resp);
	}
}