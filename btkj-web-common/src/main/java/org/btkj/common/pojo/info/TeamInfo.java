package org.btkj.common.pojo.info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.pojo.entity.User;
import org.btkj.statistics.pojo.model.Exploits;
import org.btkj.statistics.pojo.model.Exploit;

public class TeamInfo implements Serializable{

	private static final long serialVersionUID = -8073722664694128952L;

	private double total;					// 总业绩
	private List<Employee> employees;		// 员工业绩排序列表
	
	public TeamInfo(Map<Integer, Integer> map, Exploits exploits, List<User> users) {
		this.total = exploits.getTotal();
		this.employees = new ArrayList<Employee>(map.size());
		List<Exploit> list = exploits.getEmployeeExploits();
		Iterator<Entry<Integer, Integer>> itr = map.entrySet().iterator();
		a : while (itr.hasNext()) {
			Entry<Integer, Integer> entry = itr.next();
			itr.remove();
			Iterator<Exploit> titr = list.iterator();
			while (itr.hasNext()) {
				Exploit employee = titr.next();
				if (employee.getEmployeeId() != entry.getKey())
					continue;
				titr.remove();
				Iterator<User> uitr = users.iterator();
				while (uitr.hasNext()) {
					User user = uitr.next();
					if (user.getUid() != entry.getValue())
						continue;
					uitr.remove();
					this.employees.add(new Employee(employee, user));
					break a;
				}
				this.employees.add(new Employee(employee, null));
				break a;
			}
			Iterator<User> uitr = users.iterator();
			while (uitr.hasNext()) {
				User user = uitr.next();
				if (user.getUid() != entry.getValue())
					continue;
				uitr.remove();
				this.employees.add(new Employee(entry.getKey(), user));
				break a;
			}
			this.employees.add(new Employee(entry.getKey(), null));
			break a;
		}
	}
	
	private class Employee implements Serializable {
		private static final long serialVersionUID = 8209277117336123349L;
		private int id;
		private UserTips user;
		private double performance;
		
		public Employee(int employeeId, User user) {
			this.id = employeeId;
			this.performance = 0;
			if (null != user)
				this.user = new UserTips(user);
		}
		
		public Employee(Exploit employee, User user) {
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
	
	private class UserTips extends org.btkj.pojo.info.tips.UserTips {

		private static final long serialVersionUID = -782901248357849215L;
		
		private String mobile;
		
		public UserTips(User user) {
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
