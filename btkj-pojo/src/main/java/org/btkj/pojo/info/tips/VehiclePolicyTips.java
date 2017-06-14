package org.btkj.pojo.info.tips;

import java.io.Serializable;

import org.btkj.pojo.enums.VehicleType;
import org.btkj.pojo.enums.VehicleUsedType;
import org.btkj.pojo.model.insur.vehicle.InsurUnit;
import org.btkj.pojo.model.insur.vehicle.PolicyDetail;
import org.btkj.pojo.model.insur.vehicle.PolicySchema;

/**
 * 保单基本信息
 * 
 * @author ahab
 */
public class VehiclePolicyTips implements Serializable {

	private static final long serialVersionUID = -108775801806960634L;
	
	// 车辆信息
	private String license;						// 车牌号
	private String vin;							// 车架号
	private String engine;						// 发动机号
	private String model; 						// 厂牌型号
	private VehicleType vehicleType;			// 车辆类型
	private VehicleUsedType vehicleUsedType;	// 使用性质
	private String enrollDate; 					// 初登日期
	private int seatCount;						// 座位数
	private String issueDate; 					// 发证日期
	private boolean transfer;					// 是否过户
	private double price;						// 新车购置价
	private String exhaust;						// 排量
	private String load;						// 核定载质量
	
	// 主体信息
	private InsurUnit owner;					// 车主信息
	private InsurUnit insurer;					// 投保人信息
	private InsurUnit insured;					// 被保人信息
	
	// 保险信息
	private PolicySchema schema;
	private PolicyDetail detail;
	
	public String getLicense() {
		return license;
	}
	
	public void setLicense(String license) {
		this.license = license;
	}
	
	public String getVin() {
		return vin;
	}
	
	public void setVin(String vin) {
		this.vin = vin;
	}
	
	public String getEngine() {
		return engine;
	}
	
	public void setEngine(String engine) {
		this.engine = engine;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	public VehicleUsedType getVehicleUsedType() {
		return vehicleUsedType;
	}
	
	public void setVehicleUsedType(VehicleUsedType vehicleUsedType) {
		this.vehicleUsedType = vehicleUsedType;
	}
	
	public String getEnrollDate() {
		return enrollDate;
	}
	
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	
	public int getSeatCount() {
		return seatCount;
	}
	
	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}
	
	public String getIssueDate() {
		return issueDate;
	}
	
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	
	public boolean isTransfer() {
		return transfer;
	}
	
	public void setTransfer(boolean transfer) {
		this.transfer = transfer;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getExhaust() {
		return exhaust;
	}
	
	public void setExhaust(String exhaust) {
		this.exhaust = exhaust;
	}
	
	public String getLoad() {
		return load;
	}
	
	public void setLoad(String load) {
		this.load = load;
	}
	
	public InsurUnit getOwner() {
		return owner;
	}
	
	public void setOwner(InsurUnit owner) {
		this.owner = owner;
	}
	
	public InsurUnit getInsurer() {
		return insurer;
	}
	
	public void setInsurer(InsurUnit insurer) {
		this.insurer = insurer;
	}
	
	public InsurUnit getInsured() {
		return insured;
	}
	
	public void setInsured(InsurUnit insured) {
		this.insured = insured;
	}
	
	public PolicySchema getSchema() {
		return schema;
	}
	
	public void setSchema(PolicySchema schema) {
		this.schema = schema;
	}
	
	public PolicyDetail getDetail() {
		return detail;
	}
	
	public void setDetail(PolicyDetail detail) {
		this.detail = detail;
	}
}
