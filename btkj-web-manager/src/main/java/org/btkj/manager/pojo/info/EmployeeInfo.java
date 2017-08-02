package org.btkj.manager.pojo.info;

import java.io.Serializable;

import org.btkj.payment.pojo.entity.Account;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.UserPO;
import org.btkj.pojo.vo.EmployeeTip;

public class EmployeeInfo implements Serializable {

	private static final long serialVersionUID = 4323256822665484903L;

	private String name;
	private int employeeId;
	private String identity;
	private String identityFace;
	private String identityBack;
	private String mobile;
	private int created;
	private int parentId;
	private String parentName;
	private int score;
	private int scoreTotal;
	private int scoreFrozen;
	private int scoreConsume;
	private int scoreManage;
	private int scoreScale;
	private int mod;
	private int CMRate;
	private int CPRate;

	public EmployeeInfo(UserPO user, EmployeePO employee, EmployeeTip parent, Account account) {
		this.name = user.getName();
		this.employeeId = employee.getId();
		this.identity = user.getIdentity();
		this.identityFace = user.getIdentityFace();
		this.identityBack = user.getIdentityBack();
		this.mobile = user.getMobile();
		this.parentId = employee.getParentId();
		this.parentName = null == parent ? null : parent.getName();
		this.mod = employee.getMod();
		this.CMRate = employee.getCommercialRate();
		this.CPRate = employee.getCompulsoryRate();
		this.created = employee.getCreated();
		if (null != account) {
			this.score = account.getScoreAvailable();
			this.scoreTotal = account.getScorePersonal();
			this.scoreFrozen = account.getScoreFrozen();
			this.scoreConsume = account.getScoreConsume();
			this.scoreManage = account.getScoreManage();
			this.scoreScale = account.getScoreScale();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getIdentityFace() {
		return identityFace;
	}

	public void setIdentityFace(String identityFace) {
		this.identityFace = identityFace;
	}

	public String getIdentityBack() {
		return identityBack;
	}

	public void setIdentityBack(String identityBack) {
		this.identityBack = identityBack;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScoreTotal() {
		return scoreTotal;
	}

	public void setScoreTotal(int scoreTotal) {
		this.scoreTotal = scoreTotal;
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

	public int getMod() {
		return mod;
	}

	public void setMod(int mod) {
		this.mod = mod;
	}

	public int getCMRate() {
		return CMRate;
	}

	public void setCMRate(int cMRate) {
		CMRate = cMRate;
	}

	public int getCPRate() {
		return CPRate;
	}

	public void setCPRate(int cPRate) {
		CPRate = cPRate;
	}
}
