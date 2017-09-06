package org.btkj.pojo.entity;

import org.btkj.pojo.enums.VehicleOrderState;
import org.btkj.pojo.info.VehicleInfo;
import org.btkj.pojo.info.VehiclePolicyTips;
import org.btkj.pojo.model.BonusPoundage;
import org.btkj.pojo.model.DeliveryInfo;
import org.btkj.pojo.param.VehicleOrderParam;
import org.rapid.util.common.Consts;
import org.rapid.util.common.model.UniqueModel;
import org.rapid.util.lang.DateUtil;

public class VehicleOrder implements UniqueModel<String> {

	private static final long serialVersionUID = 2581269614077147352L;

	// 订单基本信息
	private String _id;
	private int uid;
	private int tid;					// 商户ID
	private int lane; 					// 线路：壁虎、乐宝吧、保途
	private int appId;					// 平台ID
	private int created; 				// 创建时间
	private String desc; 				// 描述
	private int quoteMod;				// 报价模值，用来决定本次报价同时和几家公司一起报价
	private int insureMod;				// 核保模值
	private boolean insure; 			// 是否投保
	private int employeeId;				// 雇员ID
	private String policyId;			// 保单ID
	private String salesman;			// 业务员名字
	private String salesmanMobile;		// 业务员手机号
	private VehicleOrderState state; 	// 保单状态

	// 险企信息
	private int insurerId; 				// 保险公司ID
	private String insurerName; 		// 保险公司名字
	private String insurerIcon; 		// 保险公司Icon
	
	private BonusPoundage bonus;				// 奖励
	private VehiclePolicyTips tips;
	private DeliveryInfo deliveryInfo;	// 配送信息
	
	public VehicleOrder() {}
	
	public VehicleOrder(AppPO app, TenantPO tenant, UserPO user, EmployeePO employee, Insurer insurer, 
			int lane, boolean insure, VehicleOrderParam param, VehicleInfo vehicleInfo) {
		this.lane = lane;
		this.insure = insure;
		this.appId = app.getId();
		this.uid = user.getUid();
		this.tid = tenant.getTid();
		this.salesman = user.getName();
		this.insurerId = insurer.getId();
		this.employeeId = employee.getId();
		this.quoteMod = param.getQuoteMod();
		this.insurerName = insurer.getName();
		this.insurerIcon = insurer.getIcon();
		this.insureMod = param.getInsureMod();
		this.created = DateUtil.currentTime();
		this.salesmanMobile = user.getMobile();
		this.state = VehicleOrderState.QUOTING;
		this.tips = new VehiclePolicyTips(param, vehicleInfo);
		this._id = employee.getId() + Consts.SYMBOL_UNDERLINE + param.getLicense() + Consts.SYMBOL_UNDERLINE + insurer.getId();
	}
	
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
	
	public int getQuoteMod() {
		return quoteMod;
	}

	public void setQuoteMod(int quoteMod) {
		this.quoteMod = quoteMod;
	}
	
	public int getInsureMod() {
		return insureMod;
	}
	
	public void setInsureMod(int insureMod) {
		this.insureMod = insureMod;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getSalesman() {
		return salesman;
	}
	
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	
	public String getSalesmanMobile() {
		return salesmanMobile;
	}
	
	public void setSalesmanMobile(String salesmanMobile) {
		this.salesmanMobile = salesmanMobile;
	}
	
	public int getAppId() {
		return appId;
	}
	
	public void setAppId(int appId) {
		this.appId = appId;
	}
	
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
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

	public VehicleOrderState getState() {
		return state;
	}

	public void setState(VehicleOrderState state) {
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
	
	public BonusPoundage getBonus() {
		return bonus;
	}
	
	public void setBonus(BonusPoundage bonus) {
		this.bonus = bonus;
	}
	
	public DeliveryInfo getDeliveryInfo() {
		return deliveryInfo;
	}
	
	public void setDeliveryInfo(DeliveryInfo deliveryInfo) {
		this.deliveryInfo = deliveryInfo;
	}
	
	public String getPolicyId() {
		return policyId;
	}
	
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
	
	@Override
	public String key() {
		return this._id;
	}
}
