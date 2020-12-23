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

	private static final long serialVersionUID = -7903304581744002151L;

	@Override
	protected final void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TourForm form = createTourForm(req);
		ShoppingCart shoppingCart = getCurrentShoppingCart(req);
		processTourForm(form, shoppingCart, req, resp);
		if(!SessionUtils.isCurrentShoppingCartCreated(req)) {
			SessionUtils.setCurrentShoppingCart(req, shoppingCart);
		}
		sendResponse(shoppingCart, req, resp);
	}
	
	private ShoppingCart getCurrentShoppingCart(HttpServletRequest req) {
		ShoppingCart shoppingCart = SessionUtils.getCurrentShoppingCart(req);
		if(shoppingCart == null) {
			shoppingCart = new ShoppingCart();
		}
		return shoppingCart;
	}

	protected abstract void processTourForm(TourForm form, ShoppingCart shoppingCart, HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException;

	protected void sendResponse(ShoppingCart shoppingCart, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JSONObject cardStatistics = new JSONObject();
		cardStatistics.put("totalCount", shoppingCart.getTotalCounts());
		cardStatistics.put("totalCost", shoppingCart.getTotalCost());
		RoutingUtils.sendJSON(cardStatistics, req, resp);
	}
}
