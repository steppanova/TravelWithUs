package com.stepanova.travelWithUs.service;

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
}
