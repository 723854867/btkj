package org.btkj.config.deploy;

import java.nio.file.NoSuchFileException;

import com.alibaba.dubbo.container.Main;
import com.alibaba.dubbo.container.spring.SpringContainer;

public class ConfigBoot {

	public static void main(String[] args) throws NoSuchFileException {
		// 设置 logback 的配置文件路径
		System.setProperty("logback.configurationFile", ConfigBoot.class.getResource("/conf/logback.xml").getFile());

		// 设置 spring 配置文件所在路径
		System.setProperty(SpringContainer.SPRING_CONFIG, "classpath*:conf/spring/*.xml");
		
		// 启动 dubbo
		Main.main(args);
	}
}
