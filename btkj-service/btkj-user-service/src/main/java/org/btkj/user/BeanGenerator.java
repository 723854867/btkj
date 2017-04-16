package org.btkj.user;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.ApplyInfo;
import org.rapid.util.lang.DateUtils;

public class BeanGenerator {

	public static final User newUser(int appId, String mobile) { 
		User user = new User();
		user.setAppId(appId);
		user.setMobile(mobile);
		
		int time = DateUtils.currentTime();
		user.setCreated(time);
		user.setUpdated(time);
		return user;
	}
	
	public static final User newUser(int appId, String mobile, String name, String identity) { 
		User user = new User();
		user.setAppId(appId);
		user.setMobile(mobile);
		user.setName(name);
		user.setIdentity(identity);
		
		int time = DateUtils.currentTime();
		user.setCreated(time);
		user.setUpdated(time);
		return user;
	}
	
	public static final Employee newEmployee(User user, Tenant tenant, Employee parent) {
		Employee employee = new Employee();
		employee.setUid(user.getUid());
		employee.setTid(tenant.getTid());
		employee.setParentId(null == parent ? 0 : parent.getId());
		employee.setLevel(null == parent ? 1 : parent.getLevel() + 1);
		employee.setLeft(null == parent ? 1 : parent.getRight());
		employee.setRight(employee.getLeft() + 1);
		
		int time = DateUtils.currentTime();
		employee.setCreated(time);
		employee.setUpdated(time);
		return employee;
	}
	
	public static final App newApp(int region, String name, int maxTenantsCount) {
		App app = new App();
		app.setRegion(region);
		app.setName(name);
		app.setMaxTenantsCount(maxTenantsCount);

		int time = DateUtils.currentTime();
		app.setCreated(time);
		app.setUpdated(time);
		return app;
	}
	
	public static final Tenant newTenant(int region, int appId, String name, String pwd) {
		Tenant tenant = new Tenant();
		tenant.setName(name);
		tenant.setAppId(appId);
		tenant.setRegion(region);
		tenant.setPwd(pwd);
		tenant.setPrivilege("0");
		
		int time = DateUtils.currentTime();
		tenant.setCreated(time);
		tenant.setUpdated(time);
		return tenant;
	}
	
	public static final ApplyInfo newApply(int tid, int uid, int chief) { 
		ApplyInfo ai = new ApplyInfo();
		ai.setUid(uid);
		ai.setTid(tid);
		ai.setChief(chief);
		ai.setTime(DateUtils.currentTime());
		return ai;
	}
}
