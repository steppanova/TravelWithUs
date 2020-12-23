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
	private int totalCounts = 0;
	private BigDecimal totalCost = BigDecimal.ZERO;

	public void addTour(Tour tour, int counts) {
		validateShoppingCartSize(tour.getId());
		ShoppingCartItem shoppingCartItem = tours.get(tour.getId());
		if (shoppingCartItem == null) {
			validateTourCount(counts);
			shoppingCartItem = new ShoppingCartItem(tour, counts);
			tours.put(tour.getId(), shoppingCartItem);
		} else {
			validateTourCount(counts + shoppingCartItem.getCounts());
			shoppingCartItem.setCounts(shoppingCartItem.getCounts() + counts);
		}
		refreshStatistics();
	}

	public void removeTour(Integer idTour, int counts) {
		ShoppingCartItem shoppingCartItem = tours.get(idTour);
		if (shoppingCartItem != null) {
			if (shoppingCartItem.getCounts() > counts) {
				shoppingCartItem.setCounts(shoppingCartItem.getCounts() - counts);
			} else {
				tours.remove(idTour);
			}
			refreshStatistics();
		}
	}

	public Collection<ShoppingCartItem> getItems() {
		return tours.values();
	}

	public int getTotalCounts() {
		return totalCounts;
	}
	
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	
	private void validateTourCount(int counts) {
		if(counts > Constants.MAX_TOUR_COUNT_PER_SHOPPING_CART){
			throw new ValidationException("Limit for tour count reached: count="+counts);
		}
	}
	
	private void validateShoppingCartSize(int idTour){
		if(tours.size() > Constants.MAX_TOURS_PER_SHOPPING_CART || 
				(tours.size() == Constants.MAX_TOURS_PER_SHOPPING_CART && !tours.containsKey(idTour))) {
			throw new ValidationException("Limit for ShoppingCart size reached: size="+tours.size());
		}
	}

	private void refreshStatistics() {
		totalCounts = 0;
		totalCost = BigDecimal.ZERO;
		for (ShoppingCartItem shoppingCartItem : getItems()) {
			totalCounts += shoppingCartItem.getCounts();
			totalCost = totalCost.add(shoppingCartItem.getTour().getPrice().multiply(BigDecimal.valueOf(shoppingCartItem.getCounts())));
		}
	}

	@Override
	public String toString() {
		return String.format("ShoppingCart [tours=%s, totalCounts=%s, totalCost=%s]", tours, totalCounts, totalCost);
	}



}
