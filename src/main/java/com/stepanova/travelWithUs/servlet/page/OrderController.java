package com.stepanova.travelWithUs.servlet.page;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.stepanova.travelWithUs.entity.Orders;
import com.stepanova.travelWithUs.model.ShoppingCart;
import com.stepanova.travelWithUs.servlet.AbstractController;
import com.stepanova.travelWithUs.util.RoutingUtils;
import com.stepanova.travelWithUs.util.SessionUtils;

@WebServlet("/order")
public class OrderController extends AbstractController {

	private static final long serialVersionUID = 7551806606690918506L;
	private static final String CURRENT_MESSAGE = "CURRENT_MESSAGE";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ShoppingCart shoppingCart = SessionUtils.getCurrentShoppingCart(req);
		long idOrder = getOrdersService().makeOrder(shoppingCart, SessionUtils.getCurrentAccount(req));
		SessionUtils.clearCurrentShoppingCart(req, resp);
		req.getSession().setAttribute(CURRENT_MESSAGE, "Order created successfully. Please wait for our reply.");
		RoutingUtils.redirect("/order?id=" + idOrder, req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String message = (String) req.getSession().getAttribute(CURRENT_MESSAGE);
		req.getSession().removeAttribute(CURRENT_MESSAGE);
		req.setAttribute(CURRENT_MESSAGE, message);
		Orders order = getOrdersService().findOrderById(Long.parseLong(req.getParameter("id")), SessionUtils.getCurrentAccount(req));
		req.setAttribute("order", order);
		RoutingUtils.forwardToPage("order.jsp", req, resp);
	}
}

