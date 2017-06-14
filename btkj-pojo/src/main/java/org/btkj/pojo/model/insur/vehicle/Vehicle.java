package org.btkj.pojo.model.insur.vehicle;

import java.io.Serializable;

import org.btkj.pojo.enums.VehicleType;
import org.btkj.pojo.enums.VehicleUsedType;

/**
 * 车辆信息
 * 
 * @author ahab
 */
public class Vehicle implements Serializable {

	private static final long serialVersionUID = 356404095777129105L;

	private String license;				// 车牌号
	private String vin;					// 车架号
	private String engine;				// 发动机号
	private String model; 				// 厂牌型号
	private VehicleType type;			// 车辆类型
	private VehicleUsedType usedType;	// 使用性质
	private String enrollDate; 			// 初登日期
	private int seatCount;				// 座位数
	private String issueDate; 			// 发证日期
	private boolean transfer;			// 是否过户
	private String price;				// 新车购置价
	private String exhaust;				// 排量
	private String load;				// 核定载质量

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
	
	public VehicleType getType() {
		return type;
	}
	
	public void setType(VehicleType type) {
		this.type = type;
	}
	
	public VehicleUsedType getUsedType() {
		return usedType;
	}
	
	public void setUsedType(VehicleUsedType usedType) {
		this.usedType = usedType;
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
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
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
}
