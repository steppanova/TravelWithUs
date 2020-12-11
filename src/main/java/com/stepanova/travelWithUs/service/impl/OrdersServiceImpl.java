package com.stepanova.travelWithUs.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;

import com.stepanova.travelWithUs.entity.Account;
import com.stepanova.travelWithUs.entity.Tour;
import com.stepanova.travelWithUs.exception.InternalServerErrorException;
import com.stepanova.travelWithUs.form.TourForm;
import com.stepanova.travelWithUs.jdbc.JDBCUtils;
import com.stepanova.travelWithUs.jdbc.ResultSetHandler;
import com.stepanova.travelWithUs.jdbc.ResultSetHandlerFactory;
import com.stepanova.travelWithUs.model.CurrentAccount;
import com.stepanova.travelWithUs.model.ShoppingCart;
import com.stepanova.travelWithUs.model.ShoppingCartItem;
import com.stepanova.travelWithUs.model.SocialAccount;
import com.stepanova.travelWithUs.service.OrdersService;

public class OrdersServiceImpl implements OrdersService {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrdersServiceImpl.class);
	private static final ResultSetHandler<Tour> tourResultSetHandler = 
			ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.TOUR_RESULT_SET_HANDLER);
	private static final ResultSetHandler<Account> accountResultSetHandler = 
			ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.ACCOUNT_RESULT_SET_HANDLER);
	private final DataSource dataSource;
	
	public OrdersServiceImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	@Override
	public void addTourToShoppingCart(TourForm tourForm, ShoppingCart shoppingCart) {
		try (Connection c = dataSource.getConnection()) {
			Tour tour = JDBCUtils.select(c, "select t.*, c.name as country, ci.name as city from tour t, city ci, country c "
					+ "where c.id=t.id_country and ci.id=t.id_city and t.id=?", tourResultSetHandler, tourForm.getIdTour());
			if(tour == null) {
				throw new InternalServerErrorException("Tour not found by id="+tourForm.getIdTour());
			}
			shoppingCart.addTour(tour, tourForm.getCount());
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute sql query: " + e.getMessage(), e);
		}
	}
	@Override
	public void removeTourFromShoppingCart(TourForm form, ShoppingCart shoppingCart) {
		shoppingCart.removeTour(form.getIdTour(), form.getCount());
	}
	
	@Override
	public String serializeShoppingCart(ShoppingCart shoppingCart) {
		StringBuilder res = new StringBuilder();
		for (ShoppingCartItem item : shoppingCart.getItems()) {
			res.append(item.getTour().getId()).append("-").append(item.getCount()).append("|");
		}
		if (res.length() > 0) {
			res.deleteCharAt(res.length() - 1);
		}
		return res.toString();
	}

	@Override
	public ShoppingCart deserializeShoppingCart(String string) {
		ShoppingCart shoppingCart = new ShoppingCart();
		String[] items = string.split("\\|");
		for (String item : items) {
			try {
				String data[] = item.split("-");
				int idTour = Integer.parseInt(data[0]);
				int count = Integer.parseInt(data[1]);
				addTourToShoppingCart(new TourForm(idTour, count), shoppingCart);
			} catch (RuntimeException e) {
				LOGGER.error("Can't add tour to ShoppingCart during deserialization: item=" + item, e);
			}
		}
		return shoppingCart.getItems().isEmpty() ? null : shoppingCart;
	}
	
	@Override
	public CurrentAccount authentificate(SocialAccount socialAccount) {
		try (Connection c = dataSource.getConnection()) {
			Account account = JDBCUtils.select(c, "select * from account where email=?", accountResultSetHandler, socialAccount.getEmail());
			if (account == null) {
				account = new Account(socialAccount.getName(), socialAccount.getEmail());
				account = JDBCUtils.insert(c, "INSERT INTO account(name,email) VALUES (?,?)", accountResultSetHandler, account.getName(), account.getEmail());
				c.commit();
			}
			return account;
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
		}
	}
}
