package org.btkj.master.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.po.Insurer;
import org.btkj.vehicle.pojo.Lane;
import org.btkj.vehicle.pojo.entity.TenantInsurer;

public class RouteInfo implements Serializable {

	private static final long serialVersionUID = -8341734831592750511L;

	private String key;
	private int insurerId;
	private String insurerName;
	private String insurerIcon;
	private int lane;
	private int jianJieId;
	private String laneName;
	private int created;
	private int updated;
	
	public RouteInfo() {}
	
	public RouteInfo(TenantInsurer route, Insurer insurer) {
		this.key = route.getKey();
		this.created = route.getCreated();
		this.updated = route.getUpdated();
		this.lane = route.getLane();
		this.insurerId = insurer.getId();
		this.insurerName = insurer.getName();
		this.insurerIcon = insurer.getIcon();
		this.jianJieId = route.getJianJieId();
		Lane lane = Lane.match(this.lane);
		this.laneName = lane.title();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
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

	public int getLane() {
		return lane;
	}

	public void setLane(int lane) {
		this.lane = lane;
	}
	
	public int getJianJieId() {
		return jianJieId;
	}
	
	public void setJianJieId(int jianJieId) {
		this.jianJieId = jianJieId;
	}

	public String getLaneName() {
		return laneName;
	}

	public void setLaneName(String laneName) {
		this.laneName = laneName;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	public int getUpdated() {
		return updated;
	}

	public void setUpdated(int updated) {
		this.updated = updated;
	}
}
