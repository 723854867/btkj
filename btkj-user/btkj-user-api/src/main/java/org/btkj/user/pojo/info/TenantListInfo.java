package org.btkj.user.pojo.info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;

public class TenantListInfo implements Serializable {

	private static final long serialVersionUID = -7737232217221256836L;
	
	private List<OwnedTenants> own;			// 已经拥有的代理公司列表
	private List<AuditTenants> audit;		// 正在审核的代理公司列表
	
	public TenantListInfo(List<TenantPO> own, List<EmployeePO> employees, List<TenantPO> audit) {
		this.own = new ArrayList<OwnedTenants>();
		this.audit = new ArrayList<AuditTenants>();
		Map<Integer, EmployeePO> map = new HashMap<Integer, EmployeePO>();
		for (EmployeePO employee : employees) 
			map.put(employee.getTid(), employee);
		for (TenantPO tenant : own) 
			this.own.add(new OwnedTenants(tenant, map.get(tenant.getTid())));
		for (TenantPO tenant : audit)
			this.audit.add(new AuditTenants(tenant));
	}
	
	private class OwnedTenants extends AuditTenants {
		
		private static final long serialVersionUID = 3846417279050082964L;
		
		private int employeeId;
		
		public OwnedTenants(TenantPO tenant, EmployeePO employee) {
			super(tenant);
			this.employeeId = employee.getId();
		}

		public int getEmployeeId() {
			return employeeId;
		}
		
		public void setEmployeeId(int employeeId) {
			this.employeeId = employeeId;
		}
	}
	
	private class AuditTenants implements Serializable {

		private static final long serialVersionUID = 7417305294947758415L;
		
		private int tid;
		private String tname;
		
		public AuditTenants(TenantPO tenant) {
			this.tid = tenant.getTid();
			this.tname = tenant.getName();
		}
		
		public int getTid() {
			return tid;
		}
		
		public void setTid(int tid) {
			this.tid = tid;
		}
		
		public String getTname() {
			return tname;
		}
		
		public void setTname(String tname) {
			this.tname = tname;
		}
	}
}
