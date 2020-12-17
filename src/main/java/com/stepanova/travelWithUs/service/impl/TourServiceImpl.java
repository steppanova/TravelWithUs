package com.stepanova.travelWithUs.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;

import com.stepanova.travelWithUs.entity.City;
import com.stepanova.travelWithUs.entity.Country;
import com.stepanova.travelWithUs.entity.Tour;
import com.stepanova.travelWithUs.exception.InternalServerErrorException;
import com.stepanova.travelWithUs.form.SearchForm;
import com.stepanova.travelWithUs.jdbc.JDBCUtils;
import com.stepanova.travelWithUs.jdbc.ResultSetHandler;
import com.stepanova.travelWithUs.jdbc.ResultSetHandlerFactory;
import com.stepanova.travelWithUs.jdbc.SearchQuery;
import com.stepanova.travelWithUs.service.TourService;

class TourServiceImpl implements TourService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TourServiceImpl.class);
	private static final ResultSetHandler<List<Tour>> toursResultSetHandler = 
			ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.TOUR_RESULT_SET_HANDLER);
	private final ResultSetHandler<List<Country>> countryListResultSetHandler = 
			ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.COUNTRY_RESULT_SET_HANDLER);
	private final ResultSetHandler<List<City>> cityListResultSetHandler = 
			ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.CITY_RESULT_SET_HANDLER);
	private final ResultSetHandler<Integer> countResultSetHandler = ResultSetHandlerFactory.getCountResultSetHandler();
	private final DataSource dataSource;
	
	public TourServiceImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public List<Tour> listAllTours(int page, int limit) {
		try (Connection c = dataSource.getConnection()) {
			int offset = (page - 1) * limit;
			return JDBCUtils.select(c, "select t.*, c.name as country, ci.name as city from tour t, city ci, country c "
					+ "where c.id=t.id_country and ci.id=t.id_city limit ? offset ?", toursResultSetHandler, limit, offset);
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute sql query: " + e.getMessage(), e);
		}
	}
	@Override
	public List<Tour> listToursByCountry(String countryUrl, int page, int limit) {
		try (Connection c = dataSource.getConnection()) {
			int offset = (page - 1) * limit;
			return JDBCUtils.select(c,
					"select t.*, c.name as country, ci.name as city from tour t, country c, city ci where c.url=? and ci.id=t.id_city and c.id=t.id_country order by t.id limit ? offset ?",
					toursResultSetHandler, countryUrl, limit, offset);
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute sql query: " + e.getMessage(), e);
		}
	}

	@Override
	public List<Country> listAllCountries() {
		try (Connection c = dataSource.getConnection()) {
			return JDBCUtils.select(c, "select * FROM country order by id", countryListResultSetHandler);
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute sql query: " + e.getMessage(), e);
		}
	}

	@Override
	public List<City> listAllCities() {
		try (Connection c = dataSource.getConnection()) {
			return JDBCUtils.select(c, "select * FROM city order by name", cityListResultSetHandler);
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute sql query: " + e.getMessage(), e);
		}
	}
	
	@Override
	public int countAllTours() {
		try (Connection c = dataSource.getConnection()) {
			return JDBCUtils.select(c, "select count(*) from tour", countResultSetHandler);
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute sql query: " + e.getMessage(), e);
		}
	}

	@Override
	public int countToursByCountry(String countryUrl) {
		try (Connection c = dataSource.getConnection()) {
			return JDBCUtils.select(c, "select count(*) from tour t, country c where c.id=t.id_country and c.url=?", countResultSetHandler, countryUrl);
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute sql query: " + e.getMessage(), e);
		}
	}

	@Override
	public List<Tour> listToursBySearchForm(SearchForm form, int page, int limit) {
		try (Connection c = dataSource.getConnection()) {
			int offset = (page - 1) * limit;
			SearchQuery sq = buildSearchQuery("t.*, c.name as country, ci.name as city", form);
			sq.getSql().append(" order by t.id limit ? offset ?");
			sq.getParams().add(limit);
			sq.getParams().add(offset);
			LOGGER.debug("search query={} with params={}", sq.getSql(), sq.getParams());
			return JDBCUtils.select(c, sq.getSql().toString(), toursResultSetHandler, sq.getParams().toArray());
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
		}
	}

	@Override
	public int countToursBySearchForm(SearchForm form) {
		try (Connection c = dataSource.getConnection()) {
			SearchQuery sq = buildSearchQuery("count(*)", form);
			LOGGER.debug("search query={} with params={}", sq.getSql(), sq.getParams());
			return JDBCUtils.select(c, sq.getSql().toString(), countResultSetHandler, sq.getParams().toArray());
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
		}
	}
	protected SearchQuery buildSearchQuery(String selectFields, SearchForm form) {
		List<Object> params = new ArrayList<>();
		StringBuilder sql = new StringBuilder("select ");
		sql.append(selectFields).append(" from tour t, country c, city ci where ci.id=t.id_city and c.id=t.id_country and (t.name like ? or t.description like ?)");
		params.add("%" + form.getQuery() + "%");
		params.add("%" + form.getQuery() + "%");
		JDBCUtils.populateSqlAndParams(sql, params, form.getCountries(), "c.id = ?");
		JDBCUtils.populateSqlAndParams(sql, params, form.getCities(), "ci.id = ?");
		return new SearchQuery(sql, params);
	}

}
	
