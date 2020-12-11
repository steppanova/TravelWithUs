package com.stepanova.travelWithUs.service;

import java.util.List;

import com.stepanova.travelWithUs.entity.City;
import com.stepanova.travelWithUs.entity.Country;
import com.stepanova.travelWithUs.entity.Tour;
import com.stepanova.travelWithUs.form.SearchForm;

public interface TourService {
	
	List<Tour> listAllTours(int page, int limit);
	int countAllTours();
	int countToursByCountry(String categoryUrl);
	List<Country> listAllCountries();
	List<City> listAllCities();
	List<Tour> listToursBySearchForm(SearchForm searchForm, int page, int limit);
	int countToursBySearchForm(SearchForm searchForm);
	List<Tour> listToursByCountry(String countryUrl, int page, int limit);
	List<Tour> listToursByPrice(String countryUrl, int page, int limit);
}
