package org.btkj.pojo.bo;

import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;

/**
 * 一个雇员对象
 * 
 * @author ahab
 *
 */
public class EmployeeModel extends UserModel {

	private static final long serialVersionUID = 8750324897435661418L;

	private TenantPO tenant;
	private EmployeePO employee;
	
	public EmployeeModel() {}
	
	public EmployeeModel(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee) {
		super(app, user);
		this.tenant = tenant;
		this.employee = employee;
	}

	public TenantPO getTenant() {
		return tenant;
	}

	public void setTenant(TenantPO tenant) {
		this.tenant = tenant;
	}

	public EmployeePO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeePO employee) {
		this.employee = employee;
	}
}
