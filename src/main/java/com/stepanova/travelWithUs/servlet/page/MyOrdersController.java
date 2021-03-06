package com.stepanova.travelWithUs.servlet.page;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.stepanova.travelWithUs.Constants;
import com.stepanova.travelWithUs.entity.Orders;
import com.stepanova.travelWithUs.model.CurrentAccount;
import com.stepanova.travelWithUs.servlet.AbstractController;
import com.stepanova.travelWithUs.util.RoutingUtils;
import com.stepanova.travelWithUs.util.SessionUtils;

@WebServlet("/my-orders")
public class MyOrdersController extends AbstractController {

	private static final long serialVersionUID = 428718828001207241L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CurrentAccount currentAccount = SessionUtils.getCurrentAccount(req);
		List<Orders> orders = getOrdersService().listMyOrders(currentAccount, 1, Constants.ORDERS_PER_PAGE);
		req.setAttribute("orders", orders);
		int orderCount = getOrdersService().countMyOrders(currentAccount);
		req.setAttribute("pageCount", getPageCount(orderCount, Constants.ORDERS_PER_PAGE));
		RoutingUtils.forwardToPage("my-orders.jsp", req, resp);
	}
}
