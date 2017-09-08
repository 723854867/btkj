package org.btkj.pojo.param.vehicle;

import java.util.Map;
import java.util.Set;

import javax.validation.constraints.Min;

import org.btkj.pojo.entity.vehicle.TenantInsurer;
import org.btkj.pojo.param.Param;

public class TenantSetParam extends Param {
	
	private static final long serialVersionUID = 5486126052550356258L;
	
	@Min(1)
	private int tid;
	private String jianJieId;
	private String biHuKey;
	private String biHuAgent;
	private String leBaoBaUsername;
	private String leBaoBaPassword;
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
	
	public String getBiHuKey() {
		return biHuKey;
	}
	
	public void setBiHuKey(String biHuKey) {
		this.biHuKey = biHuKey;
	}
	
	public String getBiHuAgent() {
		return biHuAgent;
	}
	
	public String getLeBaoBaPassword() {
		return leBaoBaPassword;
	}
	
	public void setLeBaoBaPassword(String leBaoBaPassword) {
		this.leBaoBaPassword = leBaoBaPassword;
	}

	public String getLeBaoBaUsername() {
		return leBaoBaUsername;
	}


	public void setLeBaoBaUsername(String leBaoBaUsername) {
		this.leBaoBaUsername = leBaoBaUsername;
	}
	
	public void setBiHuAgent(String biHuAgent) {
		this.biHuAgent = biHuAgent;
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