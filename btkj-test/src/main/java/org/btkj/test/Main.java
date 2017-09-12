package org.btkj.test;

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
			}
		}).start();
	}
}
