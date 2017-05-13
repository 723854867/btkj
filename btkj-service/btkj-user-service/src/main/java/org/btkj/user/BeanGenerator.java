package org.btkj.user;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.SpecialCommission;
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
	
	public static final Employee newEmployee(User user, Tenant tenant, Employee parent, String name, String identity) {
		Employee employee = new Employee();
		employee.setUid(user.getUid());
		employee.setTid(tenant.getTid());
		employee.setParentId(null == parent ? 0 : parent.getId());
		employee.setLevel(null == parent ? 1 : parent.getLevel() + 1);
		employee.setLeft(null == parent ? 1 : parent.getRight());
		employee.setRight(employee.getLeft() + 1);
		employee.setName(name);
		employee.setIdentity(identity);
		employee.setMobile(user.getMobile());
		
		int time = DateUtils.currentTime();
		employee.setCreated(time);
		employee.setUpdated(time);
		return employee;
	}
	
	public static final App newApp(int region, String name, int maxTenantsCount, boolean tenantAddAutonomy) {
		App app = new App();
		app.setRegion(region);
		app.setName(name);
		app.setMaxTenantsCount(maxTenantsCount);
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
	
	public static final ApplyInfo newApply(Tenant tenant, User user, EmployeeForm chief, String name, String identity) { 
		ApplyInfo ai = new ApplyInfo();
		ai.setTid(tenant.getTid());
		ai.setUid(user.getUid());
		ai.setName(name);
		ai.setMobile(user.getMobile());
		ai.setChief(chief.getUser().getUid());
		ai.setChiefName(chief.getEmployee().getName());
		ai.setMobile(chief.getUser().getMobile());
		ai.setIdentity(identity);
		ai.setTime(DateUtils.currentTime());
		return ai;
	}
	
	public static final SpecialCommission newSpecialCommission(EmployeeInfo employeeInfo) {
		SpecialCommission specialCommission = new SpecialCommission();
		specialCommission.setEid(employeeInfo.getId());
		specialCommission.setNoBusinessCar(employeeInfo.getBusinessCar());
		specialCommission.setNoBusinessTruck(employeeInfo.getNoBusinessTruck());
		specialCommission.setBusinessCar(employeeInfo.getBusinessCar());
		specialCommission.setBusinessTruck(employeeInfo.getBusinessTruck());
		specialCommission.setTakeEffectTime(employeeInfo.getTakeEffectTime());
		specialCommission.setCommercialInsuranceRatio(employeeInfo.getCommercialInsuranceRatio());
		specialCommission.setCommercialInsuranceType(employeeInfo.getCommercialInsuranceType());
		specialCommission.setCompulsoryInsuranceRatio(employeeInfo.getCompulsoryInsuranceRatio());
		specialCommission.setCompulsoryInsuranceType(employeeInfo.getCompulsoryInsuranceType());
		specialCommission.setUpdated(DateUtils.currentTime());
		return specialCommission;
	}
	
	/**
	 * 用于存储后台商家管理中部分雇员修改信息
	 * @param employee
	 * @return
	 */
	public static final Employee newEmployeeSave(EmployeeInfo employeeInfo) {
		Employee employee = new Employee();
		employee.setId(employee.getId());
		employee.setPayType(employeeInfo.getPayType());
		employee.setTagMod(employeeInfo.getTagMod());
		employee.setScaleCommission(employeeInfo.getScaleCommission());
		employee.setManageCommission(employeeInfo.getManageCommission());
		employee.setUpdated(DateUtils.currentTime());
		return employee;
	}
}
