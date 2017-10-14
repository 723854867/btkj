package org.btkj.pojo.info;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.pojo.enums.CommercialInsuranceType;
import org.btkj.pojo.enums.VehicleUsedType;
import org.btkj.pojo.model.InsurUnit;
import org.btkj.pojo.model.Insurance;
import org.btkj.pojo.model.PolicySchema;
import org.btkj.pojo.param.VehicleOrderParam;
import org.btkj.pojo.param.VehicleOrderParam.InsuranceItem;
import org.rapid.util.lang.CollectionUtil;

/**
 * 保单基本信息
 * 
 * @author ahab
 */
public class VehiclePolicyTips implements Serializable {

	private static final long serialVersionUID = -108775801806960634L;
	
	// 车辆信息(用户输入)
	private String vin;							// 车架号
	private String engine;						// 发动机号
	private String license;						// 车牌号
	private String issueDate; 					// 发证日期
	private boolean transfer;					// 是否过户
	private String enrollDate; 					// 初登日期
	private VehicleUsedType usedType;			// 使用性质
	
	// 车辆信息(从车架号获取)
	private int seat;										// 座位数
	private int year;										// 年款
	private String name;									// 名字
	private String price;									// 价格(含税)
	private String priceNoTax;								// 购置价(不含税)
	private String load;									// 核定载质量
	private String exhaust;									// 排量
	private String transmissionName;						// 变速器类型
	private String biHuJYId;								// 壁虎精友码
	private String vehicleId;								// 乐保吧车型ID
	private String leBaoBaJYId;								// 乐保吧精友ID
	
	// 主体信息
	private InsurUnit owner;					// 车主信息
	private InsurUnit insurer;					// 投保人信息
	private InsurUnit insured;					// 被保人信息
	
	// 保险信息
	private PolicySchema schema;
	
	public VehiclePolicyTips() {}
	
	public VehiclePolicyTips(VehicleOrderParam param, VehicleInfo vehicleInfo) {
		this.vin = param.getVin();
		this.name = param.getName();
		this.load = param.getLoad();
		this.price = param.getPrice();
		this.engine = param.getEngine();
		this.license = param.getLicense();
		this.exhaust = param.getExhaust();
		this.transfer = param.isTransfer();
		this.usedType = param.getUsedType();
		this.biHuJYId = param.getBiHuJYId();
		this.vehicleId = param.getVehicleId();
		this.issueDate = param.getIssueDate();
		this.enrollDate = param.getEnrollDate();
		this.priceNoTax = param.getPriceNoTax();
		this.transmissionName = param.getTransmissionName();
		this.seat = param.getSeat();
		this.year = null == param.getYear() ? 0 : param.getYear();
		if (null != vehicleInfo)
			this.leBaoBaJYId = vehicleInfo.getInsVehicleId();
		this.owner = new InsurUnit();
		this.owner.setType(param.getOwner().getType());
		this.owner.setName(param.getOwner().getName());
		this.owner.setIdNo(param.getOwner().getIdNo());
		this.owner.setMobile(param.getOwner().getMobile());
		this.owner.setIdType(param.getOwner().getIdType());
		this.insurer = new InsurUnit();
		this.insurer.setType(param.getInsurer().getType());
		this.insurer.setName(param.getInsurer().getName());
		this.insurer.setIdNo(param.getInsurer().getIdNo());
		this.insurer.setMobile(param.getInsurer().getMobile());
		this.insurer.setIdType(param.getInsurer().getIdType());	
		this.insured = new InsurUnit();
		this.insured.setType(param.getInsured().getType());
		this.insured.setName(param.getInsured().getName());
		this.insured.setIdNo(param.getInsured().getIdNo());
		this.insured.setMobile(param.getInsured().getMobile());
		this.insured.setIdType(param.getInsured().getIdType());
		this.schema = new PolicySchema();
		this.schema.setCommercialStart(param.getCommercialStart());
		this.schema.setCompulsoryStart(param.getCompulsoryStart());
		if (!CollectionUtil.isEmpty(param.getInsurances())) {
			Map<CommercialInsuranceType, Insurance> insurances = new HashMap<CommercialInsuranceType, Insurance>();
			for (Entry<CommercialInsuranceType, InsuranceItem> entry : param.getInsurances().entrySet()) {
				Insurance insurance = new Insurance();
				insurance.setQuota(entry.getValue().getQuota());
				insurance.setPrice(entry.getValue().getPrice());
				insurances.put(entry.getKey(), insurance);
			}
			this.schema.setInsurances(insurances);
		}
	}
	
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

	public VehicleUsedType getUsedType() {
		return usedType;
	}
	
	public void setUsedType(VehicleUsedType usedType) {
		this.usedType = usedType;
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
	
	public String getLeBaoBaJYId() {
		return leBaoBaJYId;
	}
	
	public void setLeBaoBaJYId(String leBaoBaJYId) {
		this.leBaoBaJYId = leBaoBaJYId;
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
}
