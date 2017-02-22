package org.btkj.config.deploy;

import java.nio.file.NoSuchFileException;

import com.alibaba.dubbo.container.spring.SpringContainer;

public class ConfigBoot {
	
	private static final String LOGBACK_CONFIGURATION_KEY = "logback.configurationFile";

	public static void main(String[] args) throws NoSuchFileException {
		// 初始化 loback 日志系统
		_initLogBack();
		
		// 启动 dubbo 服务
		_bootstrap(args);
	}
	
	private static void _initLogBack() { 
		System.setProperty(LOGBACK_CONFIGURATION_KEY, ConfigBoot.class.getResource("/conf/logback.xml").getFile());
	}
	
	private static void _bootstrap(String[] args) { 
		System.setProperty(SpringContainer.SPRING_CONFIG, "classpath*:conf/spring/*.xml");
		com.alibaba.dubbo.container.Main.main(args);
	}
}
