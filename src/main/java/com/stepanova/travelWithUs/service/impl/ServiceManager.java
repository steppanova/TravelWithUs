package com.stepanova.travelWithUs.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

import com.stepanova.travelWithUs.service.OrdersService;
import com.stepanova.travelWithUs.service.TourService;

public class ServiceManager {
	public static ServiceManager getInstance(ServletContext context) {
		ServiceManager instance = (ServiceManager) context.getAttribute("SERVICE_MANAGER");
		if (instance == null) {
			instance = new ServiceManager(context);
			context.setAttribute("SERVICE_MANAGER", instance);
		}
		return instance;
	}
	public TourService getProductService() {
		return tourService;
	}
	public OrdersService getOrderService() {
		return ordersService;
	}
	public String getApplicationProperty(String key) {
		return applicationProperties.getProperty(key);
	}
	public void close() {
		
	}

	private final Properties applicationProperties = new Properties();
	private final TourService tourService;
	private final OrdersService ordersService;
	private ServiceManager(ServletContext context) {
		loadApplicationProperties();
		tourService = new TourServiceImpl();
		ordersService = new OrdersServiceImpl();
	}
	
	private void loadApplicationProperties(){
		try(InputStream in = ServiceManager.class.getClassLoader().getResourceAsStream("application.properties")) {
			applicationProperties.load(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
