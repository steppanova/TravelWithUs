package com.stepanova.travelWithUs.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.stepanova.travelWithUs.Constants;
import com.stepanova.travelWithUs.exception.ValidationException;

public class ShoppingCart implements Serializable {
	private static final long serialVersionUID = -4018455346186544927L;
	
	private Map<Integer, ShoppingCartItem> tours = new HashMap<>();
	private int totalCount = 0;

	public void addTour(int idTour, int count) {
		validateShoppingCartSize(idTour);
		ShoppingCartItem shoppingCartItem = tours.get(idTour);
		if (shoppingCartItem == null) {
			validateTourCount(count);
			shoppingCartItem = new ShoppingCartItem(idTour, count);
			tours.put(idTour, shoppingCartItem);
		} else {
			validateTourCount(count + shoppingCartItem.getCount());
			shoppingCartItem.setCount(shoppingCartItem.getCount() + count);
		}
		refreshStatistics();
	}

	public void removeTour(Integer idTour, int count) {
		ShoppingCartItem shoppingCartItem = tours.get(idTour);
		if (shoppingCartItem != null) {
			if (shoppingCartItem.getCount() > count) {
				shoppingCartItem.setCount(shoppingCartItem.getCount() - count);
			} else {
				tours.remove(idTour);
			}
			refreshStatistics();
		}
	}

	public Collection<ShoppingCartItem> getItems() {
		return tours.values();
	}

	public int getTotalCount() {
		return totalCount;
	}

	private void validateTourCount(int count) {
		if (count > Constants.MAX_TOUR_COUNT_PER_SHOPPING_CART) {
			throw new ValidationException("Limit for product count reached: count=" + count);
		}
	}

	private void validateShoppingCartSize(int idTour) {
		if (tours.size() > Constants.MAX_TOURS_PER_SHOPPING_CART
				|| (tours.size() == Constants.MAX_TOURS_PER_SHOPPING_CART && !tours.containsKey(idTour))) {
			throw new ValidationException("Limit for ShoppingCart size reached: size=" + tours.size());
		}
	}

	private void refreshStatistics() {
		totalCount = 0;
		for (ShoppingCartItem shoppingCartItem : getItems()) {
			totalCount += shoppingCartItem.getCount();
		}
	}

	@Override
	public String toString() {
		return String.format("ShoppingCart [tours=%s, totalCount=%s]", tours, totalCount);
	}
}
