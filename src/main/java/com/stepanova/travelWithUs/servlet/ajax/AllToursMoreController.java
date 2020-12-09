package com.stepanova.travelWithUs.servlet.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stepanova.travelWithUs.servlet.AbstractController;
import com.stepanova.travelWithUs.util.RoutingUtils;

@WebServlet("/ajax/html/more/tours")
public class AllToursMoreController extends AbstractController {
	private static final long serialVersionUID = -4385792519039493271L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RoutingUtils.forwardToFragment("tour-list.jsp", req, resp);
	}
}
