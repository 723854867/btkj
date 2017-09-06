package org.btkj.pojo.info;

import java.io.Serializable;

public class VehicleInfo implements Serializable {

	private static final long serialVersionUID = 230292822921825687L;

	private String id;										// 唯一标识码
	private String InsVehicleId;							// 精友码
	private int seat;										// 座位数
	private int year;										// 年款
	private String name;									// 名字
	private String price;									// 价格(含税)
	private String priceNoTax;								// 购置价(不含税)
	private String load;									// 核定载质量
	private String exhaust;									// 排量
	private String transmissionName;						// 变速器类型
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getInsVehicleId() {
		return InsVehicleId;
	}
	
	public void setInsVehicleId(String insVehicleId) {
		InsVehicleId = insVehicleId;
	}
	
	public int getSeat() {
		return seat;
	}
	
	public void setSeat(int seat) {
		this.seat = seat;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getPriceNoTax() {
		return priceNoTax;
	}
	
	public void setPriceNoTax(String priceNoTax) {
		this.priceNoTax = priceNoTax;
	}
	
	public String getLoad() {
		return load;
	}
	
	public void setLoad(String load) {
		this.load = load;
	}
	
	public String getExhaust() {
		return exhaust;
	}
	
	public void setExhaust(String exhaust) {
		this.exhaust = exhaust;
	}
	
	public String getTransmissionName() {
		return transmissionName;
	}
	
	public void setTransmissionName(String transmissionName) {
		this.transmissionName = transmissionName;
	}
}
