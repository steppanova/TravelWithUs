package com.stepanova.travelWithUs.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Orders extends AbstractEntity<Long>{

	private static final long serialVersionUID = 4665038991873718435L;
	private Integer idAccount;
	private List<OrdersItem> items;
	private Timestamp created;

	public Orders() {

	}

	public Integer getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(Integer idAccount) {
		this.idAccount = idAccount;
	}

	public List<OrdersItem> getItems() {
		return items;
	}

	public void setItems(List<OrdersItem> items) {
		this.items = items;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public BigDecimal getTotalCost() {
		BigDecimal cost = BigDecimal.ZERO;
		if (items != null) {
			for (OrdersItem item : items) {
				cost = cost.add(item.getTour().getPrice().multiply(BigDecimal.valueOf(item.getCount())));
			}
		}
		return cost;
	}

	@Override
	public String toString() {
		return String.format("Orders [id=%s, idAccount=%s, items=%s, created=%s]", getId(), idAccount, items, created);
	}
}
