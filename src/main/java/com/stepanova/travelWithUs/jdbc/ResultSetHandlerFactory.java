package com.stepanova.travelWithUs.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stepanova.travelWithUs.entity.Account;
import com.stepanova.travelWithUs.entity.City;
import com.stepanova.travelWithUs.entity.Country;
import com.stepanova.travelWithUs.entity.Tour;

public final class ResultSetHandlerFactory {

	public final static ResultSetHandler<Tour> TOUR_RESULT_SET_HANDLER = new ResultSetHandler<Tour>() {
		@Override
		public Tour handle(ResultSet rs) throws SQLException {
			Tour t = new Tour();
			t.setId(rs.getInt("id"));
			t.setName(rs.getString("name"));
			t.setHotelType(rs.getString("hotel_type"));
			t.setDescription(rs.getString("description"));
			t.setDuration(rs.getInt("duration"));
			t.setPrice(rs.getBigDecimal("price"));
			t.setPerson(rs.getInt("person"));
			t.setHotTour(rs.getInt("hot_tour"));
			t.setImageLink(rs.getString("image_link"));
			t.setCountry(rs.getString("country"));
			t.setCity(rs.getString("city"));
			return t;
		}
	};
	public final static ResultSetHandler<Country> COUNTRY_RESULT_SET_HANDLER = new ResultSetHandler<Country>() {
		@Override
		public Country handle(ResultSet rs) throws SQLException {
			Country c = new Country();
			c.setId(rs.getInt("id"));
			c.setName(rs.getString("name"));
			c.setUrl(rs.getString("url"));
			c.setTourCount(rs.getInt("tour_count"));
			return c;
		}
	};

	public final static ResultSetHandler<City> CITY_RESULT_SET_HANDLER = new ResultSetHandler<City>() {
		@Override
		public City handle(ResultSet rs) throws SQLException {
			City ci = new City();
			ci.setId(rs.getInt("id"));
			ci.setName(rs.getString("name"));
			ci.setTourCount(rs.getInt("tour_count"));
			return ci;
		}
	};
	
	public final static ResultSetHandler<Account> ACCOUNT_RESULT_SET_HANDLER = new ResultSetHandler<Account>() {
		@Override
		public Account handle(ResultSet rs) throws SQLException {
			Account a = new Account();
			a.setId(rs.getInt("id"));
			a.setEmail(rs.getString("email"));
			a.setName(rs.getString("name"));
			return a;
		}
	};
	public final static ResultSetHandler<Integer> getCountResultSetHandler() {
		return new ResultSetHandler<Integer>() {
			@Override
			public Integer handle(ResultSet rs) throws SQLException {
				if (rs.next()) {
					return rs.getInt(1);
				} else {
					return 0;
				}
			}
		};
	}
	
	public final static <T> ResultSetHandler<T> getSingleResultSetHandler(final ResultSetHandler<T> oneRowResultSetHandler) {
		return new ResultSetHandler<T>() {
			@Override
			public T handle(ResultSet rs) throws SQLException {
				if (rs.next()) {
					return oneRowResultSetHandler.handle(rs);
				} else {
					return null;
				}
			}
		};
	}

	public final static <T> ResultSetHandler<List<T>> getListResultSetHandler(final ResultSetHandler<T> oneRowResultSetHandler) {
		return new ResultSetHandler<List<T>>() {
			@Override
			public List<T> handle(ResultSet rs) throws SQLException {
				List<T> list = new ArrayList<>();
				while (rs.next()) {
					list.add(oneRowResultSetHandler.handle(rs));
				}
				return list;
			}
		};
	}

	private ResultSetHandlerFactory() {
	}
}
