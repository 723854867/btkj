package org.btkj.bihu.vehicle.pojo.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.btkj.pojo.param.Param;
import org.rapid.util.common.enums.CRUD_TYPE;
import org.rapid.util.validator.ValidateGroups;

public class TenantConfigEditParam extends Param {

	private static final long serialVersionUID = 5646762425017609544L;

	private CRUD_TYPE type;
	@Min(value = 1, groups = { ValidateGroups.CRUD.class })
	private int tid;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	private String agent;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	private String key;
	
	public CRUD_TYPE getType() {
		return type;
	}
	
	public void setType(CRUD_TYPE type) {
		this.type = type;
	}
	
	public int getTid() {
		return tid;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
	}
	
	public String getAgent() {
		return agent;
	}
	
	public void setAgent(String agent) {
		this.agent = agent;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
}
