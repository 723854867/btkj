package org.btkj.vehicle.pojo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.btkj.pojo.VehicleUtil;
import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.pojo.enums.VehicleOrderState;
import org.btkj.pojo.model.Bonus;

public class VehicleOrderListInfo implements Serializable {

	private static final long serialVersionUID = 4559129312908140408L;

	private String id;
	private VehicleOrderState state; 			// 保单状态
	private String desc; 				// 描述
	private int created; 				// 创建时间
	private int insurerId; 				// 保险公司ID
	private String insurerName; 		// 保险公司名字
	private String insurerIcon; 		// 保险公司Icon
	private String owner;
	private String license;
	private String bonus;
	private String price;
	
	public VehicleOrderListInfo() {}
	
	public VehicleOrderListInfo(VehicleOrder order) {
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
		this.bonus = null != bonus ?  new BigDecimal(bonus.getCommercialBonus()).add(new BigDecimal(bonus.getCompulsoryBonus())).setScale(2, RoundingMode.HALF_UP).toString() : "0";
		if (state.mark() > VehicleOrderState.QUOTE_FAILURE.mark())
			this.price = VehicleUtil.totalPremium(order);
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
	
	public String getBonus() {
		return bonus;
	}
	
	public void setBonus(String bonus) {
		this.bonus = bonus;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
