package com.stepanova.travelWithUs.servlet.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.stepanova.travelWithUs.form.TourForm;
import com.stepanova.travelWithUs.model.ShoppingCart;
import com.stepanova.travelWithUs.util.SessionUtils;

@WebServlet("/ajax/json/tour/add")
public class AddTourController extends AbstractTourController{
	
	private static final long serialVersionUID = 4967078900132751364L;

	@Override
	protected void processTourForm(TourForm form, ShoppingCart shoppingCart, HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException {
		getOrdersService().addTourToShoppingCart(form, shoppingCart);
		String cookieValue = getOrdersService().serializeShoppingCart(shoppingCart);
		SessionUtils.updateCurrentShoppingCartCookie(cookieValue, resp);
	}
}
