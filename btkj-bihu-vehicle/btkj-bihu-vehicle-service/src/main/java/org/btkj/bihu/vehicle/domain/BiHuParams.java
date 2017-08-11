package org.btkj.bihu.vehicle.domain;

import java.util.TreeMap;

public class BiHuParams extends TreeMap<String, String> {

	private static final long serialVersionUID = -143173514303834180L;

	protected static final String KEY							= "key";
	protected static final String AGENT							= "Agent";					// 调用平台标识
	protected static final String GROUP							= "Group";
	protected static final String CITY_CODE						= "CityCode";				// 城市ID
	protected static final String LICENSE_NO					= "LicenseNo";				// 车牌号
	protected static final String CAN_SHOW_NO					= "CanShowNo";
	protected static final String CAN_SHOW_EXHAUST_SCALE		= "CanShowExhaustScale";
	protected static final String SHOW_XIU_LI_CHANG_TYPE		= "ShowXiuLiChangType";
	protected static final String SHOW_EMAIL					= "ShowEmail";				// 是否展示邮箱：1：是 0：否（默认）
	protected static final String SHOW_VEHICLE_INFO				= "ShowVehicleInfo";
	protected static final String SHOW_CAR_INFO					= "ShowCarInfo";			// 展示其他业务 0：否  1：是
	protected static final String TIME_FORMAT					= "TimeFormat";
	protected static final String SHOW_AUTO_MOLD_CODE			= "ShowAutoMoldCode";
	protected static final String CUST_KEY						= "CustKey";				// 客户端标识（用来区分客户）（10-32位字符）
	protected static final String CAR_OWNERS_NAME				= "CarOwnersName";			// 车主姓名
	protected static final String ID_CARD						= "IdCard";					// 车主证件号
	protected static final String OWNER_ID_CARD_TYPE			= "OwnerIdCardType";		// 车主证件类型
	protected static final String QUOTE_GROUP					= "QuoteGroup";				// 需要报价的 保险资源的枚举值之和
	protected static final String SUBMIT_GROUP					= "SubmitGroup";			// 需要核保的 保险资源的枚举值之和（这个范围应该是QuoteGroup的子集，必须报价了，才可以核保）
	protected static final String INSURED_NAME					= "InsuredName";			// 被保险人姓名
	protected static final String INSURED_ID_CARD				= "InsuredIdCard";			// 被保险人证件号
	protected static final String INSURED_ID_TYPE				= "InsuredIdType";			// 被保险人证件类型
	protected static final String INSURED_MOBILE				= "InsuredMobile";			// 被保险人手机号
	protected static final String EMAIL							= "Email";					// 被保人邮箱(针对电子保单业务)
	protected static final String INSURED_ADDRESS				= "InsuredAddress";			// 被保险人地址
	protected static final String INSURED_CERTI_START_DATE		= "InsuredCertiStartdate";	// 被保险人身份证有效期起期（北京平安必填）
	protected static final String INSURED_CERTI_END_DATE		= "InsuredCertiEnddate";	// 被保险人身份证有效期止期（北京平安必填）
	protected static final String HOLDER_ID_CARD				= "HolderIdCard";			// 投保人证件号
	protected static final String HOLDER_NAME					= "HolderName";				// 投保人姓名
	protected static final String HOLDER_ID_TYPE				= "HolderIdType";			// 投保人证件类型
	protected static final String HOLDER_MOBILE					= "HolderMobile";			// 投保人手机号
	protected static final String HOLDER_ADDRESS				= "HolderAddress";			// 投保人地址
	protected static final String HOLDER_CERTI_START_DATE		= "HolderCertiStartdate";	// 投保人身份证有效期起期（北京平安必填）
	protected static final String HOLDER_CERTI_END_DATE			= "HolderCertiEnddate";		// 投保人身份证有效期止期（北京平安必填）
	protected static final String IS_NEW_CAR					= "IsNewCar";				// 是否新车
	protected static final String CAR_TYPE						= "CarType";				// 车辆类型
	protected static final String CAR_USED_TYPE					= "CarUsedType";			// 使用性质
	protected static final String ENGINE_NO						= "EngineNo";				// 发动机号
	protected static final String CAR_VIN						= "CarVin";					// 车架号
	protected static final String REGISTER_DATE					= "RegisterDate";			// 注册日期
	protected static final String MOLD_NAME						= "MoldName";				// 品牌型号
	protected static final String FORCE_TAX						= "ForceTax";				// 0:单商业 ，1：商业+交强车船，2：单交强+车船
	protected static final String BOLI							= "Boli";					// 玻璃单独破碎险，0-不投保，1国产，2进口
	protected static final String BU_JI_MIAN_CHE_SUN			= "BuJiMianCheSun";			// 不计免赔险(车损) ，0-不投保，1投保
	protected static final String BU_JI_MIAN_DAO_QIANG			= "BuJiMianDaoQiang";		// 不计免赔险(盗抢) ，0-不投保，1投保
	protected static final String BU_JI_MIAN_SAN_ZHE			= "BuJiMianSanZhe";			// 不计免赔险(三者) ，0-不投保，1投保
	protected static final String BU_JI_MIAN_CHENG_KE			= "BuJiMianChengKe";		// 不计免乘客0-不投保，1投保
	protected static final String BU_JI_MIAN_SI_JI				= "BuJiMianSiJi";			// 不计免司机0-不投保，1投保
	protected static final String BU_JI_MIAN_HUA_HEN			= "BuJiMianHuaHen";			// 不计免划痕0-不投保，1投保
	protected static final String BU_JI_MIAN_SHE_SHUI			= "BuJiMianSheShui";		// 不计免涉水0-不投保，1投保
	protected static final String BU_JI_MIAN_ZI_RAN				= "BuJiMianZiRan";			// 不计免自燃0-不投保，1投保
	protected static final String BU_JI_MIAN_JING_SHEN_SUN_SHI	= "BuJiMianJingShenSunShi";	// 不计免精神损失0-不投保，1投保
	protected static final String SHE_SHUI						= "SheShui";				// 涉水行驶损失险,0-不投保，1投保
	protected static final String HUA_HEN						= "HuaHen";					// 车身划痕损失险,0-不投保，>0投保(具体金额)：2000、5000、10000、20000
	protected static final String SI_JI							= "SiJi";					// 车上人员责任险(司机),0-不投保，>0投保(具体金额):10000、20000、30000、40000、50000、100000、200000(金额是单个座位的保额支持自定义1000-1000000)
	protected static final String CHENG_KE						= "ChengKe";				// 车上人员责任险(乘客),0-不投保，>0投保(具体金额)：10000、20000、30000、40000、50000、100000、200000(金额是单个座位的保额支持自定义1000-1000000)
	protected static final String CHE_SUN						= "CheSun";					// 机动车损失保险，0-不投保，1-投保
	protected static final String DAO_QIANG						= "DaoQiang";				// 全车盗抢保险，0-不投保，1-投保
	protected static final String SAN_ZHE						= "SanZhe";					// 第三者责任保险,0-不投保,>0投保(具体金额)：50000、100000、150000、200000、300000、500000、1000000、1500000
	protected static final String ZI_RAN						= "ZiRan";					// 自燃损失险，0-不投保，1投保
	protected static final String SEAT_COUNT					= "SeatCount";				// 核定载客量
	protected static final String TON_COUNT						= "TonCount";				// 核定载质量
	protected static final String HC_JING_SHEN_SUN_SHI			= "HcJingShenSunShi";		// 精神损失抚慰金责任险（0:不投,>0：保额）（前提是三者，司机，乘客至少有一个投保，保额支持自定义）
	protected static final String HC_SAN_FANG_TE_YUE			= "HcSanFangTeYue";			// 机动车损失保险无法找到第三方特约险（0:不投，1：投保）(前提必须上车损险)
	protected static final String HC_XIU_LI_CHANG				= "HcXiuLiChang";			// 指定修理厂险（0:不投，>0：保额）国产0.1-0.3  进口0.1-0.6
	protected static final String HC_XIU_LI_CHANG_TYPE			= "HcXiuLiChangType";		// 指定专修厂类型 -1没有 国产0 进口1
	protected static final String BIZ_TIME_STAMP				= "BizTimeStamp";			// 商业险起保时间（时间戳格式）单位是秒
	protected static final String FORCE_TIME_STAMP				= "ForceTimeStamp";			// 交强险起保时间（时间戳格式）单位是秒
	protected static final String TRANSFER_DATE					= "TransferDate";			// 过户车日期（yyyy-mm-dd）
	protected static final String PURCHASE_PRICE				= "PurchasePrice";			// 购置价格
	protected static final String NEGOTIATE_PRICE				= "NegotiatePrice";			// 协商价格
	protected static final String AUTO_MOLD_CODE_SOURCE			= "AutoMoldCodeSource";		// 按照车型类型报价:1.续保车型;2.自定义车型;3.最低配置车型
	protected static final String EXHAUST_SCALE					= "ExhaustScale";			// 排气量
	protected static final String BIZ_SHORT_END_DATE			= "BizShortEndDate";		// 商业险截止日期 时间戳格式，范围： 小于一年.
	
