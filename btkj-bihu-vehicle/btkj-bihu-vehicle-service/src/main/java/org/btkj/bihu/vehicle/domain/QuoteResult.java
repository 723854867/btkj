package org.btkj.bihu.vehicle.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.btkj.bihu.vehicle.RespHandler;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.bo.Insurance;
import org.btkj.pojo.bo.PolicySchema;
import org.btkj.pojo.enums.CommercialInsuranceType;
import org.rapid.util.common.message.Result;

public class QuoteResult implements Serializable {
	
	public static final RespHandler<QuoteResult> JSON_HANDLER			= RespHandler.build(QuoteResult.class);

	private static final long serialVersionUID = 6161307790712774336L;

	private int BusinessStatus;
	private String StatusMessage;
	private UserInfo UserInfo;
	private Item Item;
	private String CustKey;
	private CarInfo CarInfo;
	
	public int getBusinessStatus() {
		return BusinessStatus;
	}
	
	public void setBusinessStatus(int businessStatus) {
		BusinessStatus = businessStatus;
	}
	
	public String getStatusMessage() {
		return StatusMessage;
	}
	
	public void setStatusMessage(String statusMessage) {
		StatusMessage = statusMessage;
	}
	
	public UserInfo getUserInfo() {
		return UserInfo;
	}
	
	public void setUserInfo(UserInfo userInfo) {
		UserInfo = userInfo;
	}
	
	public Item getItem() {
		return Item;
	}
	
	public void setItem(Item item) {
		Item = item;
	}
	
	public String getCustKey() {
		return CustKey;
	}
	
	public void setCustKey(String custKey) {
		CustKey = custKey;
	}
	
	public CarInfo getCarInfo() {
		return CarInfo;
	}
	
	public void setCarInfo(CarInfo carInfo) {
		CarInfo = carInfo;
	}
	
	public class UserInfo implements Serializable {

		private static final long serialVersionUID = -4753064904713837174L;
		
