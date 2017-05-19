package org.btkj.pojo.submit;

import java.io.Serializable;

import org.btkj.pojo.enums.IDType;
import org.btkj.pojo.enums.UnitType;
import org.btkj.pojo.enums.VehicleFlag;
import org.btkj.pojo.enums.VehicleType;
import org.btkj.pojo.enums.VehicleUsedType;
import org.btkj.pojo.model.insur.Insur;
import org.btkj.pojo.model.insur.DamageInsur;

public class QuoteSubmit implements Serializable {

	private static final long serialVersionUID = -108775801806960634L;

	private UnitInfo owner;					// 车主信息
	private UnitInfo insure;				// 投保人信息
	private UnitInfo insured;				// 被保人信息
	private VehicleInfo vehicleInfo;
	
	public UnitInfo getOwner() {
		return owner;
	}
	
	public void setOwner(UnitInfo owner) {
		this.owner = owner;
	}
	
	public UnitInfo getInsure() {
		return insure;
	}
	
	public void setInsure(UnitInfo insure) {
		this.insure = insure;
	}
	
	public UnitInfo getInsured() {
		return insured;
	}
	
	public void setInsured(UnitInfo insured) {
		this.insured = insured;
	}
	
	public VehicleInfo getVehicleInfo() {
		return vehicleInfo;
	}
	
	public void setVehicleInfo(VehicleInfo vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}
	
	/**
	 * 单位或者个人信息
	 * 
	 * @author ahab
	 */
	public class UnitInfo implements Serializable {
		private static final long serialVersionUID = 3166597984394403191L;
		private UnitType type;				// 类型：个人、机关、企业
		private IDType idType;				// 证件类型
		private String idNo;				// 证件号码
		private String name;				// 名字
		private String mobile;				// 手机号码
		public UnitType getType() {
			return type;
		}
		public void setType(UnitType type) {
			this.type = type;
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
	}
	
	/**
	 * 车辆信息
	 * @author ahab
	 */
	public class VehicleInfo implements Serializable {
		private static final long serialVersionUID = -8459573951237408706L;
		private VehicleFlag flag;					// 车辆上牌状态
		private String licenseNo;					// 车牌
		private String vin;							// 车架号
		private String engineNo;					// 发动机号
		private int seatCount;						// 座位数
		private String enrollDate;					// 初登日期
		private String transferDate;				// 如果是过户车则填写过户日期
		private VehicleType type;					// 车辆类型
		private VehicleUsedType usedType;			// 车辆使用性质
		private String moldName;					// 车辆厂牌型号
		private String loadWeight;					// 核载质量(吨位)
		private String exhaust;						// 排量
		private String price;						// 购置价格(含税)
		public VehicleFlag getFlag() {
			return flag;
		}
		public void setFlag(VehicleFlag flag) {
			this.flag = flag;
		}
		public String getLicenseNo() {
			return licenseNo;
		}
		public void setLicenseNo(String licenseNo) {
			this.licenseNo = licenseNo;
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
		public String getEngineNo() {
			return engineNo;
		}
		public void setEngineNo(String engineNo) {
			this.engineNo = engineNo;
		}
		public String getVin() {
			return vin;
		}
		public void setVin(String vin) {
			this.vin = vin;
		}
		public String getMoldName() {
			return moldName;
		}
		public void setMoldName(String moldName) {
			this.moldName = moldName;
		}
		public String getTransferDate() {
			return transferDate;
		}
		public void setTransferDate(String transferDate) {
			this.transferDate = transferDate;
		}
		public int getSeatCount() {
			return seatCount;
		}
		public void setSeatCount(int seatCount) {
			this.seatCount = seatCount;
		}
		public String getLoadWeight() {
			return loadWeight;
		}
		public void setLoadWeight(String loadWeight) {
			this.loadWeight = loadWeight;
		}
		public String getExhaust() {
			return exhaust;
		}
		public void setExhaust(String exhaust) {
			this.exhaust = exhaust;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
		public String getEnrollDate() {
			return enrollDate;
		}
		public void setEnrollDate(String enrollDate) {
			this.enrollDate = enrollDate;
		}
	}
	
	/**
	 * 险种信息
	 * @author ahab
	 */
	public class VehicleInsur implements Serializable {
		private static final long serialVersionUID = 9173178875694654678L;
		private String compulsoryInsurEnd;		// 交强险截止日期
		private String commercialInsurEnd;		// 商业险截止日期
		private String compulsoryInsurBegin;	// 交强险起保日期
		private String commercialInsurBegin;	// 商业险起保日期
		private DamageInsur damageInsur;			// 车损险
		private Insur thirdLiabilityInsur;			// 第三者责任险
		private Insur driverSeatInsur;				// 司机座位险
		private Insur passengerSeatInsur;			// 乘客座位险
		private Insur robberyInsur;					// 盗抢险
		public String getCompulsoryInsurEnd() {
			return compulsoryInsurEnd;
		}
		public void setCompulsoryInsurEnd(String compulsoryInsurEnd) {
			this.compulsoryInsurEnd = compulsoryInsurEnd;
		}
		public String getCommercialInsurEnd() {
			return commercialInsurEnd;
		}
		public void setCommercialInsurEnd(String commercialInsurEnd) {
			this.commercialInsurEnd = commercialInsurEnd;
		}
		public String getCompulsoryInsurBegin() {
			return compulsoryInsurBegin;
		}
		public void setCompulsoryInsurBegin(String compulsoryInsurBegin) {
			this.compulsoryInsurBegin = compulsoryInsurBegin;
		}
		public String getCommercialInsurBegin() {
			return commercialInsurBegin;
		}
		public void setCommercialInsurBegin(String commercialInsurBegin) {
			this.commercialInsurBegin = commercialInsurBegin;
		}
		public DamageInsur getDamageInsur() {
			return damageInsur;
		}
		public void setDamageInsur(DamageInsur damageInsur) {
			this.damageInsur = damageInsur;
		}
		public Insur getThirdLiabilityInsur() {
			return thirdLiabilityInsur;
		}
		public void setThirdLiabilityInsur(Insur thirdLiabilityInsur) {
			this.thirdLiabilityInsur = thirdLiabilityInsur;
		}
		public Insur getDriverSeatInsur() {
			return driverSeatInsur;
		}
		public void setDriverSeatInsur(Insur driverSeatInsur) {
			this.driverSeatInsur = driverSeatInsur;
		}
		public Insur getPassengerSeatInsur() {
			return passengerSeatInsur;
		}
		public void setPassengerSeatInsur(Insur passengerSeatInsur) {
			this.passengerSeatInsur = passengerSeatInsur;
		}
		public Insur getRobberyInsur() {
			return robberyInsur;
		}
		public void setRobberyInsur(Insur robberyInsur) {
			this.robberyInsur = robberyInsur;
		}
	}
}
