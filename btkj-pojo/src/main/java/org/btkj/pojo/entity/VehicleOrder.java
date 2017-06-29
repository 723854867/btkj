package org.btkj.pojo.entity;

import org.btkj.pojo.enums.PolicyState;
import org.btkj.pojo.info.tips.VehiclePolicyTips;
import org.btkj.pojo.model.insur.vehicle.Bonus;
import org.rapid.util.common.model.UniqueModel;
import org.rapid.util.lang.DateUtils;

public class VehicleOrder implements UniqueModel<String> {

	private static final long serialVersionUID = 2581269614077147352L;

	// 订单基本信息
	private String _id;
	private String batchId;				// 批次号，一次报价可以对多家公司进行报价、多个报价共用一个批次号
	private int appId;					// 平台ID
	private int tid;					// 商户ID
	private int lane; 					// 线路：壁虎、乐宝吧、保途
	private boolean insure; 			// 是否投保
	private PolicyState state; 			// 保单状态
	private String desc; 				// 描述
	private int created; 				// 创建时间

	// 险企信息
	private int insurerId; 				// 保险公司ID
	private String insurerName; 		// 保险公司名字
	private String insurerIcon; 		// 保险公司Icon
	
	private VehiclePolicyTips tips;
	private Bonus bonus;				// 奖励
	
	public VehicleOrder() {}
	
	public VehicleOrder(String id, String batchId, int appId, int tid, Insurer insurer, int lane, boolean insure, VehiclePolicyTips tips) {
		this._id = id;
		this.batchId = batchId;
		this.appId = appId;
		this.tid = tid;
		this.insurerId = insurer.getId();
		this.insurerName = insurer.getName();
		this.insurerIcon = insurer.getIcon();
		this.lane = lane;
		this.insure = insure;
		this.state = PolicyState.NEW;
		this.tips = tips;
		this.created = DateUtils.currentTime();
	}
	
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
	
	public int getAppId() {
		return appId;
	}
	
	public void setAppId(int appId) {
		this.appId = appId;
	}
	
	public int getTid() {
		return tid;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getLane() {
		return lane;
	}

	public void setLane(int lane) {
		this.lane = lane;
	}

	public boolean isInsure() {
		return insure;
	}

	public void setInsure(boolean insure) {
		this.insure = insure;
	}

	public PolicyState getState() {
		return state;
	}

	public void setState(PolicyState state) {
		this.state = state;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	public int getInsurerId() {
		return insurerId;
	}

	public void setInsurerId(int insurerId) {
		this.insurerId = insurerId;
	}

	public String getInsurerName() {
		return insurerName;
	}

	public void setInsurerName(String insurerName) {
		this.insurerName = insurerName;
	}

	public String getInsurerIcon() {
		return insurerIcon;
	}

	public void setInsurerIcon(String insurerIcon) {
		this.insurerIcon = insurerIcon;
	}
	
	public VehiclePolicyTips getTips() {
		return tips;
	}
	
	public void setTips(VehiclePolicyTips tips) {
		this.tips = tips;
	}
	
	public Bonus getBonus() {
		return bonus;
	}
	
	public void setBonus(Bonus bonus) {
		this.bonus = bonus;
	}
	
	public String commisionRoutePath() {
		return null;
	}

	@Override
	public String key() {
		return this._id;
	}
}
