package com.stepanova.travelWithUs.entity;

public class OrdersItem extends AbstractEntity<Long>{
	
	private static final long serialVersionUID = 7747357513465108086L;
	private Long idOrders;
	private Tour tour;
	private int count;

	public OrdersItem(Tour tour, int count) {
		super();
		this.tour = tour;
		this.count = count;
	}

	public OrdersItem() {
		super();
	}

	public Long getIdOrders() {
		return idOrders;
	}

	public void setIdOrders(Long idOrders) {
		this.idOrders = idOrders;
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
		return String.format("OrdersItem [id=%s, idOrders=%s, tour=%s, count=%s]", getId(), idOrders, tour, count);
	}
}
