package com.stepanova.travelWithUs.entity;

public class Country extends AbstractEntity<Integer> {
	
	private static final long serialVersionUID = -2827957421861584437L;
	private String name;
	private String url;
	private Integer tourCount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getTourCount() {
		return tourCount;
	}

	public void setTourCount(Integer tourCount) {
		this.tourCount = tourCount;
	}

	@Override
	public String toString() {
		return String.format("Country [id=%s, name=%s, url=%s, tourCount=%s]", getId(), name, url, tourCount);
	}
}