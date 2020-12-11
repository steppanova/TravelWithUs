package com.stepanova.travelWithUs.servlet.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.stepanova.travelWithUs.form.TourForm;
import com.stepanova.travelWithUs.model.ShoppingCart;
import com.stepanova.travelWithUs.servlet.AbstractController;
import com.stepanova.travelWithUs.util.RoutingUtils;
import com.stepanova.travelWithUs.util.SessionUtils;

public abstract class AbstractTourController extends AbstractController {
	private static final long serialVersionUID = 5096979151346608146L;

	@Override
	protected final void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TourForm form = createTourForm(req);
		ShoppingCart shoppingCart = SessionUtils.getCurrentShoppingCart(req);
		processTourForm(form, shoppingCart, req, resp);
		sendResponse(shoppingCart, req, resp);
	}

	protected abstract void processTourForm(TourForm form, ShoppingCart shoppingCart, HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException;

	protected void sendResponse(ShoppingCart shoppingCart, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JSONObject cardStatistics = new JSONObject();
		cardStatistics.put("totalCount", shoppingCart.getTotalCount());
		cardStatistics.put("totalCost", shoppingCart.getTotalCost());
		RoutingUtils.sendJSON(cardStatistics, req, resp);
	}
}
