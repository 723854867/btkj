package org.btkj.common.pojo.info;

import java.io.Serializable;
import java.util.List;

import org.btkj.pojo.info.tips.UserTips;

public class TeamInfo implements Serializable{

	private static final long serialVersionUID = -8073722664694128952L;

	private double total;					// 总业绩
	private List<Employee> employees;		// 员工业绩排序列表
	
	private class Employee implements Serializable {
		private static final long serialVersionUID = 8209277117336123349L;
		private int id;
		private UserTips user;
		private double performance;
		
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
}
