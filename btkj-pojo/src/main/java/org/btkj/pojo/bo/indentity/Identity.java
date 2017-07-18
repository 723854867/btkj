package org.btkj.pojo.bo.indentity;

import java.io.Serializable;

import org.btkj.pojo.enums.Client;

public class Identity implements Serializable {

	private static final long serialVersionUID = -7877302501899876765L;

	protected Client client;					// 客户端类型
	
	public Identity() {}
	
	public Identity(Client client) {
		this.client = client;
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
}
