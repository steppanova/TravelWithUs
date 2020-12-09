package com.stepanova.travelWithUs.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stepanova.travelWithUs.service.impl.ServiceManager;

public class TravelWithUsApplicationListener implements ServletContextListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(TravelWithUsApplicationListener.class);
	private ServiceManager serviceManager;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			serviceManager = ServiceManager.getInstance(sce.getServletContext());
		} catch (RuntimeException e) {
			LOGGER.error("Web application 'travelWithUs' init failed: " + e.getMessage(), e);
			throw e;
		}
		LOGGER.info("Web application 'travelWithUs' initialized");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		serviceManager.close();
		LOGGER.info("Web application 'ishop' destroyed");
	}
}
