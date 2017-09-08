package org.btkj.pojo.model.identity;

import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.enums.Client;

public class App extends Identity {

	private static final long serialVersionUID = 712257043568111367L;

	private AppPO entity;

	public App() {}

	public App(AppPO entity, Client client) {
		super(client);
		this.entity = entity;
	}

	public int getId() {
		return entity.getId();
	}

	public String getName() {
		return entity.getName();
	}

	public AppPO getEntity() {
		return entity;
	}

	public void setEntity(AppPO entity) {
		this.entity = entity;
	}
}
