package com.stepanova.travelWithUs.service;

import java.util.List;

import com.stepanova.travelWithUs.entity.Orders;
import com.stepanova.travelWithUs.form.TourForm;
import com.stepanova.travelWithUs.model.CurrentAccount;
import com.stepanova.travelWithUs.model.ShoppingCart;
import com.stepanova.travelWithUs.model.SocialAccount;

public interface OrdersService {
	
	void addTourToShoppingCart(TourForm tourForm, ShoppingCart shoppingCart);

	void removeTourFromShoppingCart(TourForm form, ShoppingCart shoppingCart);
	
	String serializeShoppingCart(ShoppingCart shoppingCart);
	
	ShoppingCart deserializeShoppingCart(String string);
	
	CurrentAccount authentificate(SocialAccount socialAccount);
	
	long makeOrder(ShoppingCart shoppingCart, CurrentAccount currentAccount);

	Orders findOrderById(long id, CurrentAccount currentAccount);

	List<Orders> listMyOrders(CurrentAccount currentAccount, int page, int limit);

	int countMyOrders(CurrentAccount currentAccount);
}
