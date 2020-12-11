package com.stepanova.travelWithUs.servlet.page;

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

@WebServlet("/search")
public class SearchController extends AbstractController {

	private static final long serialVersionUID = 1672604851035271029L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SearchForm searchForm = createSearchForm(req);
		List<Tour> tours = getTourService().listToursBySearchForm(searchForm, 1, Constants.MAX_TOURS_PER_HTML_PAGE);
		req.setAttribute("tours", tours);
		int totalCount = getTourService().countToursBySearchForm(searchForm);
		req.setAttribute("pageCount", getPageCount(totalCount, Constants.MAX_TOURS_PER_HTML_PAGE));
		req.setAttribute("tourCount", totalCount);
		req.setAttribute("searchForm", searchForm);
		RoutingUtils.forwardToPage("search-result.jsp", req, resp);
	}
}