	private String key;
	
	public BiHuParams(RequestType type) {
		switch (type) {
		case RENEWL:
			setGroup(1);
			setCanShowNo(1);
			setCanShowExhaustScale(1);
			setShowXiuLiChangeType(1);
			setTimeFormat(1);
			setShowAutoMoldCode(1);
			break;
		case QUOTE_RESULT:
			setTimeFormat(1);
			setShowEmail(1);
			setShowXiuLiChangeType(1);
			setShowVehicleInfo(1);
			setShowCarInfo(1);
			break;
		default:
			break;
		}
	}
	
	public String getKey() {
		return key;
	}
	
	public BiHuParams setKey(String key) {
		this.key = key;
		return this;
	}
	
	public BiHuParams setAgent(String agent) {
		put(AGENT, agent);
		return this;
	}
	
	public BiHuParams setGroup(int group) {
		put(GROUP, String.valueOf(group));
		return this;
	}
	
	public BiHuParams setCityCode(int cityCode) {
		put(CITY_CODE, String.valueOf(cityCode));
		return this;
	}
	
	public BiHuParams setLicenseNo(String licenseNo) {
		put(LICENSE_NO, licenseNo);
		return this;
	}
	
	public BiHuParams setCanShowNo(int canShowNo) {
		put(CAN_SHOW_NO, String.valueOf(canShowNo));
		return this;
	}
	
