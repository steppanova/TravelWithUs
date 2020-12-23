package com.stepanova.travelWithUs.entity;

public class OrdersItem extends AbstractEntity<Long>{
	
	private static final long serialVersionUID = 7747357513465108086L;
	private Long idOrders;
	private Tour tour;
	private int counts;

	public OrdersItem(Tour tour, int counts) {
		super();
		this.tour = tour;
		this.counts = counts;
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

	public int getCounts() {
		return counts;
	}

	public void setCounts(int counts) {
		this.counts = counts;
	}

	@Override
	public String toString() {
		return String.format("OrdersItem [id=%s, idOrders=%s, tour=%s, counts=%s]", getId(), idOrders, tour, counts);
	}
}
