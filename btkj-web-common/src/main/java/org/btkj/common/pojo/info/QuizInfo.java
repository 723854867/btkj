package org.btkj.common.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.entity.Quiz;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.tips.UserTips;

public class QuizInfo implements Serializable {

	private static final long serialVersionUID = 1401798625447294854L;

	private int id;
	private int time;
	private String content;
	private UserTips user;
	
	public QuizInfo(User user, Quiz quiz) {
		this.id = quiz.getId();
		this.time = quiz.getCreated();
		this.content = quiz.getContent();
		this.user = new UserTips(user);
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

	public UserTips getUser() {
		return user;
	}

	public void setUser(UserTips user) {
		this.user = user;
	}

}
