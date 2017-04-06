package org.btkj.user;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.rapid.util.lang.DateUtils;

public class BeanGenerator {

	/**
	 * 新用户
	 * 
	 * @param cm
	 * @param identity
	 * @param name
	 * @return
	 */
	public static final User newUser(int appId, String mobile, String identity, String name) { 
		User user = new User();
		user.setAppId(appId);
		user.setMobile(mobile);
		user.setIdentity(identity);
		user.setName(name);
		
		int time = DateUtils.currentTime();
		user.setCreated(time);
		user.setUpdated(time);
		user.setAppLoginTime(time);
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
	
	public static final App newApp(String name) {
		App app = new App();
		app.setName(name);
		return app;
	}
	
	public static final Tenant newTenant(Region region, int appId, String name, String pwd) {
		Tenant tenant = new Tenant();
		tenant.setName(name);
		tenant.setAppId(appId);
		tenant.setRegionId(region.getId());
		tenant.setPwd(pwd);
		tenant.setPrivilege("0");
		
		int time = DateUtils.currentTime();
		tenant.setCreated(time);
		tenant.setUpdated(time);
		return tenant;
	}
}
