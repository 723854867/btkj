package org.btkj.pojo.model;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;

/**
 * 一个雇员对象
 * 
 * @author ahab
 *
 */
public class EmployeeModel extends UserModel {

	private static final long serialVersionUID = 8750324897435661418L;

	private Tenant tenant;
	private Employee employee;
	
	public EmployeeModel() {}
	
	public EmployeeModel(App app, User user, Tenant tenant, Employee employee) {
		super(app, user);
		this.tenant = tenant;
		this.employee = employee;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
