package org.btkj.master.service;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Resource;

import org.btkj.master.persistence.dao.AdministratorDao;
import org.btkj.master.persistence.domain.Administrator;
import org.rapid.util.lang.DateUtils;

public class Role {

	private AtomicBoolean lock;
	private Administrator administrator;
	
	@Resource
	private AdministratorDao administratorDao;
	
	public Role(Administrator administrator) {
		this.administrator = administrator;
		this.lock = new AtomicBoolean(false);
	}
	
	/**
	 * 执行登录动作
	 */
	public void login() { 
		administrator.setUpdated(DateUtils.currentTime());
		administratorDao.update(administrator);
	}
	
	public boolean tryLock() { 
		return lock.compareAndSet(false, true);
	}
	
	public void unlock() { 
		lock.set(false);
	}

	public int getId() {
		return administrator.getId();
	}
	
	public String getPwd() {
		return administrator.getPwd();
	}
	
	public String getName() { 
		return administrator.getName();
	}
}
