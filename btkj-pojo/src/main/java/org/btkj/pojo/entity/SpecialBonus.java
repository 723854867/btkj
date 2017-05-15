package org.btkj.pojo.entity;

import org.rapid.util.common.model.UniqueModel;

/**
 * 自留佣金设置表
 * @author sj
 */
public class SpecialBonus implements UniqueModel<Integer> {

	private static final long serialVersionUID = -315073031541553807L;

	private int id;
	private int eid;					//雇员ID
	private int noBusinessCar;		//非营业客车 1勾选
	private int noBusinessTruck;		//非营业货车 1勾选
	private int businessCar;			//营业客车     1勾选
	private int businessTruck;			//营业货车     1勾选
	private int startTime;		//生效时间
	private float vciRatio; //商业险比例
	private int vciType;	  //商业险比例：0减，1加
	private float tciRatio; //交强险比例
	private int tciType;    //交强险比例：0减，1加
	private int created;
	private int updated;

	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getEid() {
		return eid;
	}



	public void setEid(int eid) {
		this.eid = eid;
	}

	public int getNoBusinessCar() {
		return noBusinessCar;
	}



	public void setNoBusinessCar(int noBusinessCar) {
		this.noBusinessCar = noBusinessCar;
	}



	public int getNoBusinessTruck() {
		return noBusinessTruck;
	}



	public void setNoBusinessTruck(int noBusinessTruck) {
		this.noBusinessTruck = noBusinessTruck;
	}



	public int getBusinessCar() {
		return businessCar;
	}



	public void setBusinessCar(int businessCar) {
		this.businessCar = businessCar;
	}



	public int getBusinessTruck() {
		return businessTruck;
	}



	public void setBusinessTruck(int businessTruck) {
		this.businessTruck = businessTruck;
	}


	public int getStartTime() {
		return startTime;
	}



	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	
	public float getVciRatio() {
		return vciRatio;
	}



	public void setVciRatio(float vciRatio) {
		this.vciRatio = vciRatio;
	}



	public int getVciType() {
		return vciType;
	}



	public void setVciType(int vciType) {
		this.vciType = vciType;
	}



	public float getTciRatio() {
		return tciRatio;
	}



	public void setTciRatio(float tciRatio) {
		this.tciRatio = tciRatio;
	}



	public int getTciType() {
		return tciType;
	}



	public void setTciType(int tciType) {
		this.tciType = tciType;
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



	@Override
	public Integer key() {
		return id;
	}
}
