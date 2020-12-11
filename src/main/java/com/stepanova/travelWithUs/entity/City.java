package com.stepanova.travelWithUs.entity;

public class City extends AbstractEntity<Integer> {
	
	private static final long serialVersionUID = -3737193319735610430L;
	private String name;
	private int tourCount;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTourCount() {
		return tourCount;
	}
	public void setTourCount(int tourCount) {
		this.tourCount = tourCount;
	}
	
}