	public BiHuParams setCanShowExhaustScale(int canShowExhaustScale) {
		put(CAN_SHOW_EXHAUST_SCALE, String.valueOf(canShowExhaustScale));
		return this;
	}
	
	public BiHuParams setShowXiuLiChangeType(int showXiuLiChangType) {
		put(SHOW_XIU_LI_CHANG_TYPE, String.valueOf(showXiuLiChangType));
		return this;
	}
	
	public BiHuParams setShowEmail(int showEmail) {
		put(SHOW_EMAIL, String.valueOf(showEmail));
		return this;
	} 
	
	public BiHuParams setShowVehicleInfo(int showVehicleInfo) {
		put(SHOW_VEHICLE_INFO, String.valueOf(showVehicleInfo));
		return this;
	}
	
	public BiHuParams setShowCarInfo(int showCarInfo) {
		put(SHOW_CAR_INFO, String.valueOf(showCarInfo));
		return this;
	}
	
	public BiHuParams setTimeFormat(int timeFormat) {
		put(TIME_FORMAT, String.valueOf(timeFormat));
		return this;
	}
	
	public BiHuParams setShowAutoMoldCode(int showAutoMoldCode) {
		put(SHOW_AUTO_MOLD_CODE, String.valueOf(showAutoMoldCode));
		return this;
	}
	
	public BiHuParams setCustKey(String custKey) {
		put(CUST_KEY, custKey);
		return this;
	}
	
	public BiHuParams setCarOwnerName(String carOwnerName) {
		put(CAR_OWNERS_NAME, carOwnerName);
		return this;
	}
	
	public BiHuParams setIdCard(String idCard) {
		put(ID_CARD, idCard);
		return this;
	}
	
	public BiHuParams setOwnerIdCardType(String ownerIdCardType) {
		put(OWNER_ID_CARD_TYPE, ownerIdCardType);
		return this;
	}
	
	public BiHuParams setQuoteGroup(int quoteGroup) {
		put(QUOTE_GROUP, String.valueOf(quoteGroup));
		return this;
	}
	
	public BiHuParams setSubmitGroup(int submitGroup) {
		put(SUBMIT_GROUP, String.valueOf(submitGroup));
		return this;
	}
	
	public BiHuParams setInsuredName(String insuredName) {
		put(INSURED_NAME, insuredName);
		return this;
	}
	
	public BiHuParams setInsuredIdCard(String insuredIdCard) {
		put(INSURED_ID_CARD, insuredIdCard);
		return this;
	}
	
