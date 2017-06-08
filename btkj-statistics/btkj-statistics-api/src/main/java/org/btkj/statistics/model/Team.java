package org.btkj.statistics.model;

import java.io.Serializable;
import java.util.List;

public class Team implements Serializable {

	private static final long serialVersionUID = -8208431771913440200L;

	private double performance;
	private List<TeamEmployee> employees;
	
	public Team() {}
	
	public Team(double performance, List<TeamEmployee> employees) {
		this.performance = performance;
		this.employees = employees;
	}
	
	public double getPerformance() {
		return performance;
	}
	
	public void setPerformance(double performance) {
		this.performance = performance;
	}
	
	public List<TeamEmployee> getEmployees() {
		return employees;
	}
	
	public void setEmployees(List<TeamEmployee> employees) {
		this.employees = employees;
	}
}