		private String LicenseNo;					// 车牌号
		private String ForceExpireDate;				// (去年)交强险到期时间（报价成功即可返回）
		private String BusinessExpireDate;			// （去年）商业险到期时间(准确率有问题，只有平安成功时才会返回内容)
		private String BusinessStartDate;			// (这次报价)商业险起保日期（报价成功即可返回）到期时间是90天外的，无法报价交强险，商业险按第二天进行投保算价
		private String ForceStartDate;				// (这次报价)交强险起保日期（报价成功即可返回）
		private String InsuredName;					// 被保险人姓名
		private String InsuredIdCard;				// 被保险人证件号
		private int InsuredIdType;					// 被保人证件类型1：身份证2: 组织机构代码证3：护照4：军官证5：港澳回乡证或台胞证6：其他7：港澳通行证
		private String InsuredMobile;				// 被保险人手机号
		private String HolderName;					// 投保人姓名
		private String HolderIdCard;				//　投保人证件号
		private int HolderIdType;					// 投保人证件类型1：身份证2: 组织机构代码证3：护照4：军官证5：港澳回乡证或台胞证6：其他7：港澳通行证
		private String HolderMobile;				// 投保人手机号
		private String Email;						// 被保人/投保人邮箱
		private String AutoMoldCode;				// 精友编码
		private String VehicleInfo;					// 车型信息
		public String getLicenseNo() {
			return LicenseNo;
		}
		public void setLicenseNo(String licenseNo) {
			LicenseNo = licenseNo;
		}
		public String getForceExpireDate() {
			return ForceExpireDate;
		}
		public void setForceExpireDate(String forceExpireDate) {
			ForceExpireDate = forceExpireDate;
		}
		public String getBusinessExpireDate() {
			return BusinessExpireDate;
		}
		public void setBusinessExpireDate(String businessExpireDate) {
			BusinessExpireDate = businessExpireDate;
		}
		public String getBusinessStartDate() {
			return BusinessStartDate;
		}
		public void setBusinessStartDate(String businessStartDate) {
			BusinessStartDate = businessStartDate;
		}
		public String getForceStartDate() {
			return ForceStartDate;
		}
		public void setForceStartDate(String forceStartDate) {
			ForceStartDate = forceStartDate;
		}
		public String getInsuredName() {
			return InsuredName;
		}
		public void setInsuredName(String insuredName) {
			InsuredName = insuredName;
		}
		public String getInsuredIdCard() {
			return InsuredIdCard;
		}
		public void setInsuredIdCard(String insuredIdCard) {
			InsuredIdCard = insuredIdCard;
		}
		public int getInsuredIdType() {
			return InsuredIdType;
		}
		public void setInsuredIdType(int insuredIdType) {
			InsuredIdType = insuredIdType;
		}
		public String getInsuredMobile() {
			return InsuredMobile;
		}
		public void setInsuredMobile(String insuredMobile) {
			InsuredMobile = insuredMobile;
		}
		public String getHolderName() {
			return HolderName;
		}
		public void setHolderName(String holderName) {
			HolderName = holderName;
		}
		public String getHolderIdCard() {
			return HolderIdCard;
		}
		public void setHolderIdCard(String holderIdCard) {
			HolderIdCard = holderIdCard;
		}
		public int getHolderIdType() {
			return HolderIdType;
		}
		public void setHolderIdType(int holderIdType) {
			HolderIdType = holderIdType;
		}
		public String getHolderMobile() {
			return HolderMobile;
		}
		public void setHolderMobile(String holderMobile) {
			HolderMobile = holderMobile;
		}
		public String getEmail() {
			return Email;
		}
		public void setEmail(String email) {
			Email = email;
		}
		public String getAutoMoldCode() {
			return AutoMoldCode;
		}
		public void setAutoMoldCode(String autoMoldCode) {
			AutoMoldCode = autoMoldCode;
		}
		public String getVehicleInfo() {
			return VehicleInfo;
		}
		public void setVehicleInfo(String vehicleInfo) {
			VehicleInfo = vehicleInfo;
		}
	}
	public class Item implements Serializable {
		private static final long serialVersionUID = -1290916775545123870L;
		private int Source;
		private double BizTotal;				// 商业险总额
		private double ForceTotal;				// 交强险总额(90天之外的取不到，值为0)
		private double TaxTotal;				// 车船税总额(90天之外的取不到，值为0)
		private int QuoteStatus;				// 报价状态，-1=未报价， 0=报价失败，>0报价成功
		private String QuoteResult;				// 报价信息
		private Detail CheSun;					// 车损险
		private Detail SanZhe;					// 第三方责任险
		private Detail DaoQiang;				// 全车盗抢险
		private Detail SiJi;					// 车上人员责任险(司机)
		private Detail ChengKe;					// 车上人员责任险(乘客)
		private Detail BoLi;					// 玻璃险
		private Detail HuaHen;					// 不计免划痕险
		private Detail BuJiMianCheSun;			// 不计免车损险
		private Detail BuJiMianSanZhe;			// 不计免三者险
		private Detail BuJiMianDaoQiang;		// 不计免盗抢险
		private Detail SheShui;					// 涉水行驶损失险
		private Detail ZiRan;					// 自燃损失险
		private Detail HcJingShenSunShi;		// 精神损失抚慰金责任险
		private Detail HcSanFangTeYue;			// 机动车损失无法找到第三方
		private Detail BuJiMianChengKe;			// 不计免乘客
		private Detail BuJiMianSiJi;			// 不计免司机
		private Detail BuJiMianHuaHen;			// 不计免划痕
		private Detail BuJiMianSheShui;			// 不计免涉水
		private Detail BuJiMianZiRan;			// 不计免自然
		private Detail BuJiMianJingShenSunShi;	// 不计免精神
		private Detail HcXiuLiChang;			// 指定修理厂保额
		private int HcXiuLiChangType;			// 指定修理厂类型
		private String RateFactor1;				// 费率系数1（无赔款优惠系数）
		private double RateFactor2;				// 费率系数2（自主渠道系数）
		private double RateFactor3;				// 费率系数3（自主核保系数）
		private double RateFactor4; 			// 费率系数4（交通违法浮动系数）
		public int getSource() {
			return Source;
		}
		public void setSource(int source) {
			Source = source;
		}
		public double getBizTotal() {
			return BizTotal;
		}
		public void setBizTotal(double bizTotal) {
			BizTotal = bizTotal;
		}
		public double getForceTotal() {
			return ForceTotal;
		}
		public void setForceTotal(double forceTotal) {
			ForceTotal = forceTotal;
		}
		public double getTaxTotal() {
			return TaxTotal;
		}
		public void setTaxTotal(double taxTotal) {
			TaxTotal = taxTotal;
		}
		public int getQuoteStatus() {
			return QuoteStatus;
		}
		public void setQuoteStatus(int quoteStatus) {
			QuoteStatus = quoteStatus;
		}
		public String getQuoteResult() {
			return QuoteResult;
		}
		public void setQuoteResult(String quoteResult) {
			QuoteResult = quoteResult;
		}
		public Detail getCheSun() {
			return CheSun;
		}
		public void setCheSun(Detail cheSun) {
			CheSun = cheSun;
		}
		public Detail getSanZhe() {
			return SanZhe;
		}
		public void setSanZhe(Detail sanZhe) {
			SanZhe = sanZhe;
		}
		public Detail getDaoQiang() {
			return DaoQiang;
		}
		public void setDaoQiang(Detail daoQiang) {
			DaoQiang = daoQiang;
		}
		public Detail getSiJi() {
			return SiJi;
		}
		public void setSiJi(Detail siJi) {
			SiJi = siJi;
		}
		public Detail getChengKe() {
			return ChengKe;
		}
		public void setChengKe(Detail chengKe) {
			ChengKe = chengKe;
		}
		public Detail getBoLi() {
			return BoLi;
		}
		public void setBoLi(Detail boLi) {
			BoLi = boLi;
		}
		public Detail getHuaHen() {
			return HuaHen;
		}
		public void setHuaHen(Detail huaHen) {
			HuaHen = huaHen;
		}
		public Detail getBuJiMianCheSun() {
			return BuJiMianCheSun;
		}
		public void setBuJiMianCheSun(Detail buJiMianCheSun) {
			BuJiMianCheSun = buJiMianCheSun;
		}
		public Detail getBuJiMianSanZhe() {
			return BuJiMianSanZhe;
		}
		public void setBuJiMianSanZhe(Detail buJiMianSanZhe) {
			BuJiMianSanZhe = buJiMianSanZhe;
		}
		public Detail getBuJiMianDaoQiang() {
			return BuJiMianDaoQiang;
		}
		public void setBuJiMianDaoQiang(Detail buJiMianDaoQiang) {
			BuJiMianDaoQiang = buJiMianDaoQiang;
		}
		public Detail getSheShui() {
			return SheShui;
		}
		public void setSheShui(Detail sheShui) {
			SheShui = sheShui;
		}
		public Detail getZiRan() {
			return ZiRan;
		}
		public void setZiRan(Detail ziRan) {
			ZiRan = ziRan;
		}
		public Detail getHcJingShenSunShi() {
			return HcJingShenSunShi;
		}
		public void setHcJingShenSunShi(Detail hcJingShenSunShi) {
			HcJingShenSunShi = hcJingShenSunShi;
		}
		public Detail getHcSanFangTeYue() {
			return HcSanFangTeYue;
		}
		public void setHcSanFangTeYue(Detail hcSanFangTeYue) {
			HcSanFangTeYue = hcSanFangTeYue;
		}
		public Detail getBuJiMianChengKe() {
			return BuJiMianChengKe;
		}
		public void setBuJiMianChengKe(Detail buJiMianChengKe) {
			BuJiMianChengKe = buJiMianChengKe;
		}
		public Detail getBuJiMianSiJi() {
			return BuJiMianSiJi;
		}
		public void setBuJiMianSiJi(Detail buJiMianSiJi) {
			BuJiMianSiJi = buJiMianSiJi;
		}
		public Detail getBuJiMianHuaHen() {
			return BuJiMianHuaHen;
		}
		public void setBuJiMianHuaHen(Detail buJiMianHuaHen) {
			BuJiMianHuaHen = buJiMianHuaHen;
		}
		public Detail getBuJiMianSheShui() {
			return BuJiMianSheShui;
		}
		public void setBuJiMianSheShui(Detail buJiMianSheShui) {
			BuJiMianSheShui = buJiMianSheShui;
		}
		public Detail getBuJiMianZiRan() {
			return BuJiMianZiRan;
		}
		public void setBuJiMianZiRan(Detail buJiMianZiRan) {
			BuJiMianZiRan = buJiMianZiRan;
		}
		public Detail getBuJiMianJingShenSunShi() {
			return BuJiMianJingShenSunShi;
		}
		public void setBuJiMianJingShenSunShi(Detail buJiMianJingShenSunShi) {
			BuJiMianJingShenSunShi = buJiMianJingShenSunShi;
		}
		public Detail getHcXiuLiChang() {
			return HcXiuLiChang;
		}
		public void setHcXiuLiChang(Detail hcXiuLiChang) {
			HcXiuLiChang = hcXiuLiChang;
		}
		public int getHcXiuLiChangType() {
			return HcXiuLiChangType;
		}
		public void setHcXiuLiChangType(int hcXiuLiChangType) {
			HcXiuLiChangType = hcXiuLiChangType;
		}
		public String getRateFactor1() {
			return RateFactor1;
		}
		public void setRateFactor1(String rateFactor1) {
			RateFactor1 = rateFactor1;
		}
		public double getRateFactor2() {
			return RateFactor2;
		}
		public void setRateFactor2(double rateFactor2) {
			RateFactor2 = rateFactor2;
		}
		public double getRateFactor3() {
			return RateFactor3;
		}
		public void setRateFactor3(double rateFactor3) {
			RateFactor3 = rateFactor3;
		}
		public double getRateFactor4() {
			return RateFactor4;
		}
		public void setRateFactor4(double rateFactor4) {
			RateFactor4 = rateFactor4;
		}
		public class Detail implements Serializable {
			private static final long serialVersionUID = 2576669839303824040L;
			private double BaoE;			// 保额
			private double BaoFei;			// 保费
			public double getBaoE() {
				return BaoE;
			}
			public void setBaoE(double baoE) {
				BaoE = baoE;
			}
			public double getBaoFei() {
				return BaoFei;
			}
			public void setBaoFei(double baoFei) {
				BaoFei = baoFei;
			}
		}
	}
	
