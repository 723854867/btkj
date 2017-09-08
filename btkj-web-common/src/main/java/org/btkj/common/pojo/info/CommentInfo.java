package org.btkj.common.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.entity.community.Comment;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.info.UserTips;

public class CommentInfo implements Serializable {

	private static final long serialVersionUID = -502812163244423783L;

	private int id;
	private int time;
	private UserTips user;
	private String content;
	
	public CommentInfo(UserPO user, Comment comment) {
		this.id = comment.getId();
		this.time = comment.getCreated();
		this.content = comment.getContent();
		if (null != user)
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
