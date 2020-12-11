package com.stepanova.travelWithUs.model;

import java.io.Serializable;

import com.stepanova.travelWithUs.entity.Tour;

public class ShoppingCartItem implements Serializable {

	private static final long serialVersionUID = 4796570150616495535L;
	private Tour tour;
	private int count;
	public ShoppingCartItem() {
		super();
	}
	public ShoppingCartItem(Tour tour, int count) {
		super();
		this.tour = tour;
		this.count = count;
	}
	public Tour getTour() {
		return tour;
	}
	public void setTour(Tour tour) {
		this.tour = tour;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return String.format("ShoppingCartItem [tour=%s, count=%s]", tour, count);
	}
}
