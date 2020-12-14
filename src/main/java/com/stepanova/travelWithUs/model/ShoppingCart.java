package com.stepanova.travelWithUs.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import com.stepanova.travelWithUs.Constants;
import com.stepanova.travelWithUs.entity.Tour;
import com.stepanova.travelWithUs.exception.ValidationException;

public class ShoppingCart implements Serializable {
	
	private static final long serialVersionUID = -7182154007024202473L;
	private Map<Integer, ShoppingCartItem> tours = new LinkedHashMap<>();
	private int totalCount = 0;
	private BigDecimal totalCost = BigDecimal.ZERO;

	public void addTour(Tour tour, int count) {
		validateShoppingCartSize(tour.getId());
		ShoppingCartItem shoppingCartItem = tours.get(tour.getId());
		if (shoppingCartItem == null) {
			validateTourCount(count);
			shoppingCartItem = new ShoppingCartItem(tour, count);
			tours.put(tour.getId(), shoppingCartItem);
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
	
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	
	private void validateTourCount(int count) {
		if(count > Constants.MAX_TOUR_COUNT_PER_SHOPPING_CART){
			throw new ValidationException("Limit for tour count reached: count="+count);
		}
	}
	
	private void validateShoppingCartSize(int idTour){
		if(tours.size() > Constants.MAX_TOURS_PER_SHOPPING_CART || 
				(tours.size() == Constants.MAX_TOURS_PER_SHOPPING_CART && !tours.containsKey(idTour))) {
			throw new ValidationException("Limit for ShoppingCart size reached: size="+tours.size());
		}
	}

	private void refreshStatistics() {
		totalCount = 0;
		totalCost = BigDecimal.ZERO;
		for (ShoppingCartItem shoppingCartItem : getItems()) {
			totalCount += shoppingCartItem.getCount();
			totalCost = totalCost.add(shoppingCartItem.getTour().getPrice().multiply(BigDecimal.valueOf(shoppingCartItem.getCount())));
		}
	}

	@Override
	public String toString() {
		return String.format("ShoppingCart [tours=%s, totalCount=%s, totalCost=%s]", tours, totalCount, totalCost);
	}


}
