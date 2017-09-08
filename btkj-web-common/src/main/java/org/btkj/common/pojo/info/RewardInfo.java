package org.btkj.common.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.entity.payment.Account;

public class RewardInfo implements Serializable {

	private static final long serialVersionUID = -3587384138602432887L;

	private int personal;
	private int manage;
	private int scale;
	private int available;
	private int consume;
	
	public RewardInfo(Account account) {
		if (null != account) {
			this.personal = account.getScorePersonal();
			this.manage = account.getScoreManage();
			this.scale = account.getScoreScale();
			this.available = account.getScoreAvailable();
			this.consume = account.getScoreConsume();
		}
	}

	public int getPersonal() {
		return personal;
	}

	public void setPersonal(int personal) {
		this.personal = personal;
	}

	public int getManage() {
		return manage;
	}

	public void setManage(int manage) {
		this.manage = manage;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public int getConsume() {
		return consume;
	}

	public void setConsume(int consume) {
		this.consume = consume;
	}
}
