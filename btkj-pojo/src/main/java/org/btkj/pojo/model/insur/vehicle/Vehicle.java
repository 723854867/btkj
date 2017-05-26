package org.btkj.pojo.model.insur.vehicle;

import java.io.Serializable;

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
	private String model; 				// 车辆型号
	private String enrollDate; 			// 初登日期
	private int seatCount;				// 座位数
	private String transferDate; 		// 过户日期

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

	public String getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(String transferDate) {
		this.transferDate = transferDate;
	}
}
