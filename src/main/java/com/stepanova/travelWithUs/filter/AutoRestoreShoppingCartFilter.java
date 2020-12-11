package com.stepanova.travelWithUs.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stepanova.travelWithUs.form.TourForm;
import com.stepanova.travelWithUs.model.ShoppingCart;
import com.stepanova.travelWithUs.service.OrdersService;
import com.stepanova.travelWithUs.service.impl.ServiceManager;
import com.stepanova.travelWithUs.util.SessionUtils;

@WebFilter(filterName="AutoRestoreShoppingCartFilter")
public class AutoRestoreShoppingCartFilter extends AbstractFilter {
	
	private static final String SHOPPING_CARD_DESERIALIZATION_DONE = "SHOPPING_CARD_DESERIALIZATION_DONE";

	private OrdersService ordersService;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ordersService = ServiceManager.getInstance(filterConfig.getServletContext()).getOrdersService();
	}
	
	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
		if(req.getSession().getAttribute(SHOPPING_CARD_DESERIALIZATION_DONE) == null){
			if(!SessionUtils.isCurrentShoppingCartCreated(req)) {
				Cookie cookie = SessionUtils.findShoppingCartCookie(req);
				if(cookie != null) {
					ShoppingCart shoppingCart = ordersService.deserializeShoppingCart(cookie.getValue());
					SessionUtils.setCurrentShoppingCart(req, shoppingCart);
				}
			}
			req.getSession().setAttribute(SHOPPING_CARD_DESERIALIZATION_DONE, Boolean.TRUE);
		}
		
		chain.doFilter(req, resp);
	}

}
