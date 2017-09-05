package org.btkj.manager.pojo.info;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.btkj.pojo.info.EmployeeTip;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.math.tree.Node;

public class ModularsInfo {

	private Set<String> pmodulars;
	private List<TenantModulars> tmodulars;
	
	public ModularsInfo(Set<String> pmodulars, Map<Integer, Set<String>> tmodulars, Map<Integer, EmployeeTip> employees) {
		this.pmodulars = pmodulars;
		this.tmodulars = new ArrayList<TenantModulars>();
		for (EmployeeTip employee : employees.values()) {
			if (employee.getLayer() == Node.ROOT_LAYER) 
				this.tmodulars.add(new TenantModulars(employee));
			else {
				Set<String> modulars = tmodulars.get(employee.getId());
				if (CollectionUtil.isEmpty(modulars))
					continue;
				this.tmodulars.add(new TenantModulars(employee, modulars));
			}
		}
	}
	
	public Set<String> getPmodulars() {
		return pmodulars;
	}
	
	public void setPmodulars(Set<String> pmodulars) {
		this.pmodulars = pmodulars;
	}
	
	public List<TenantModulars> getTmodulars() {
		return tmodulars;
	}
	
	public void setTmodulars(List<TenantModulars> tmodulars) {
		this.tmodulars = tmodulars;
	}
	
	private class TenantModulars {
		private int tid;
		private int mod;
		private String tname;
		private int employeeId;
		private Set<String> modulars;
		public TenantModulars(EmployeeTip employee) {
			this.tid = employee.getTid();
			this.mod = employee.getMod();
			this.tname = employee.getName();
			this.employeeId = employee.getId();
		}
		public TenantModulars(EmployeeTip employee, Set<String> modulars) {
			this(employee);
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