	public BiHuParams setInsuredIdType(String insuredIdType) {
		put(INSURED_ID_TYPE, insuredIdType);
		return this;
	}
	
	public BiHuParams setInsuredMobile(String insuredMobile) {
		put(INSURED_MOBILE, insuredMobile);
		return this;
	}
	
	public BiHuParams setEmail(String email) {
		put(EMAIL, email);
		return this;
	}
	
	public BiHuParams setInsuredAddress(String insuredAddress) {
		put(INSURED_ADDRESS, insuredAddress);
		return this;
	}
	
	public BiHuParams setInsuredCertiStartdate(String insuredCertiStartdate) {
		put(INSURED_CERTI_START_DATE, insuredCertiStartdate);
		return this;
	}
	
	public BiHuParams setInsuredCertiEnddate(String insuredCertiEnddate) {
		put(INSURED_CERTI_END_DATE, insuredCertiEnddate);
		return this;
	}
	
	public BiHuParams setHolderIdCard(String holderIdCard) {
		put(HOLDER_ID_CARD, holderIdCard);
		return this;
	}
	
	public BiHuParams setHolderName(String holderName) {
		put(HOLDER_NAME, holderName);
		return this;
	}
	
	public BiHuParams setHolderIdType(String holderIdType) {
		put(HOLDER_ID_TYPE, holderIdType);
		return this;
	}
	
	public BiHuParams setHolderMobile(String holderMobile) {
		put(HOLDER_MOBILE, holderMobile);
		return this;
	}
	
	public BiHuParams setHolderAddress(String holderAddress) {
		put(HOLDER_ADDRESS, holderAddress);
		return this;
	}
	
	public BiHuParams setHolderCertiStartdate(String holderCertiStartdate) {
		put(HOLDER_CERTI_START_DATE, holderCertiStartdate);
		return this;
	}
	
	public BiHuParams setHolderCertiEnddate(String holderCertiEnddate) {
		put(HOLDER_CERTI_END_DATE, holderCertiEnddate);
		return this;
	}
	
	public BiHuParams setIsNewCar(String isNewCar) {
		put(IS_NEW_CAR, isNewCar);
		return this;
	}
	
	public BiHuParams setCarType(String carType) {
		put(CAR_TYPE, carType);
		return this;
	}
	
	public BiHuParams setCarUsedType(String carUsedType) {
		put(CAR_USED_TYPE, carUsedType);
		return this;
	}
	
	public BiHuParams setEngineNo(String engineNo) {
		put(ENGINE_NO, engineNo);
		return this;
	}
	
	public BiHuParams setCarVin(String vin) {
		put(CAR_VIN, vin);
		return this;
	}
	
	public BiHuParams setRegisterDate(String registerDate) {
		put(REGISTER_DATE, registerDate);
		return this;
	}
	
	public BiHuParams setMoldName(String moldName) {
		put(MOLD_NAME, moldName);
		return this;
	}
	
	public BiHuParams setForceTax(String forceTax) {
		put(FORCE_TAX, forceTax);
		return this;
	}
	
	public BiHuParams setBoLi(double boLi) {
		put(BOLI, String.valueOf(boLi));
		return this;
	}
	
	public BiHuParams setBuJiMianCheSun(double buJiMianCheSun) {
		put(BU_JI_MIAN_CHE_SUN, String.valueOf(buJiMianCheSun));
		return this;
	}
	
	public BiHuParams setBuJiMianDaoQiang(double buJiMianDaoQiang) {
		put(BU_JI_MIAN_DAO_QIANG, String.valueOf(buJiMianDaoQiang));
		return this;
	}
	
	public BiHuParams setBuJiMianSanZhe(double buJiMianSanZhe) {
		put(BU_JI_MIAN_SAN_ZHE, String.valueOf(buJiMianSanZhe));
		return this;
	}
	
	public BiHuParams setBuJiMianChengKe(double buJiMianChengKe) {
		put(BU_JI_MIAN_CHENG_KE, String.valueOf(buJiMianChengKe));
		return this;
	}
	
	public BiHuParams setBuJiMianSiJi(double buJiMianSiJi) {
		put(BU_JI_MIAN_SI_JI, String.valueOf(buJiMianSiJi));
		return this;
	}
	
