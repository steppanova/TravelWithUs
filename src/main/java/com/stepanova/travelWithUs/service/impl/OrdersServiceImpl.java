package com.stepanova.travelWithUs.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;

import com.stepanova.travelWithUs.entity.Account;
import com.stepanova.travelWithUs.entity.Orders;
import com.stepanova.travelWithUs.entity.OrdersItem;
import com.stepanova.travelWithUs.entity.Tour;
import com.stepanova.travelWithUs.exception.AccessDeniedException;
import com.stepanova.travelWithUs.exception.InternalServerErrorException;
import com.stepanova.travelWithUs.exception.ResourceNotFoundException;
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
	private final ResultSetHandler<Orders> ordersResultSetHandler = 
			ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.ORDERS_RESULT_SET_HANDLER);
	private final ResultSetHandler<List<OrdersItem>> ordersItemListResultSetHandler = 
			ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.ORDERS_ITEM_RESULT_SET_HANDLER);
	private final ResultSetHandler<List<Orders>> orderssResultSetHandler = 
			ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.ORDERS_RESULT_SET_HANDLER);
	private final ResultSetHandler<Integer> countResultSetHandler = 
			ResultSetHandlerFactory.getCountResultSetHandler();

	
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

	@Override
	public long makeOrder(ShoppingCart shoppingCart, CurrentAccount currentAccount) {
		if (shoppingCart == null || shoppingCart.getItems().isEmpty()) {
			throw new InternalServerErrorException("shoppingCart is null or empty");
		}
		try (Connection c = dataSource.getConnection()) {
			Orders order = JDBCUtils.insert(c, "INSERT INTO orders(id_account,created) values(?,?,?)", ordersResultSetHandler, 
					currentAccount.getId(), new Timestamp(System.currentTimeMillis()));
			JDBCUtils.insertBatch(c, "insert into orders_item (id_orders,id_tour,count) values(?,?,?,?)", 
					toOrderItemParameterList(order.getId(), shoppingCart.getItems()));
			c.commit();
			return order.getId();
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
		}
	}
	private List<Object[]> toOrderItemParameterList(long idOrders, Collection<ShoppingCartItem> items) {
		List<Object[]> parametersList = new ArrayList<>();
		for (ShoppingCartItem item : items) {
			parametersList.add(new Object[] { idOrders, item.getTour().getId(), item.getCount() });
		}
		return parametersList;
	}
	@Override
	public Orders findOrderById(long id, CurrentAccount currentAccount) {
		try (Connection c = dataSource.getConnection()) {
			Orders order = JDBCUtils.select(c, "select * from orders where id=?", ordersResultSetHandler, id);
			if (order == null) {
				throw new ResourceNotFoundException("Order not found by id: " + id);
			}
			if (!order.getIdAccount().equals(currentAccount.getId())) {
				throw new AccessDeniedException("Account with id=" + currentAccount.getId() + " is not owner for order with id=" + id);
			}
			List<OrdersItem> list = JDBCUtils.select(c,
					"select o.id as id, o.id_orders as id_orders, o.id_tour, o.count, t.*, c.name as country, ci.name as city from orders_item o, tour t, country c, city ci "
							+ "where ci.id=t.id_city and c.id=t.id_country and o.id_tour=t.id and o.id_orders=?",
					ordersItemListResultSetHandler, id);
			order.setItems(list);
			return order;
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
		}
	}

	@Override
	public List<Orders> listMyOrders(CurrentAccount currentAccount, int page, int limit) {
		int offset = (page - 1) * limit;
		try (Connection c = dataSource.getConnection()) {
			List<Orders> orders = JDBCUtils.select(c, "select * from orders where id_account=? order by id desc limit ? offset ?", orderssResultSetHandler, currentAccount.getId(), limit, offset);
			return orders;
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
		}
	}

	@Override
	public int countMyOrders(CurrentAccount currentAccount) {
		try (Connection c = dataSource.getConnection()) {
			return JDBCUtils.select(c, "select count(*) from orders where id_account=?", countResultSetHandler, currentAccount.getId());
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
		}
	}
}
