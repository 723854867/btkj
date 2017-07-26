package org.btkj.manager.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.bo.Bonus;
import org.btkj.pojo.enums.VehicleOrderState;
import org.btkj.pojo.po.UserPO;
import org.btkj.pojo.po.VehicleOrder;

public class VehicleOrderListInfo implements Serializable {

	private static final long serialVersionUID = 7112816273407951408L;

	private int uid;
	private int employeeId;
	private String userName;
	private String userMobile;
	private String id;
	private VehicleOrderState state; // 保单状态
	private String desc; // 描述
	private int created; // 创建时间
	private int insurerId; // 保险公司ID
	private String insurerName; // 保险公司名字
	private String insurerIcon; // 保险公司Icon
	private String owner;
	private String license;
	private double bonus;
	private double price;

	public VehicleOrderListInfo(UserPO user, VehicleOrder order) {
		this.uid = order.getUid();
		this.employeeId = order.getEmployeeId();
		this.id = order.get_id();
		this.state = order.getState();
		this.desc = order.getDesc();
		this.created = order.getCreated();
		this.insurerId = order.getInsurerId();
		this.insurerName = order.getInsurerName();
		this.insurerIcon = order.getInsurerIcon();
		this.owner = order.getTips().getOwner().getName();
		this.license = order.getTips().getLicense();
		Bonus bonus = order.getBonus();
		this.bonus = null != bonus ? bonus.getCommercialBonus() + bonus.getCompulsoryBonus() : 0;
		if (state == VehicleOrderState.QUOTE_SUCCESS || state == VehicleOrderState.INSURE_FAILURE
				|| state == VehicleOrderState.INSURE_SUCCESS) {
			this.price = order.getTips().getSchema().getCommericalTotal()
					+ order.getTips().getSchema().getCompulsiveTotal()
					+ order.getTips().getSchema().getVehicleVesselTotal();
		}
		if (null != user) {
			this.userName = user.getName();
			this.userMobile = user.getMobile();
		}
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}