	public BiHuParams setBuJiMianHuaHen(double buJiMianHuaHen) {
		put(BU_JI_MIAN_HUA_HEN, String.valueOf(buJiMianHuaHen));
		return this;
	}
	
	public BiHuParams setBuJiMianSheShui(double buJiMianSheShui) {
		put(BU_JI_MIAN_SHE_SHUI, String.valueOf(buJiMianSheShui));
		return this;
	}
	
	public BiHuParams setBuJiMianZiRan(double buJiMianZiRan) {
		put(BU_JI_MIAN_ZI_RAN, String.valueOf(buJiMianZiRan));
		return this;
	}
	
	public BiHuParams setBuJiMianJingShenSunShi(double buJiMianJingShenSunShi) {
		put(BU_JI_MIAN_JING_SHEN_SUN_SHI, String.valueOf(buJiMianJingShenSunShi));
		return this;
	}
	
	public BiHuParams setSheShui(double sheShui) {
		put(SHE_SHUI, String.valueOf(sheShui));
		return this;
	}
	
	public BiHuParams setHuaHen(double huaHen) {
		put(HUA_HEN, String.valueOf(huaHen));
		return this;
	}
	
	public BiHuParams setSiJi(double siJi) {
		put(SI_JI, String.valueOf(siJi));
		return this;
	}
	
	public BiHuParams setChengKe(double chengKe) {
		put(CHENG_KE, String.valueOf(chengKe));
		return this;
	}
	
	public BiHuParams setCheSun(double cheSun) {
		put(CHE_SUN, String.valueOf(cheSun));
		return this;
	}
	
	public BiHuParams setDaoQiang(double daoQiang) {
		put(DAO_QIANG, String.valueOf(daoQiang));
		return this;
	}
	
	public BiHuParams setSanZhe(double sanZhe) {
		put(SAN_ZHE, String.valueOf(sanZhe));
		return this;
	}
	
	public BiHuParams setZiRan(double ziRan) {
		put(ZI_RAN, String.valueOf(ziRan));
		return this;
	}
	
	public BiHuParams setSeatCount(String seatCount) {
		put(SEAT_COUNT, seatCount);
		return this;
	}
	
	public BiHuParams setTonCount(String tonCount) {
		put(TON_COUNT, tonCount);
		return this;
	}
	
	public BiHuParams setHcJingShenSunShi(String hcJingShenSunShi) {
		put(HC_JING_SHEN_SUN_SHI, hcJingShenSunShi);
		return this;
	}
	
	public BiHuParams setHcSanFangTeYue(double hcSanFangTeYue) {
		put(HC_SAN_FANG_TE_YUE, String.valueOf(hcSanFangTeYue));
		return this;
	}
	
	public BiHuParams setHcXiuLiChang(double hcXiuLiChang) {
		put(HC_XIU_LI_CHANG, String.valueOf(hcXiuLiChang));
		return this;
	}
	
	public BiHuParams setHcXiuLiChangType(int hcXiuLiChangType) {
		put(HC_XIU_LI_CHANG_TYPE, String.valueOf(hcXiuLiChangType));
		return this;
	}
	
	public BiHuParams setBizTimeStamp(String bizTimeStamp) {
		put(BIZ_TIME_STAMP, bizTimeStamp);
		return this;
	}
	
	public BiHuParams setForceTimeStamp(String forceTimeStamp) {
		put(FORCE_TIME_STAMP, forceTimeStamp);
		return this;
	}
	
	public BiHuParams setTransferDate(String transferDate) {
		put(TRANSFER_DATE, transferDate);
		return this;
	}
	
	public BiHuParams setPurchasePrice(String purchasePrice) {
		put(PURCHASE_PRICE, purchasePrice);
		return this;
	}
	
	public BiHuParams setNegotiatePrice(String negotiatePrice) {
		put(NEGOTIATE_PRICE, negotiatePrice);
		return this;
	}
	
	public BiHuParams setAutoMoldCode(String autoMoldCode) {
		put(AUTO_MOLD_CODE_SOURCE, autoMoldCode);
		return this;
	}
	
	public BiHuParams setExhaustScale(String exhaustScale) {
		put(EXHAUST_SCALE, exhaustScale);
		return this;
	}
	
	public BiHuParams setBizShortEndDate(String bizShortEndDate) {
		put(BIZ_SHORT_END_DATE, bizShortEndDate);
		return this;
	}
}
