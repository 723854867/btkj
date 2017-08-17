package org.btkj.pojo.vo;

import java.io.Serializable;
import java.util.LinkedList;

import org.btkj.pojo.bo.InsurUnit;
import org.btkj.pojo.bo.PolicyDetail;
import org.btkj.pojo.bo.PolicySchema;
import org.btkj.pojo.enums.VehicleBizType;
import org.btkj.pojo.enums.VehicleType;
import org.btkj.pojo.enums.VehicleTypeCode;
import org.btkj.pojo.enums.VehicleTypeDetailCode;
import org.btkj.pojo.enums.VehicleUsedType;

/**
 * 保单基本信息
 * 
 * @author ahab
 */
public class VehiclePolicyTips implements Serializable {

	private static final long serialVersionUID = -108775801806960634L;
	
	// 车辆信息(用户输入)
	private String license;						// 车牌号
	private String vin;							// 车架号
	private String engine;						// 发动机号
	private String enrollDate; 					// 初登日期
	private String issueDate; 					// 发证日期
	private boolean transfer;					// 是否过户
	private VehicleUsedType vehicleUsedType;	// 使用性质
	
	// 车辆信息(从车架号获取)
	private int seat;										// 座位数
	private int year;										// 年款
	private String name;									// 名字
	private double price;									// 价格(含税)
	private double priceNoTax;								// 购置价(不含税)
	private String load;									// 核定载质量
	private String exhaust;									// 排量
	private String transmissionName;						// 变速器类型
	private String biHuJYId;								// 壁虎精友码
	private String vehicleId;								// 乐保吧车型ID
	private String leBaoBaJYId;								// 乐保吧精友ID
	private VehicleType vehicleType;						// 车辆种类
	private VehicleTypeCode vehicleTypeCode;				// 车辆种类类型
	private VehicleTypeDetailCode vehicleTypeDetailCode;	// 特种车特有：特种车的详细分类
	
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

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
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

	public VehicleUsedType getVehicleUsedType() {
		return vehicleUsedType;
	}

	public void setVehicleUsedType(VehicleUsedType vehicleUsedType) {
		this.vehicleUsedType = vehicleUsedType;
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
	
	public String getBiHuJYId() {
		return biHuJYId;
	}
	
	public void setBiHuJYId(String biHuJYId) {
		this.biHuJYId = biHuJYId;
	}
	
	public String getVehicleId() {
		return vehicleId;
	}
	
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	
	public String getLeBaoBaJYId() {
		return leBaoBaJYId;
	}
	
	public void setLeBaoBaJYId(String leBaoBaJYId) {
		this.leBaoBaJYId = leBaoBaJYId;
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
	
	public void bind(VehicleInfo vehicleInfo) {
		this.seat = vehicleInfo.getSeat();
		this.year = vehicleInfo.getYear();
		this.name = vehicleInfo.getName();
		this.price = vehicleInfo.getPrice();
		this.priceNoTax = vehicleInfo.getPriceNoTax();
		this.load = vehicleInfo.getLoad();
		this.exhaust = vehicleInfo.getExhaust();
		this.transmissionName = vehicleInfo.getTransmissionName();
		this.vehicleId = vehicleInfo.getId();
		this.vehicleType = vehicleInfo.getVehicleType();
		this.vehicleTypeCode = vehicleInfo.getVehicleTypeCode();
		this.vehicleTypeDetailCode = vehicleInfo.getVehicleTypeDetailCode();
	}
	
	public LinkedList<String> commisionRoutePath() {
		LinkedList<String> list = new LinkedList<String>();
		switch (vehicleUsedType) {
		case HOME_USE:
			list.addLast(VehicleBizType.NO_PROFIT.id());
			list.addLast(VehicleType.COACH.name().toLowerCase());
			list.addLast(VehicleUsedType.HOME_USE.id());
			break;
		case ENTERPRISE:
			list.addLast(VehicleBizType.NO_PROFIT.id());
			list.addLast(VehicleType.COACH.name().toLowerCase());
			list.addLast(VehicleUsedType.ENTERPRISE.id());
			break;
		case ORGAN:
			list.addLast(VehicleBizType.NO_PROFIT.id());
			list.addLast(VehicleType.COACH.name().toLowerCase());
			list.addLast(VehicleUsedType.ORGAN.id());
			break;
		case LEASE:
			list.addLast(VehicleBizType.PROFIT.id());
			list.addLast(VehicleType.COACH.name().toLowerCase());
			list.addLast(VehicleUsedType.LEASE.id());
			break;
		case CITY_BUS:
			list.addLast(VehicleBizType.PROFIT.id());
			list.addLast(VehicleType.COACH.name().toLowerCase());
			list.addLast(VehicleUsedType.CITY_BUS.id());
			break;
		case HIGHWAY_TRANSPORT:
			list.addLast(VehicleBizType.PROFIT.id());
			list.addLast(VehicleType.COACH.name().toLowerCase());
			list.addLast(VehicleUsedType.HIGHWAY_TRANSPORT.id());
			break;
		case BIZ_TRUCK:
			list.addLast(VehicleBizType.PROFIT.id());
			list.addLast(VehicleType.TRUCK.name().toLowerCase());
			break;
		case NO_BIZ_TRUCK:
			list.addLast(VehicleBizType.NO_PROFIT.id());
			list.addLast(VehicleType.TRUCK.name().toLowerCase());
			break;
		case PARTICULAR:
			list.addLast(VehicleBizType.IGNROE_PROFIT.id());
			list.addLast(VehicleType.PARTICULAR.name().toLowerCase());
			break;
		case MOTOR:
			list.addLast(VehicleBizType.IGNROE_PROFIT.id());
			list.addLast(VehicleType.MOTOR.name().toLowerCase());
			break;
		case TRACTOR:
			list.addLast(VehicleBizType.IGNROE_PROFIT.id());
			list.addLast(VehicleType.TRACTOR.name().toLowerCase());
			break;
		default:
			return null;
		}
		if (null != vehicleTypeCode)
			list.addLast(vehicleTypeCode.name());
		if (null != vehicleTypeDetailCode)
			list.addLast(vehicleTypeDetailCode.name().replaceAll("V", ""));
		return list;
	}
}
