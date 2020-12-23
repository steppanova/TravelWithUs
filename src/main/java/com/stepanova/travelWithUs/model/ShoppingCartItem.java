package com.stepanova.travelWithUs.model;

import java.io.Serializable;

import com.stepanova.travelWithUs.entity.Tour;

public class ShoppingCartItem implements Serializable {

	private static final long serialVersionUID = 4796570150616495535L;
	private Tour tour;
	private int counts;
	public ShoppingCartItem() {
		super();
	}
	public ShoppingCartItem(Tour tour, int counts) {
		super();
		this.tour = tour;
		this.counts = counts;
	}
	public Tour getTour() {
		return tour;
	}
	public void setTour(Tour tour) {
		this.tour = tour;
	}
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	@Override
	public String toString() {
		return String.format("ShoppingCartItem [tour=%s, counts=%s]", tour, counts);
	}
}
