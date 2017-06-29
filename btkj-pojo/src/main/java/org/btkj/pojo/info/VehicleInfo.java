package org.btkj.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.enums.vehicle.VehicleType;
import org.btkj.pojo.enums.vehicle.VehicleTypeCode;
import org.btkj.pojo.enums.vehicle.VehicleTypeDetailCode;

public class VehicleInfo implements Serializable {

	private static final long serialVersionUID = 230292822921825687L;

	private String id;										// 唯一标识码
	private int seat;										// 座位数
	private int year;										// 年款
	private String name;									// 名字
	private double price;									// 价格(含税)
	private double priceNoTax;								// 购置价(不含税)
	private String load;									// 核定载质量
	private String exhaust;									// 排量
	private String transmissionName;						// 变速器类型
	private VehicleType vehicleType;						// 车辆种类
	private VehicleTypeCode vehicleTypeCode;				// 车辆种类类型
	private VehicleTypeDetailCode vehicleTypeDetailCode;	// 特种车特有：特种车的详细分类
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
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
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPriceNoTax() {
		return priceNoTax;
	}
	
	public void setPriceNoTax(double priceNoTax) {
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
	
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	public VehicleTypeCode getVehicleTypeCode() {
		return vehicleTypeCode;
	}
	
	public void setVehicleTypeCode(VehicleTypeCode vehicleTypeCode) {
		this.vehicleTypeCode = vehicleTypeCode;
	}
	
	public VehicleTypeDetailCode getVehicleTypeDetailCode() {
		return vehicleTypeDetailCode;
	}
	
	public void setVehicleTypeDetailCode(VehicleTypeDetailCode vehicleTypeDetailCode) {
		this.vehicleTypeDetailCode = vehicleTypeDetailCode;
	}
}
