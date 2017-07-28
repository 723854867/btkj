package org.btkj.pojo.po;

import org.btkj.pojo.bo.Bonus;
import org.btkj.pojo.bo.DeliveryInfo;
import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.enums.VehicleOrderState;
import org.btkj.pojo.vo.VehiclePolicyTips;
import org.rapid.util.common.Consts;
import org.rapid.util.common.model.UniqueModel;
import org.rapid.util.lang.DateUtil;

public class VehicleOrder implements UniqueModel<String> {

	private static final long serialVersionUID = 2581269614077147352L;

	// 订单基本信息
	private String _id;
	private String batchId;				// 批次号，一次报价可以对多家公司进行报价、多个报价共用一个批次号
	private int quoteMod;				// 报价模值，用来决定本次报价同时和几家公司一起报价
	private int insureMod;				// 核保模值
	private int appId;					// 平台ID
	private int uid;
	private int tid;					// 商户ID
	private int employeeId;				// 雇员ID
	private String salesman;			// 业务员名字
	private String salesmanMobile;		// 业务员手机号
	private int lane; 					// 线路：壁虎、乐宝吧、保途
	private boolean insure; 			// 是否投保
	private String desc; 				// 描述
	private int created; 				// 创建时间
	private VehicleOrderState state; 	// 保单状态
	private String policyId;			// 保单ID

	// 险企信息
	private int insurerId; 				// 保险公司ID
	private String insurerName; 		// 保险公司名字
	private String insurerIcon; 		// 保险公司Icon
	
	private Bonus bonus;				// 奖励
	private VehiclePolicyTips tips;
	private DeliveryInfo deliveryInfo;	// 配送信息
	
	public VehicleOrder() {}
	
	public VehicleOrder(String batchId, int quoteMod, int insureMod, Employee employee, Insurer insurer, int lane, boolean insure, VehiclePolicyTips tips) {
		this._id = _orderId(tips.getLicense(), employee, insurer);
		this.batchId = batchId;
		this.quoteMod = quoteMod;
		this.insureMod = insureMod;
		this.employeeId = employee.getId();
		this.appId = employee.getAppId();
		this.tid = employee.getTid();
		this.uid = employee.getUid();
		this.insurerId = insurer.getId();
		this.insurerName = insurer.getName();
		this.insurerIcon = insurer.getIcon();
		this.lane = lane;
		this.insure = insure;
		this.state = VehicleOrderState.QUOTING;
		this.tips = tips;
		this.created = DateUtil.currentTime();
		this.salesman = employee.getUser().getName();
		this.salesmanMobile = employee.getUser().getMobile();
	}
	
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
	
	public String getBatchId() {
		return batchId;
	}
	
	public void setBatchId(String batchId) {
		this.batchId = batchId;
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
	
	public Bonus getBonus() {
		return bonus;
	}
	
	public void setBonus(Bonus bonus) {
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

	private String _orderId(String license, Employee employee, Insurer insurer) {
		return employee.getId() + Consts.SYMBOL_UNDERLINE + license + Consts.SYMBOL_UNDERLINE + insurer.getId();
	}
}
