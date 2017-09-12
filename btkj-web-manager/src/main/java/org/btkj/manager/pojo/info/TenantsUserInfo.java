package org.btkj.manager.pojo.info;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.btkj.pojo.info.EmployeeTip;

public class TenantsUserInfo {

	private Set<String> pmodulars;
	private List<TenantInfo> tenants;
	
	public TenantsUserInfo(Set<String> pmodulars, Map<Integer, Set<String>> tmodulars, Map<Integer, EmployeeTip> employees) {
		this.pmodulars = pmodulars;
		this.tenants = new ArrayList<TenantInfo>();
		for (EmployeeTip employee : employees.values()) {
			Set<String> modulars = tmodulars.get(employee.getId());
			this.tenants.add(new TenantInfo(employee, modulars));
		}
	}
	
	public Set<String> getPmodulars() {
		return pmodulars;
	}
	
	public void setPmodulars(Set<String> pmodulars) {
		this.pmodulars = pmodulars;
	}
	
	public List<TenantInfo> getTenants() {
		return tenants;
	}
	
	public void setTenants(List<TenantInfo> tenants) {
		this.tenants = tenants;
	}
	
	private class TenantInfo {
		private int tid;
		private int mod;
		private int tmod;
		private int layer;
		private String tname;
		private int employeeId;
		private Set<String> modulars;
		public TenantInfo(EmployeeTip employee, Set<String> modulars) {
			this.tid = employee.getTid();
			this.mod = employee.getMod();
			this.tmod = employee.getTmod();
			this.tname = employee.getTname();
			this.layer = employee.getLayer();
			this.employeeId = employee.getId();
			this.modulars = modulars;
		}
		public int getTid() {
			return tid;
		}
		public void setTid(int tid) {
			this.tid = tid;
		}
		public int getMod() {
			return mod;
		}
		public void setMod(int mod) {
			this.mod = mod;
		}
		public int getTmod() {
			return tmod;
		}
		public void setTmod(int tmod) {
			this.tmod = tmod;
		}
		public int getLayer() {
			return layer;
		}
		public void setLayer(int layer) {
			this.layer = layer;
		}
		public String getTname() {
			return tname;
		}
		public void setTname(String tname) {
			this.tname = tname;
		}
		public int getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(int employeeId) {
			this.employeeId = employeeId;
		}
		public Set<String> getModulars() {
			return modulars;
		}
		public void setModulars(Set<String> modulars) {
			this.modulars = modulars;
		}
	}
}
