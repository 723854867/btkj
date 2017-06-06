package org.btkj.common.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.entity.Comment;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.tips.UserTips;

public class CommentInfo implements Serializable {

	private static final long serialVersionUID = -502812163244423783L;

	private int id;
	private int time;
	private UserTips user;
	private String content;
	
	public CommentInfo(User user, Comment comment) {
		this.id = comment.getId();
		this.user = new UserTips(user);
		this.time = comment.getCreated();
		this.content = comment.getContent();
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
	
	public UserTips getUser() {
		return user;
	}
	
	public void setUser(UserTips user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