	public class CarInfo implements Serializable {
		private static final long serialVersionUID = 9082107578995614065L;
		private String Source;
		private String EngineNo;
		private String CarVin;
		private String MoldName;
		private String RegisterDate;
		private String CredentislasNum;
		private String LicenseOwner;
		private int IdType;
		private int LicenseType;
		private int CarUsedType;
		private int SeatCount;
		private double ExhaustScale;
		private double CarEquQuality;
		private double TonCount;
		private double PurchasePrice;
		private int FuelType;
		private int ProofType;
		private int LicenseColor;
		private int ClauseType;
		private int RunRegion;
		private String Risk;
		public String getSource() {
			return Source;
		}
		public void setSource(String source) {
			Source = source;
		}
		public String getEngineNo() {
			return EngineNo;
		}
		public void setEngineNo(String engineNo) {
			EngineNo = engineNo;
		}
		public String getCarVin() {
			return CarVin;
		}
		public void setCarVin(String carVin) {
			CarVin = carVin;
		}
		public String getMoldName() {
			return MoldName;
		}
		public void setMoldName(String moldName) {
			MoldName = moldName;
		}
		public String getRegisterDate() {
			return RegisterDate;
		}
		public void setRegisterDate(String registerDate) {
			RegisterDate = registerDate;
		}
		public String getCredentislasNum() {
			return CredentislasNum;
		}
		public void setCredentislasNum(String credentislasNum) {
			CredentislasNum = credentislasNum;
		}
		public String getLicenseOwner() {
			return LicenseOwner;
		}
		public void setLicenseOwner(String licenseOwner) {
			LicenseOwner = licenseOwner;
		}
		public int getIdType() {
			return IdType;
		}
		public void setIdType(int idType) {
			IdType = idType;
		}
		public int getLicenseType() {
			return LicenseType;
		}
		public void setLicenseType(int licenseType) {
			LicenseType = licenseType;
		}
		public int getCarUsedType() {
			return CarUsedType;
		}
		public void setCarUsedType(int carUsedType) {
			CarUsedType = carUsedType;
		}
		public int getSeatCount() {
			return SeatCount;
		}
		public void setSeatCount(int seatCount) {
			SeatCount = seatCount;
		}
		public double getExhaustScale() {
			return ExhaustScale;
		}
		public void setExhaustScale(double exhaustScale) {
			ExhaustScale = exhaustScale;
		}
		public double getCarEquQuality() {
			return CarEquQuality;
		}
		public void setCarEquQuality(double carEquQuality) {
			CarEquQuality = carEquQuality;
		}
		public double getTonCount() {
			return TonCount;
		}
		public void setTonCount(double tonCount) {
			TonCount = tonCount;
		}
		public double getPurchasePrice() {
			return PurchasePrice;
		}
		public void setPurchasePrice(double purchasePrice) {
			PurchasePrice = purchasePrice;
		}
		public int getFuelType() {
			return FuelType;
		}
		public void setFuelType(int fuelType) {
			FuelType = fuelType;
		}
		public int getProofType() {
			return ProofType;
		}
		public void setProofType(int proofType) {
			ProofType = proofType;
		}
		public int getLicenseColor() {
			return LicenseColor;
		}
		public void setLicenseColor(int licenseColor) {
			LicenseColor = licenseColor;
		}
		public int getClauseType() {
			return ClauseType;
		}
		public void setClauseType(int clauseType) {
			ClauseType = clauseType;
		}
		public int getRunRegion() {
			return RunRegion;
		}
		public void setRunRegion(int runRegion) {
			RunRegion = runRegion;
		}
		public String getRisk() {
			return Risk;
		}
		public void setRisk(String risk) {
			Risk = risk;
		}
	}
	
