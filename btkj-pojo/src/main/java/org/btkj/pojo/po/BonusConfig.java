package org.btkj.pojo.po;

import org.btkj.pojo.bo.BonusRouteBody;
import org.rapid.util.Node;
import org.rapid.util.common.Consts;
import org.rapid.util.common.model.UniqueModel;

public class BonusConfig extends Node<BonusRouteBody> implements UniqueModel<String> {

	private static final long serialVersionUID = -7988999600328702481L;
	
	private int tid;
	private String _id;
	private int insurerId;
	
	public BonusConfig() {}
	
	public BonusConfig(int tid, int insurerId) {
		this.tid = tid;
		this.insurerId = insurerId;
		this._id = tid + Consts.SYMBOL_UNDERLINE + insurerId;
	}
	
	public int getTid() {
		return tid;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
	}
	
	public String get_id() {
		return _id;
	}
	
	public void set_id(String _id) {
		this._id = _id;
	}
	
	public int getInsurerId() {
		return insurerId;
	}
	
	public void setInsurerId(int insurerId) {
		this.insurerId = insurerId;
	}

	@Override
	public String key() {
		return this._id;
	}
}
