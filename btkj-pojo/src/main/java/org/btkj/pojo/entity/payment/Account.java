package org.btkj.pojo.entity.payment;

import org.rapid.util.common.model.UniqueModel;

public class Account implements UniqueModel<Integer> {

	private static final long serialVersionUID = 8272783970593831487L;

	private int employeeId;
	private int scoreAvailable;
	private int scoreFrozen;
	private int scoreConsume;
	private int scorePersonal;
	private int scoreManage;
	private int scoreScale;
	private int created;
	private int updated;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getScoreAvailable() {
		return scoreAvailable;
	}

	public void setScoreAvailable(int scoreAvailable) {
		this.scoreAvailable = scoreAvailable;
	}

	public int getScoreFrozen() {
		return scoreFrozen;
	}

	public void setScoreFrozen(int scoreFrozen) {
		this.scoreFrozen = scoreFrozen;
	}
	
	public int getScoreConsume() {
		return scoreConsume;
	}
	
	public void setScoreConsume(int scoreConsume) {
		this.scoreConsume = scoreConsume;
	}

	public int getScorePersonal() {
		return scorePersonal;
	}

	public void setScorePersonal(int scorePersonal) {
		this.scorePersonal = scorePersonal;
	}

	public int getScoreManage() {
		return scoreManage;
	}

	public void setScoreManage(int scoreManage) {
		this.scoreManage = scoreManage;
	}

	public int getScoreScale() {
		return scoreScale;
	}

	public void setScoreScale(int scoreScale) {
		this.scoreScale = scoreScale;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	public int getUpdated() {
		return updated;
	}

	public void setUpdated(int updated) {
		this.updated = updated;
	}

	@Override
	public Integer key() {
		return employeeId;
	}
}