	public Result<PolicySchema> schema() {
		if (this.Item.QuoteStatus <= 0)
			return Result.result(BtkjCode.QUOTE_FAILURE, this.Item.QuoteResult);
		org.btkj.pojo.bo.PolicySchema result = new org.btkj.pojo.bo.PolicySchema();
		Map<CommercialInsuranceType, Insurance> insurances = new HashMap<CommercialInsuranceType, Insurance>();
		if (null != this.UserInfo) {
			result.setCompulsiveStart(this.UserInfo.ForceStartDate);
			result.setCompulsiveEnd(this.UserInfo.ForceExpireDate);
			result.setCommercialStart(this.UserInfo.BusinessStartDate);
			result.setCommercialEnd(this.UserInfo.BusinessExpireDate);
		}
		if (null != this.Item) {
			result.setCompulsiveTotal(this.Item.ForceTotal);
			result.setVehicleVesselTotal(this.Item.TaxTotal);
			result.setCommericialTotal(this.Item.BizTotal);
			if (null != this.Item.CheSun && this.Item.CheSun.BaoE != 0) 
				insurances.put(CommercialInsuranceType.DAMAGE, new Insurance(this.Item.CheSun.BaoE, this.Item.CheSun.BaoFei));
			if (null != this.Item.BuJiMianCheSun && this.Item.BuJiMianCheSun.BaoE != 0)
				insurances.put(CommercialInsuranceType.DAMAGE_DEDUCTIBLE, new Insurance(this.Item.BuJiMianCheSun.BaoE, this.Item.BuJiMianCheSun.BaoFei));
			if (null != this.Item.SanZhe && this.Item.SanZhe.BaoE != 0)
				insurances.put(CommercialInsuranceType.THIRD, new Insurance(this.Item.SanZhe.BaoE, this.Item.SanZhe.BaoFei));
			if (null != this.Item.BuJiMianSanZhe && this.Item.BuJiMianSanZhe.BaoE != 0)
				insurances.put(CommercialInsuranceType.THIRD_DEDUCTIBLE, new Insurance(this.Item.BuJiMianSanZhe.BaoE, this.Item.BuJiMianSanZhe.BaoFei));
			if (null != this.Item.SiJi && this.Item.SiJi.BaoE != 0)
				insurances.put(CommercialInsuranceType.DRIVER, new Insurance(this.Item.SiJi.BaoE, this.Item.SiJi.BaoFei));
			if (null != this.Item.BuJiMianSiJi && this.Item.BuJiMianSiJi.BaoE != 0)
				insurances.put(CommercialInsuranceType.DRIVER_DEDUCTIBLE, new Insurance(this.Item.BuJiMianSiJi.BaoE, this.Item.BuJiMianSiJi.BaoFei));
			if (null != this.Item.ChengKe && this.Item.ChengKe.BaoE != 0)
				insurances.put(CommercialInsuranceType.PASSENGER, new Insurance(this.Item.ChengKe.BaoE, this.Item.ChengKe.BaoFei));
			if (null != this.Item.BuJiMianChengKe && this.Item.BuJiMianChengKe.BaoE != 0)
				insurances.put(CommercialInsuranceType.PASSENGER_DEDUCTIBLE, new Insurance(this.Item.BuJiMianChengKe.BaoE, this.Item.BuJiMianChengKe.BaoFei));
			if (null != this.Item.DaoQiang && this.Item.DaoQiang.BaoE != 0)
				insurances.put(CommercialInsuranceType.ROBBERY, new Insurance(this.Item.DaoQiang.BaoE, this.Item.DaoQiang.BaoFei));
			if (null != this.Item.BuJiMianDaoQiang && this.Item.BuJiMianDaoQiang.BaoE != 0)
				insurances.put(CommercialInsuranceType.ROBBERY_DEDUCTIBLE, new Insurance(this.Item.BuJiMianDaoQiang.BaoE, this.Item.BuJiMianDaoQiang.BaoFei));
			if (null != this.Item.BoLi && this.Item.BoLi.BaoE != 0)
				insurances.put(CommercialInsuranceType.GLASS, new Insurance(this.Item.BoLi.BaoE, this.Item.BoLi.BaoFei));
			if (null != this.Item.ZiRan && this.Item.ZiRan.BaoE != 0)
				insurances.put(CommercialInsuranceType.AUTO_FIRE, new Insurance(this.Item.ZiRan.BaoE, this.Item.ZiRan.BaoFei));
			if (null != this.Item.BuJiMianZiRan && this.Item.BuJiMianZiRan.BaoE != 0)
				insurances.put(CommercialInsuranceType.AUTO_FIRE_DEDUCTIBLE, new Insurance(this.Item.BuJiMianZiRan.BaoE, this.Item.BuJiMianZiRan.BaoFei));
			if (null != this.Item.HuaHen && this.Item.HuaHen.BaoE != 0)
				insurances.put(CommercialInsuranceType.SCRATCH, new Insurance(this.Item.HuaHen.BaoE, this.Item.HuaHen.BaoFei));
			if (null != this.Item.BuJiMianHuaHen && this.Item.BuJiMianHuaHen.BaoE != 0)
				insurances.put(CommercialInsuranceType.SCRATCH_DEDUCTIBLE, new Insurance(this.Item.BuJiMianHuaHen.BaoE, this.Item.BuJiMianHuaHen.BaoFei));
			if (null != this.Item.SheShui && this.Item.SheShui.BaoE != 0)
				insurances.put(CommercialInsuranceType.WADDING, new Insurance(this.Item.SheShui.BaoE, this.Item.SheShui.BaoFei));
			if (null != this.Item.BuJiMianSheShui && this.Item.BuJiMianSheShui.BaoE != 0)
				insurances.put(CommercialInsuranceType.WADDING_DEDUCTIBLE, new Insurance(this.Item.BuJiMianSheShui.BaoE, this.Item.BuJiMianSheShui.BaoFei));
			if (-1 != this.Item.HcXiuLiChangType && null != this.Item.HcXiuLiChang && this.Item.HcXiuLiChang.BaoE != 0) {
				double quota = this.Item.HcXiuLiChangType == 1 ? 1 + this.Item.HcXiuLiChang.BaoE : this.Item.HcXiuLiChang.BaoE;
				insurances.put(CommercialInsuranceType.GARAGE_DESIGNATED, new Insurance(quota, this.Item.HcXiuLiChang.BaoFei));
			} if (null != this.Item.HcSanFangTeYue && this.Item.HcSanFangTeYue.BaoE != 0)
				insurances.put(CommercialInsuranceType.UNKNOWN_THIRD, new Insurance(this.Item.HcSanFangTeYue.BaoE, this.Item.HcSanFangTeYue.BaoFei));
		}
		result.setNoLossDiscountRate(this.Item.RateFactor1);
		result.setAutoChannelRate(this.Item.RateFactor2);
		result.setAutoUnderwritingRate(this.Item.RateFactor3);
		result.setTrafficViolationRate(this.Item.RateFactor4);
		result.setInsurances(insurances.isEmpty() ? null : insurances);
		return Result.result(result);
	}
}
