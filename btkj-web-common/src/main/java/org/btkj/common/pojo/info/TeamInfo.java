package org.btkj.common.pojo.info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.statistics.pojo.model.Exploits;
import org.btkj.pojo.po.UserPO;
import org.btkj.statistics.pojo.model.Exploit;

public class TeamInfo implements Serializable{

	private static final long serialVersionUID = -8073722664694128952L;

	private double total;					// 总业绩
	private List<Employee> employees;		// 员工业绩排序列表
	
	public TeamInfo(Map<Integer, Integer> map, Exploits exploits, Map<Integer, UserPO> users) {
		this.total = exploits.getTotal();
		this.employees = new ArrayList<Employee>(map.size());
		List<Exploit> list = exploits.getEmployeeExploits();
		Iterator<Entry<Integer, Integer>> itr = map.entrySet().iterator();
		while (itr.hasNext()) {
			Entry<Integer, Integer> entry = itr.next();
			itr.remove();
			UserPO user = users.remove(entry.getValue());
			Iterator<Exploit> titr = list.iterator();
			Exploit exploit = null;
			while (titr.hasNext()) {
				Exploit temp = titr.next();
				if (temp.getEmployeeId() != entry.getKey())
					continue;
				titr.remove();
				exploit = temp;
			}
			if (null == exploit)
				this.employees.add(new Employee(entry.getKey(), user));
			else
				this.employees.add(new Employee(exploit, user));
		}
	}
	
	private class Employee implements Serializable {
		private static final long serialVersionUID = 8209277117336123349L;
		private int id;
		private UserTips user;
		private double performance;
		
		public Employee(int employeeId, UserPO user) {
			this.id = employeeId;
			this.performance = 0;
			if (null != user)
				this.user = new UserTips(user);
		}
		
		public Employee(Exploit employee, UserPO user) {
			this.id = employee.getEmployeeId();
			this.performance = employee.getQuota();
			if (null != user)
				this.user = new UserTips(user);
		}
		
		public int getId() {
			return id;
		}
		
		public void setId(int id) {
			this.id = id;
		}
		
		public UserTips getUser() {
			return user;
		}
		
		public void setUser(UserTips user) {
			this.user = user;
		}
		
		public double getPerformance() {
			return performance;
		}
		
		public void setPerformance(double performance) {
			this.performance = performance;
		}
	}
	
	private class UserTips extends org.btkj.pojo.vo.UserTips {

		private static final long serialVersionUID = -782901248357849215L;
		
		private String mobile;
		
		public UserTips(UserPO user) {
			super(user);
			this.mobile = user.getMobile();
		}
		
		public String getMobile() {
			return mobile;
		}
		
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
	}
}
