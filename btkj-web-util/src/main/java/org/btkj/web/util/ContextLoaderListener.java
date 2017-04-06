package org.btkj.web.util;

import java.nio.file.NoSuchFileException;

import javax.servlet.ServletContextEvent;

import org.rapid.util.io.ResourceUtil;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

/**
 * 在 spring 容器初始化之前初始化 logback
 * 
 * @author ahab
 */
public class ContextLoaderListener extends org.springframework.web.context.ContextLoaderListener {

	private static final String LOGBACK_CONFIGURATION_LOCATION = "logbackConfigLocation";

	public ContextLoaderListener() {
	}

	public ContextLoaderListener(WebApplicationContext context) {
		super(context);
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		String logbackConfiguration = event.getServletContext().getInitParameter(LOGBACK_CONFIGURATION_LOCATION);
		LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
		JoranConfigurator configurator = new JoranConfigurator();
		configurator.setContext(context);
		context.reset();
		try {
			configurator.doConfigure(ResourceUtil.getFile(logbackConfiguration));
		} catch (NoSuchFileException | JoranException e) {
			LoggerFactory.getLogger(ContextLoaderListener.class).error("Logback initialize failure, system will closed!", e);
			System.exit(1);
		}
		super.contextInitialized(event);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
	}
}
