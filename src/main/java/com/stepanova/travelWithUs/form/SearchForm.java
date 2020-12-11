package com.stepanova.travelWithUs.form;

import java.util.ArrayList;
import java.util.List;

public class SearchForm {
	private String query;
	private List<Integer> countries;
	private List<Integer> cities;
	
	public SearchForm(String query, String[] countries, String[] cities) {
		super();
		this.query = query;
		this.countries = convert(countries);
		this.cities = convert(cities);
	}
	private List<Integer> convert(String[] args) {
		if(args == null) {
			return null;
		} else {
			List<Integer> res = new ArrayList<>();
			for(String arg : args) {
				res.add(Integer.parseInt(arg));
			}
			return res;
		}
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public List<Integer> getCountries() {
		return countries;
	}
	public void setCountries(List<Integer> countries) {
		this.countries = countries;
	}
	public List<Integer> getCities() {
		return cities;
	}
	public void setCities(List<Integer> cities) {
		this.cities = cities;
	}
	public boolean isCountriesEmpty(){
		return countries.isEmpty();
	}
	public boolean isCitiesEmpty(){
		return cities.isEmpty();
	}
}
