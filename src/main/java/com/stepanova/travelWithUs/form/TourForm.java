package com.stepanova.travelWithUs.form;

public class TourForm {
	private Integer idTour;
	private Integer count;
	
	public TourForm() {
		super();
	}
	public TourForm(Integer idTour, Integer count) {
		super();
		this.idTour = idTour;
		this.count = count;
	}
	public Integer getIdTour() {
		return idTour;
	}
	public void setIdTour(Integer idTour) {
		this.idTour = idTour;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
