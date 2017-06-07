package org.btkj.user;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.SpecialBonus;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.ApplyInfo;
import org.btkj.pojo.info.EmployeeInfo;
import org.btkj.pojo.model.EmployeeForm;
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
	
	public static final App newApp(int region, String name, int maxTenantsCount, int maxArticlesCount, boolean tenantAddAutonomy) {
		App app = new App();
		app.setRegion(region);
		app.setName(name);
		app.setMaxTenantsCount(maxTenantsCount);
		app.setMaxArticlesCount(maxArticlesCount);
		app.setTenantAddAutonomy(tenantAddAutonomy);

		int time = DateUtils.currentTime();
		app.setCreated(time);
		app.setUpdated(time);
		return app;
	}
	
	public static final Tenant newTenant(int region, int appId, String name) {
		Tenant tenant = new Tenant();
		tenant.setName(name);
		tenant.setAppId(appId);
		tenant.setRegion(region);
		tenant.setPrivilege("0");
		
		int time = DateUtils.currentTime();
		tenant.setCreated(time);
		tenant.setUpdated(time);
		return tenant;
	}
	
	public static final ApplyInfo newApply(Tenant tenant, User user, EmployeeForm chief) { 
		ApplyInfo ai = new ApplyInfo();
		ai.setTid(tenant.getTid());
		ai.setUid(user.getUid());
		ai.setChief(chief.getEmployee().getId());
		ai.setTime(DateUtils.currentTime());
		return ai;
	}
	
	public static final SpecialBonus newSpecialBonus(EmployeeInfo employeeInfo) {
		SpecialBonus specialBonus = new SpecialBonus();
		specialBonus.setEid(employeeInfo.getId());
		specialBonus.setNoBusinessCar(employeeInfo.getBusinessCar());
		specialBonus.setNoBusinessTruck(employeeInfo.getNoBusinessTruck());
		specialBonus.setBusinessCar(employeeInfo.getBusinessCar());
		specialBonus.setBusinessTruck(employeeInfo.getBusinessTruck());
		specialBonus.setStartTime(employeeInfo.getStartTime());
		specialBonus.setVciRatio(employeeInfo.getVciRatio());
		specialBonus.setVciType(employeeInfo.getVciType());
		specialBonus.setTciRatio(employeeInfo.getTciRatio());
		specialBonus.setTciType(employeeInfo.getTciType());
		specialBonus.setUpdated(DateUtils.currentTime());
		return specialBonus;
	}
	
	/**
	 * 用于存储后台商家管理中部分雇员修改信息
	 * @param employee
	 * @return
	 */
	public static final Employee newEmployeeSave(EmployeeInfo employeeInfo) {
		Employee employee = new Employee();
		employee.setId(employeeInfo.getId());
		employee.setPayType(employeeInfo.getPayType());
		employee.setTagMod(employeeInfo.getTagMod());
		employee.setScaleBonus(employeeInfo.getScaleBonus());
		employee.setManageBonus(employeeInfo.getManageBonus());
		employee.setUpdated(DateUtils.currentTime());
		return employee;
	}
}
