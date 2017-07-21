package org.btkj.vehicle.pojo.entity;

import java.util.Map;

import org.btkj.pojo.bo.Insurance;
import org.btkj.pojo.enums.BonusScaleType;
import org.btkj.pojo.enums.CommercialInsuranceType;
import org.btkj.pojo.enums.PolicyNature;
import org.rapid.util.common.model.UniqueModel;

public class VehiclePolicy implements UniqueModel<String> {

	private static final long serialVersionUID = -7788126900244400122L;

	private String _id;								
	private int tid;								// 商户ID
	private int insurerId;							// 险企ID
	private String owner;							// 车主姓名
	private String idNo;							// 车主证件号
	private String issueDate;						// 发证日期
	private String enrollDate;						// 初登日期
	private String license;							// 车牌号
	private String engine;							// 发动机号
	private String vin;								// 车架号
	private String name;							// 厂牌型号
	private int seat;								// 座位数
	private PolicyNature nature;					// 转续保类型
	private boolean transfer;						// 是否过户车
	private BonusScaleType scaleType;				// 规模奖励类型
	private double vehiclePrice;					// 购置价
	private String salesmanMobile;					// 业务员手机号
	private double vesselPrice;						// 车船税
	private double commercialPrice;					// 商业险保费	
	private double compulsoryPrice;					// 交强险保费
	private String commercialNo;					// 商业险保单号
	private String commercialDeliverNo;				// 商业险投保单号
	private String compulsoryNo;					// 交强险保单号
	private String compulsoryDeliverNo;				// 交强险投保单号
	private String commercialStartDate;
	private String compulsoryStartDate;
	private String commercialIssueDate;
	private String compulsoryIssueDate;
	private Map<CommercialInsuranceType, Insurance> insurances;
	
	public String get_id() {
		return _id;
	}
	
	public void set_id(String _id) {
		this._id = _id;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getInsurerId() {
		return insurerId;
	}

	public void setInsurerId(int insurerId) {
		this.insurerId = insurerId;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public PolicyNature getNature() {
		return nature;
	}

	public void setNature(PolicyNature nature) {
		this.nature = nature;
	}

	public boolean isTransfer() {
		return transfer;
	}

	public void setTransfer(boolean transfer) {
		this.transfer = transfer;
	}

	public BonusScaleType getScaleType() {
		return scaleType;
	}

	public void setScaleType(BonusScaleType scaleType) {
		this.scaleType = scaleType;
	}

	public double getVehiclePrice() {
		return vehiclePrice;
	}

	public void setVehiclePrice(double vehiclePrice) {
		this.vehiclePrice = vehiclePrice;
	}

	public String getSalesmanMobile() {
		return salesmanMobile;
	}

	public void setSalesmanMobile(String salesmanMobile) {
		this.salesmanMobile = salesmanMobile;
	}

	public double getVesselPrice() {
		return vesselPrice;
	}

	public void setVesselPrice(double vesselPrice) {
		this.vesselPrice = vesselPrice;
	}

	public double getCommercialPrice() {
		return commercialPrice;
	}

	public void setCommercialPrice(double commercialPrice) {
		this.commercialPrice = commercialPrice;
	}

	public double getCompulsoryPrice() {
		return compulsoryPrice;
	}

	public void setCompulsoryPrice(double compulsoryPrice) {
		this.compulsoryPrice = compulsoryPrice;
	}

	public String getCommercialNo() {
		return commercialNo;
	}

	public void setCommercialNo(String commercialNo) {
		this.commercialNo = commercialNo;
	}

	public String getCommercialDeliverNo() {
		return commercialDeliverNo;
	}

	public void setCommercialDeliverNo(String commercialDeliverNo) {
		this.commercialDeliverNo = commercialDeliverNo;
	}

	public String getCompulsoryNo() {
		return compulsoryNo;
	}

	public void setCompulsoryNo(String compulsoryNo) {
		this.compulsoryNo = compulsoryNo;
	}

	public String getCompulsoryDeliverNo() {
		return compulsoryDeliverNo;
	}

	public void setCompulsoryDeliverNo(String compulsoryDeliverNo) {
		this.compulsoryDeliverNo = compulsoryDeliverNo;
	}

	public String getCommercialStartDate() {
		return commercialStartDate;
	}

	public void setCommercialStartDate(String commercialStartDate) {
		this.commercialStartDate = commercialStartDate;
	}

	public String getCompulsoryStartDate() {
		return compulsoryStartDate;
	}

	public void setCompulsoryStartDate(String compulsoryStartDate) {
		this.compulsoryStartDate = compulsoryStartDate;
	}
	
	public String getCommercialIssueDate() {
		return commercialIssueDate;
	}
	
	public void setCommercialIssueDate(String commercialIssueDate) {
		this.commercialIssueDate = commercialIssueDate;
	}
	
	public String getCompulsoryIssueDate() {
		return compulsoryIssueDate;
	}
	
	public void setCompulsoryIssueDate(String compulsoryIssueDate) {
		this.compulsoryIssueDate = compulsoryIssueDate;
	}
	
	public Map<CommercialInsuranceType, Insurance> getInsurances() {
		return insurances;
	}

	public void setInsurances(Map<CommercialInsuranceType, Insurance> insurances) {
		this.insurances = insurances;
	}

	@Override
	public String key() {
		return _id;
	}
}
