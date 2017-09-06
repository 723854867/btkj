package org.btkj.pojo.param;

import java.io.Serializable;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.enums.CommercialInsuranceType;
import org.btkj.pojo.enums.IDType;
import org.btkj.pojo.enums.VehicleUnitType;
import org.btkj.pojo.enums.VehicleUsedType;
import org.btkj.pojo.info.VehicleInfo;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.validator.custom.CarEngine;
import org.rapid.util.validator.custom.CarLicense;
import org.rapid.util.validator.custom.CarVin;
import org.rapid.util.validator.custom.Date;
import org.rapid.util.validator.custom.Mobile;
import org.rapid.util.validator.custom.Numeric;

/**
 * 报价参数
 * 
 * @author ahab
 */
public class VehicleOrderParam extends EmployeeParam {

	private static final long serialVersionUID = 5252138275575928498L;

	@Min(1)
	private int quoteMod;									// 报价险企模值
	@Min(0)
	private int insureMod;									// 核保险企模值
	
	@CarLicense
	@NotNull
	private String license;									// 车牌号
	@CarVin
	@NotNull
	private String vin;										// 车架号
	@CarEngine
	@NotNull
	private String engine;									// 发动机号
	@Date(fomat = DateUtil.YYYY_MM_DD)
	@NotNull
	private String enrollDate; 								// 初登日期
	@Date
	private String issueDate; 								// 发证日期
	private boolean transfer;								// 是否过户
	@NotNull
	private VehicleUsedType usedType;						// 使用性质
	
	// 变速器类型和壁虎精友码用在当乐保吧车型ID为null时用来获取唯一的乐保吧车型
	private String transmissionName;						// 变速器类型
	private String biHuJYId;								// 壁虎精友码
	private String vehicleId;								// 乐保吧车型ID(如果有该ID，那么车辆信息肯定是齐全的)
	
	// 下面这些参数如果不为null则以传递的为准，如果为 null，则以 vehicleId 读取出来的车型为准
	private Integer seat;									// 座位数
	private Integer year;									// 年款
	private String name;									// 厂牌型号
	@Numeric
	private String price;									// 价格(含税)
	@Numeric
	private String priceNoTax;								// 购置价(不含税)
	private String load;									// 核定载质量
	private String exhaust;									// 排量
	
	// 主体信息
	@NotNull
	@Valid
	private Unit owner;										// 车主信息
	@NotNull
	@Valid
	private Unit insurer;									// 投保人信息
	@NotNull
	@Valid
	private Unit insured;									// 被保人信息
	
	// 投保方案
	@Date
	private String commercialStart;							// 商业险起保时间
	@Date
	private String compulsoryStart;							// 交强险起保时间
	@Size(min = 1, max = 30)
	private Map<CommercialInsuranceType, InsuranceItem> insurances;
	
	public int getQuoteMod() {
		return quoteMod;
	}
	
	public void setQuoteMod(int quoteMod) {
		this.quoteMod = quoteMod;
	}

	public int getInsureMod() {
		return insureMod;
	}

	public void setInsureMod(int insureMod) {
		this.insureMod = insureMod;
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
	
	public Integer getSeat() {
		return seat;
	}

	public void setSeat(Integer seat) {
		this.seat = seat;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
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

	public Unit getOwner() {
		return owner;
	}
	
	public void setOwner(Unit owner) {
		this.owner = owner;
	}
	
	public Unit getInsurer() {
		return insurer;
	}
	
	public void setInsurer(Unit insurer) {
		this.insurer = insurer;
	}
	
	public Unit getInsured() {
		return insured;
	}
	
	public void setInsured(Unit insured) {
		this.insured = insured;
	}

	public String getCommercialStart() {
		return commercialStart;
	}

	public void setCommercialStart(String commercialStart) {
		this.commercialStart = commercialStart;
	}

	public String getCompulsoryStart() {
		return compulsoryStart;
	}
	
	public void setCompulsoryStart(String compulsoryStart) {
		this.compulsoryStart = compulsoryStart;
	}

	public Map<CommercialInsuranceType, InsuranceItem> getInsurances() {
		return insurances;
	}
	
	public void setInsurances(Map<CommercialInsuranceType, InsuranceItem> insurances) {
		this.insurances = insurances;
	}
	
	public void bindVehicleInfo(VehicleInfo vehicleInfo) { 
		this.vehicleId = vehicleInfo.getId();
		this.seat = vehicleInfo.getSeat();
		this.year = vehicleInfo.getYear();
		if (null != vehicleInfo.getPrice())
			this.price = vehicleInfo.getPrice();
		if (null != vehicleInfo.getPriceNoTax())
			this.priceNoTax = vehicleInfo.getPriceNoTax();
		this.name = vehicleInfo.getName();
		this.transmissionName = vehicleInfo.getTransmissionName();
		this.load = vehicleInfo.getLoad();
		this.exhaust = vehicleInfo.getExhaust();
	}
	
	public class Unit implements Serializable {
		private static final long serialVersionUID = -5366462716100333615L;
		@NotNull
		private VehicleUnitType type;		// 类型
		@NotNull
		@Size(min = BtkjConsts.LIMITS.NAME_MIN, max = BtkjConsts.LIMITS.NAME_MAX)
		private String name;				// 名字
		@NotNull
		@Mobile
		private String mobile;				// 手机号：如果是单位则一般是联系人的手机号
		@NotNull
		private IDType idType;				// 证件类型
		@NotNull
		private String idNo;				// 证件号
		public VehicleUnitType getType() {
			return type;
		}
		public void setType(VehicleUnitType type) {
			this.type = type;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public IDType getIdType() {
			return idType;
		}
		public void setIdType(IDType idType) {
			this.idType = idType;
		}
		public String getIdNo() {
			return idNo;
		}
		public void setIdNo(String idNo) {
			this.idNo = idNo;
		}
	}
	
	public class InsuranceItem implements Serializable {
		private static final long serialVersionUID = 596592567507107727L;
		@NotNull
		@Numeric
		private String quota;
		@Numeric
		private String price;
		public String getQuota() {
			return quota;
		}
		public void setQuota(String quota) {
			this.quota = quota;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
	}
}
