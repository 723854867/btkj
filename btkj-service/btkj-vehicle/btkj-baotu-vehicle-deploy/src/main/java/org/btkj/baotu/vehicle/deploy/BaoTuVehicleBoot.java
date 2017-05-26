package org.btkj.baotu.vehicle.deploy;

import org.slf4j.bridge.SLF4JBridgeHandler;

import com.alibaba.dubbo.container.Main;
import com.alibaba.dubbo.container.spring.SpringContainer;

public class BaoTuVehicleBoot {

	public static void main(String[] args) throws InterruptedException {
		// 初始化日志系统，jul-to-slf4j 需要手动注册
		SLF4JBridgeHandler.removeHandlersForRootLogger();
		SLF4JBridgeHandler.install();
		System.setProperty("logback.configurationFile", BaoTuVehicleBoot.class.getResource("/conf/logback.xml").getFile());

		// 设置 spring 配置文件所在路径
		System.setProperty(SpringContainer.SPRING_CONFIG, "classpath*:conf/spring/*.xml");

		// 启动 dubbo
		Main.main(args);	
	}
}
