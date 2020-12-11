package com.stepanova.travelWithUs.servlet.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.stepanova.travelWithUs.form.TourForm;
import com.stepanova.travelWithUs.model.ShoppingCart;
import com.stepanova.travelWithUs.servlet.AbstractController;
import com.stepanova.travelWithUs.util.RoutingUtils;
import com.stepanova.travelWithUs.util.SessionUtils;

@WebServlet("/ajax/json/tour/add")
public class AddTourController extends AbstractController{
	private static final long serialVersionUID = -3440773010973766553L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TourForm tourForm = createTourForm(req);
		ShoppingCart shoppingCart = SessionUtils.getCurrentShoppingCart(req);
		getOrdersService().addTourToShoppingCart(tourForm, shoppingCart);
		
		JSONObject r = new JSONObject();
		r.put("totalCount", shoppingCart.getTotalCount());
		r.put("totalCost", shoppingCart.getTotalCost());
		RoutingUtils.sendJSON(r, req, resp);
	}

}
