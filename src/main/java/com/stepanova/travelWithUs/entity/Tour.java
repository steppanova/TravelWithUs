package com.stepanova.travelWithUs.entity;

import java.math.BigDecimal;

public class Tour extends AbstractEntity<Integer> {
	
	private static final long serialVersionUID = -6754510065984161187L;
	private String name;
	private String hotelType;
	private String description;
	private String imageLink;
	private BigDecimal price;
	private String country;
	private String city;
    private Integer duration;
    private Integer person;
    private Integer hotTour;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getHotelType() {
		return hotelType;
	}

	public void setHotelType(String hotelType) {
		this.hotelType = hotelType;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getPerson() {
		return person;
	}

	public void setPerson(Integer person) {
		this.person = person;
	}

	public Integer getHotTour() {
		return hotTour;
	}

	public void setHotTour(Integer hotTour) {
		this.hotTour = hotTour;
	}

	@Override
	public String toString() {
		return String.format(
				"Tour [id=%s, name=%s, hotel_type=%s, description=%s, imageLink=%s, price=%s, country=%s, city=%s, duration=%s, person=%s, hotTour=%s]",
				getId(), name, hotelType, description, imageLink, price, country, city, duration, person, hotTour);
	}

	
}
