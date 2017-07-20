package org.btkj.pojo.po;

import java.util.Map;

import org.btkj.pojo.bo.InsurUnit;
import org.btkj.pojo.bo.Insurance;
import org.btkj.pojo.enums.CommercialInsuranceType;
import org.btkj.pojo.enums.VehicleUsedType;
import org.btkj.pojo.vo.JianJiePoliciesInfo.BaseInfo;
import org.btkj.pojo.vo.VehiclePolicyTips;
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
	private int renewalType;						// 转续保类型
	private boolean transfer;						// 是否过户车
	private VehicleUsedType usedType;				// 使用性质
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
	private Map<CommercialInsuranceType, Insurance> insurances;
	
	public VehiclePolicy() {}
	
	public VehiclePolicy(VehicleOrder order, BaseInfo commercial, BaseInfo compulsory) {
		this.tid = order.getTid();
		this.insurerId = order.getInsurerId();
		VehiclePolicyTips tips = order.getTips();
		
		InsurUnit owner = tips.getOwner();
		this.owner = owner.getName();
		this.idNo = owner.getIdNo();
		
		this.issueDate = tips.getIssueDate();
		this.enrollDate = tips.getEnrollDate();
		this.license = tips.getLicense();
		this.engine = tips.getEngine();
		this.vin = tips.getVin();
		this.name = tips.getName();
		this.seat = tips.getSeat();
	}
	
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

	public int getRenewalType() {
		return renewalType;
	}

	public void setRenewalType(int renewalType) {
		this.renewalType = renewalType;
	}

	public boolean isTransfer() {
		return transfer;
	}

	public void setTransfer(boolean transfer) {
		this.transfer = transfer;
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

	public VehicleUsedType getUsedType() {
		return usedType;
	}

	public void setUsedType(VehicleUsedType usedType) {
		this.usedType = usedType;
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
