package com.stepanova.travelWithUs.servlet.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stepanova.travelWithUs.Constants;
import com.stepanova.travelWithUs.entity.Tour;
import com.stepanova.travelWithUs.form.SearchForm;
import com.stepanova.travelWithUs.servlet.AbstractController;
import com.stepanova.travelWithUs.util.RoutingUtils;

@WebServlet("/ajax/html/more/search")
public class SearchResultsMoreController extends AbstractController {
	
	private static final long serialVersionUID = -4448457311551685335L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SearchForm searchForm = createSearchForm(req);
		List<Tour> tours = getTourService().listToursBySearchForm(searchForm, getPage(req), Constants.MAX_TOURS_PER_HTML_PAGE);
		req.setAttribute("tours", tours);
		RoutingUtils.forwardToFragment("tour-list.jsp", req, resp);
	}
}
