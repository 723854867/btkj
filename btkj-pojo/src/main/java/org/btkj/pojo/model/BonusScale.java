package org.btkj.pojo.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.rapid.util.common.model.UniqueModel;
import org.rapid.util.lang.DateUtil;

public class BonusScale implements UniqueModel<String> {

	private static final long serialVersionUID = -5665893334528543094L;

	private String _id;
	private int employeeId;
	private int tid;
	private int quota;
	private int SCCMQuota;
	private int SCCPQuota;
	private int RCCMQuota;
	private int RCCPQuota;
	private int cpRate; // 交强险比例
	private int cmRate; // 商业险比例
	private State state; // 状态
	private Set<String> policies;
	private int created;
	private int updated;

	public enum State {
		AUDIT, AGREE, REJECT;
	}

	public BonusScale() {
	}

	public BonusScale(String key) {
		this._id = key;
		this.created = DateUtil.currentTime();
		this.updated = created;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public int getSCCMQuota() {
		return SCCMQuota;
	}

	public void setSCCMQuota(int sCCMQuota) {
		SCCMQuota = sCCMQuota;
	}

	public int getSCCPQuota() {
		return SCCPQuota;
	}

	public void setSCCPQuota(int sCCPQuota) {
		SCCPQuota = sCCPQuota;
	}

	public int getRCCMQuota() {
		return RCCMQuota;
	}

	public void setRCCMQuota(int rCCMQuota) {
		RCCMQuota = rCCMQuota;
	}

	public int getRCCPQuota() {
		return RCCPQuota;
	}

	public void setRCCPQuota(int rCCPQuota) {
		RCCPQuota = rCCPQuota;
	}

	public int getCmRate() {
		return cmRate;
	}

	public void setCmRate(int cmRate) {
		this.cmRate = cmRate;
	}

	public int getCpRate() {
		return cpRate;
	}

	public void setCpRate(int cpRate) {
		this.cpRate = cpRate;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Set<String> getPolicies() {
		return policies;
	}

	public void setPolicies(Set<String> policies) {
		this.policies = policies;
	}

	public int getUpdated() {
		return updated;
	}

	public void setUpdated(int updated) {
		this.updated = updated;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	public void addPolicy(String id) {
		if (null == this.policies)
			this.policies = new HashSet<String>();
		this.policies.add(id);
	}

	public void addPolicies(Collection<String> collection) {
		if (null == this.policies)
			this.policies = new HashSet<String>();
		this.policies.addAll(collection);
	}

	@Override
	public String key() {
		return this._id;
	}
}
