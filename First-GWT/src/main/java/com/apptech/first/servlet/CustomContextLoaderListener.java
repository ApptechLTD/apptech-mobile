package com.apptech.first.servlet;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

public class CustomContextLoaderListener extends ContextLoaderListener {

	
	private static final Logger log = Logger.getLogger(CustomContextLoaderListener.class.getName());
	static final long CamelShutdownTimeout = 20; // timeout in seconds for camel shutdown
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		
		//WebApplicationContext context = getCurrentWebApplicationContext();
		//CamelContext camelContext = (CamelContext)context.getBean("camel-apns");
		super.contextDestroyed(servletContextEvent);
		
		log.info("contextDestroyed called !!!");
		try {
			//if (context != null)
			//{
				//CamelContext camelContext = (CamelContext)context.getBean("camel-apns");
				//CamelContext camelContext = (CamelContext)context.getBean("camel-apns");
//				// Stop camel
//				log.info("contextDestroyed stopRoute apns-push..");
//				camelContext.stopRoute("apns-push", CamelShutdownTimeout, TimeUnit.SECONDS);
//				log.info("contextDestroyed stopRoute gcm-push..");
//				camelContext.stopRoute("gcm-push", CamelShutdownTimeout, TimeUnit.SECONDS);
//				Thread.sleep(CamelShutdownTimeout * 1000);
//				log.info("contextDestroyed stop..");
				//camelContext.stop();
				//log.info("contextDestroyed waiting for shutdown in time = " + CamelShutdownTimeout);
				//Thread.sleep(CamelShutdownTimeout * 1000);
			//}
		} 
		catch (Exception e) {
			log.log(Level.SEVERE, "contextDestroyed()  failed: " + e.getMessage(), e);
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		super.contextInitialized(servletContextEvent);
	}

}
