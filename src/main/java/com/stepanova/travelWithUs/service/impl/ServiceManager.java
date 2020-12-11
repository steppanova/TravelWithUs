package com.stepanova.travelWithUs.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.stepanova.travelWithUs.service.OrdersService;
import com.stepanova.travelWithUs.service.SocialService;
import com.stepanova.travelWithUs.service.TourService;

public class ServiceManager {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceManager.class);
	public static ServiceManager getInstance(ServletContext context) {
		ServiceManager instance = (ServiceManager) context.getAttribute("SERVICE_MANAGER");
		if (instance == null) {
			instance = new ServiceManager(context);
			context.setAttribute("SERVICE_MANAGER", instance);
		}
		return instance;
	}
	public TourService getTourService() {
		return tourService;
	}
	public OrdersService getOrdersService() {
		return ordersService;
	}
	public SocialService getSocialService() {
		return socialService;
	}
	public String getApplicationProperty(String key) {
		return applicationProperties.getProperty(key);
	}
	public void close() {
		try {
			dataSource.close();
		} catch (SQLException e) {
			LOGGER.error("Close datasource failed: "+e.getMessage(), e);
		}
	}

	private final Properties applicationProperties = new Properties();
	private final BasicDataSource dataSource;
	private final TourService tourService;
	private final OrdersService ordersService;
	private final SocialService socialService;
	private ServiceManager(ServletContext context) {
		loadApplicationProperties();
		dataSource = createDataSource();
		tourService = new TourServiceImpl(dataSource);
		ordersService = new OrdersServiceImpl(dataSource);
		socialService = new FacebookSocialService(this);
	}
	
	private BasicDataSource createDataSource(){
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDefaultAutoCommit(false);
		dataSource.setRollbackOnReturn(true);
		dataSource.setDriverClassName(getApplicationProperty("db.driver"));
		dataSource.setUrl(getApplicationProperty("db.url"));
		dataSource.setUsername(getApplicationProperty("db.username"));
		dataSource.setPassword(getApplicationProperty("db.password"));
		dataSource.setInitialSize(Integer.parseInt(getApplicationProperty("db.pool.initSize")));
		dataSource.setMaxTotal(Integer.parseInt(getApplicationProperty("db.pool.maxSize")));
		return dataSource;
	}
	
	private void loadApplicationProperties(){
		try(InputStream in = ServiceManager.class.getClassLoader().getResourceAsStream("application.properties")) {
			applicationProperties.load(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
