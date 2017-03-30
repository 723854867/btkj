package org.btkj.test;

import org.btkj.pojo.entity.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:conf/spring/*.xml");
		Tx tx = context.getBean("tx", Tx.class);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					tx.selectForUpdate(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					tx.selectForUpdate(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

//					User user = new User();
//					user.setAppId(102);
//					user.setName("ss");
//					user.setIdentity("123");
//					user.setMobile("+8651");
//					try {
//						tx.insert(user);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
			}
		}).start();
	}
}
