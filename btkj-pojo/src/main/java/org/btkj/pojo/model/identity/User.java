package org.btkj.pojo.model.identity;

import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.enums.Client;

public class User extends Identity {

	private static final long serialVersionUID = -3257275276324329323L;

	private AppPO app;
	private UserPO entity;
	
	public User() {}
	
	public User(AppPO app, UserPO user) {
		this.app = app;
		this.entity = user;
	}
	
	public User(Client client, AppPO app, UserPO user) {
		super(client);
		this.app = app;
		this.entity = user;
	}
	
	public int getUid() {
		return entity.getUid();
	}
	
	public String getName() {
		return entity.getName();
	}
	
	public int getAppId() {
		return entity.getAppId();
	}
	
	public String getPwd() {
		return entity.getPwd();
	}
	
	public int region() {
		return app.getRegion();
	}
	
	public int maxArticleCount() {
		return app.getMaxArticlesCount();
	}
	
	public AppPO getApp() {
		return app;
	}
	
	public UserPO getEntity() {
		return entity;
	}
}
