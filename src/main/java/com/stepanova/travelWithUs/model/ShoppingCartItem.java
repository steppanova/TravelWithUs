package com.stepanova.travelWithUs.model;

import java.io.Serializable;

public class ShoppingCartItem implements Serializable {

	private static final long serialVersionUID = 4796570150616495535L;
	private int idTour;
	private int count;

	public ShoppingCartItem() {
		super();
	}

	public ShoppingCartItem(int idTour, int count) {
		super();
		this.idTour = idTour;
		this.count = count;
	}

	public int getIdTour() {
		return idTour;
	}

	public void setIdTour(int idTour) {
		this.idTour = idTour;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "ShoppingCartItem [idTour=" + idTour + ", count=" + count + "]";
	}
}
