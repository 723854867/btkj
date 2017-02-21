package org.btkj.user.deploy;

import com.alibaba.dubbo.container.spring.SpringContainer;

public class UserBoot {

	public static void main(String[] args) {
		System.setProperty(SpringContainer.SPRING_CONFIG, "classpath*:conf/spring/*.xml");
		com.alibaba.dubbo.container.Main.main(args);
	}
}
