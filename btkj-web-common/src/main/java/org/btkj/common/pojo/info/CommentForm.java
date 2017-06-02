package org.btkj.common.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.entity.Comment;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.info.tips.EmployeeTips;

public class CommentForm implements Serializable {

	private static final long serialVersionUID = -502812163244423783L;

	private int id;
	private int time;
	private String content;
	private EmployeeTips employeeTips;
	
	public CommentForm(Employee employee, Comment comment) {
		this.id = comment.getId();
		this.time = comment.getCreated();
		this.content = comment.getContent();
		this.employeeTips = new EmployeeTips(employee);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public EmployeeTips getEmployeeTips() {
		return employeeTips;
	}
	
	public void setEmployeeTips(EmployeeTips employeeTips) {
		this.employeeTips = employeeTips;
	}
}
