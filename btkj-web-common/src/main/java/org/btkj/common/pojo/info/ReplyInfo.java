package org.btkj.common.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.entity.community.Reply;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.info.UserTips;

public class ReplyInfo implements Serializable {

	private static final long serialVersionUID = 4964035714874266655L;

	private int id;
	private int time;
	private UserTips user;
	private String content;
	
	public ReplyInfo(UserPO user, Reply reply) {
		this.id = reply.getId();
		this.time = reply.getCreated();
		this.content = reply.getContent();
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
