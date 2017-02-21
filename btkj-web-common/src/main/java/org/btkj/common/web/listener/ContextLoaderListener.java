 package org.btkj.common.web.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.WebApplicationContext;

/**
 * 在 spring 容器初始化之前初始化 logback
 * 
 * @author ahab
 */
public class ContextLoaderListener extends org.springframework.web.context.ContextLoaderListener {

	private static final String LOGBACK_CONFIGURATION_KEY = "logback.configurationFile";
	private static final String LOGBACK_CONFIGURATION_LOCATION = "logbackConfigLocation";

	public ContextLoaderListener() {
	}

	public ContextLoaderListener(WebApplicationContext context) {
		super(context);
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		String logbackConfiguration = event.getServletContext().getInitParameter(LOGBACK_CONFIGURATION_LOCATION);
		if (null != logbackConfiguration)
			System.setProperty(LOGBACK_CONFIGURATION_KEY, logbackConfiguration);
		super.contextInitialized(event);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
	}
}
