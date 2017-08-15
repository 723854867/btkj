package org.btkj.master.pojo.param;

import java.util.Map;
import java.util.Set;

import javax.validation.constraints.Min;

import org.btkj.pojo.param.Param;
import org.btkj.vehicle.pojo.entity.TenantInsurer;

public class TenantSetParam extends Param {
	
	private static final long serialVersionUID = 5486126052550356258L;
	
	@Min(1)
	private int tid;
	private String jianJieId;
	private String bihuKey;
	private String bihuAgent;
	private Set<String> insurersDelete;
	private Map<String, TenantInsurer> insurersUpdate;
	private Map<String, TenantInsurer> insurersInsert;
	
	public int getTid() {
		return tid;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
	}
	
	public String getJianJieId() {
		return jianJieId;
	}
	
	public void setJianJieId(String jianJieId) {
		this.jianJieId = jianJieId;
	}
	
	public String getBihuKey() {
		return bihuKey;
	}
	
	public void setBihuKey(String bihuKey) {
		this.bihuKey = bihuKey;
	}
	
	public String getBihuAgent() {
		return bihuAgent;
	}
	
	public void setBihuAgent(String bihuAgent) {
		this.bihuAgent = bihuAgent;
	}
	
	public Set<String> getInsurersDelete() {
		return insurersDelete;
	}
	
	public void setInsurersDelete(Set<String> insurersDelete) {
		this.insurersDelete = insurersDelete;
	}
	
	public Map<String, TenantInsurer> getInsurersInsert() {
		return insurersInsert;
	}
	
	public void setInsurersInsert(Map<String, TenantInsurer> insurersInsert) {
		this.insurersInsert = insurersInsert;
	}
	
	public Map<String, TenantInsurer> getInsurersUpdate() {
		return insurersUpdate;
	}
	
	public void setInsurersUpdate(Map<String, TenantInsurer> insurersUpdate) {
		this.insurersUpdate = insurersUpdate;
	}
}
