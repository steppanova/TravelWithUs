package com.stepanova.travelWithUs.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stepanova.travelWithUs.form.SearchForm;
import com.stepanova.travelWithUs.form.TourForm;
import com.stepanova.travelWithUs.service.OrdersService;
import com.stepanova.travelWithUs.service.SocialService;
import com.stepanova.travelWithUs.service.TourService;
import com.stepanova.travelWithUs.service.impl.ServiceManager;

public abstract class AbstractController extends HttpServlet {

	private static final long serialVersionUID = -6555095942968735383L;
	protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private TourService tourService;
	private OrdersService ordersService;
	private SocialService socialService;

	@Override
	public final void init() throws ServletException {
		tourService = ServiceManager.getInstance(getServletContext()).getTourService();
		ordersService =  ServiceManager.getInstance(getServletContext()).getOrdersService();
		socialService = ServiceManager.getInstance(getServletContext()).getSocialService();
	}

	public final TourService getTourService() {
		return tourService;
	}

	public final OrdersService getOrdersService() {
		return ordersService;
	}
	
	public final SocialService getSocialService() {
		return socialService;
	}
	
	public final int getPageCount(int totalCount, int itemsPerPage) {
		int res = totalCount / itemsPerPage;
		if(res * itemsPerPage != totalCount) {
			res++;
		}
		return res;
	}
	
	public final int getPage(HttpServletRequest request) {
		try {
			return Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			return 1;
		}
	}
	
	public final SearchForm createSearchForm(HttpServletRequest request) {
		return new SearchForm(
				request.getParameter("query"), 
				request.getParameterValues("country"), 
				request.getParameterValues("city"));
	}
	
	public final TourForm createTourForm(HttpServletRequest request) {
		return new TourForm(
				Integer.parseInt(request.getParameter("idTour")),
				Integer.parseInt(request.getParameter("count")));
	}
}
