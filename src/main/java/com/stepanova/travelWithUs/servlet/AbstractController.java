package com.stepanova.travelWithUs.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.stepanova.travelWithUs.service.OrdersService;
import com.stepanova.travelWithUs.service.TourService;
import com.stepanova.travelWithUs.service.impl.ServiceManager;

public abstract class AbstractController extends HttpServlet {

	private static final long serialVersionUID = -6555095942968735383L;
	private TourService tourService;
	private OrdersService ordersService;

	@Override
	public final void init() throws ServletException {
		tourService = ServiceManager.getInstance(getServletContext()).getProductService();
		ordersService = ServiceManager.getInstance(getServletContext()).getOrderService();
	}

	public final TourService getProductService() {
		return tourService;
	}

	public final OrdersService getOrderService() {
		return ordersService;
	}
}
