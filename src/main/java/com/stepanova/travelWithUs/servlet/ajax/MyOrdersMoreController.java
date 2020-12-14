package com.stepanova.travelWithUs.servlet.ajax;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.stepanova.travelWithUs.Constants;
import com.stepanova.travelWithUs.entity.Orders;
import com.stepanova.travelWithUs.servlet.AbstractController;
import com.stepanova.travelWithUs.util.RoutingUtils;
import com.stepanova.travelWithUs.util.SessionUtils;

@WebServlet("/ajax/html/more/my-orders")
public class MyOrdersMoreController extends AbstractController {
	private static final long serialVersionUID = -2651974520717714088L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Orders> orders = getOrdersService().listMyOrders(SessionUtils.getCurrentAccount(req), getPage(req), Constants.ORDERS_PER_PAGE);
		req.setAttribute("orders", orders);
		RoutingUtils.forwardToFragment("my-orders-tbody.jsp", req, resp);
	}
}