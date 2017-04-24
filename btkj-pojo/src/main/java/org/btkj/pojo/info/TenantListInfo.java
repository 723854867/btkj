package org.btkj.pojo.info;

import java.io.Serializable;
import java.util.List;

import org.btkj.pojo.info.tips.TenantTips;

public class TenantListInfo implements Serializable {

	private static final long serialVersionUID = -7737232217221256836L;
	
	private TenantTips main;				// 主企业
	private List<TenantTips> owns;			// 已经拥有的企业
	private List<TenantTips> audit;			// 正在审核的企业
	
	public TenantTips getMain() {
		return main;
	}
	
	public void setMain(TenantTips main) {
		this.main = main;
	}
	
	public List<TenantTips> getOwns() {
		return owns;
	}
	
	public void setOwns(List<TenantTips> owns) {
		this.owns = owns;
	}
	
	public List<TenantTips> getAudit() {
		return audit;
	}
	
	public void setAudit(List<TenantTips> audit) {
		this.audit = audit;
	}
}
