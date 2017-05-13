package org.btkj.pojo.entity;

import org.rapid.util.common.model.UniqueModel;

/**
 * 自留佣金设置表
 * @author sj
 */
public class SpecialCommission implements UniqueModel<Integer> {

	private static final long serialVersionUID = -315073031541553807L;

	private int id;
	private int eid;					//雇员ID
	private int noBusinessCar;		//非营业客车 1勾选
	private int noBusinessTruck;		//非营业货车 1勾选
	private int businessCar;			//营业客车     1勾选
	private int businessTruck;			//营业货车     1勾选
	private int takeEffectTime;		//生效时间
	private float commercialInsuranceRatio; //商业险比例
	private int commercialInsuranceType;	  //商业险比例：0减，1加
	private float compulsoryInsuranceRatio; //交强险比例
	private int compulsoryInsuranceType;    //交强险比例：0减，1加
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



	public int getTakeEffectTime() {
		return takeEffectTime;
	}



	public void setTakeEffectTime(int takeEffectTime) {
		this.takeEffectTime = takeEffectTime;
	}



	public float getCommercialInsuranceRatio() {
		return commercialInsuranceRatio;
	}



	public void setCommercialInsuranceRatio(float commercialInsuranceRatio) {
		this.commercialInsuranceRatio = commercialInsuranceRatio;
	}



	public int getCommercialInsuranceType() {
		return commercialInsuranceType;
	}



	public void setCommercialInsuranceType(int commercialInsuranceType) {
		this.commercialInsuranceType = commercialInsuranceType;
	}



	public float getCompulsoryInsuranceRatio() {
		return compulsoryInsuranceRatio;
	}



	public void setCompulsoryInsuranceRatio(float compulsoryInsuranceRatio) {
		this.compulsoryInsuranceRatio = compulsoryInsuranceRatio;
	}



	public int getCompulsoryInsuranceType() {
		return compulsoryInsuranceType;
	}



	public void setCompulsoryInsuranceType(int compulsoryInsuranceType) {
		this.compulsoryInsuranceType = compulsoryInsuranceType;
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
