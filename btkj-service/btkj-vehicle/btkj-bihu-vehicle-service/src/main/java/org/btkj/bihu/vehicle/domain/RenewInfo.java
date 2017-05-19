package org.btkj.bihu.vehicle.domain;

import java.io.Serializable;

import org.btkj.pojo.model.Renew;
import org.rapid.util.net.http.handler.SyncJsonRespHandler;
import org.rapid.util.net.http.handler.SyncRespHandler;

public class RenewInfo implements Serializable {
	
	private static final long serialVersionUID = 6265475310068561305L;
	
	public static final SyncRespHandler<RenewInfo> JSON_HANDLER			= SyncJsonRespHandler.build(RenewInfo.class);

	/**
	 * 1: 获取续保信息成功(车辆信息 + 险种) 如果是续保期外的车或者是投保我司对接外的其他保险公司的车辆，这种情况，只返回该车的投保截止日期
	 * 2: 失败，需要完善行驶证信息(车辆信息 + 险种都没有获取到)
	 * 3: 失败，获取用户车辆信息成功(车架号、发动机号、品牌型号、初登日期可以取到)，获取险种失败
	 * -10000: 输入的参数是否有空或者长度不符合要求
	 * -10001: 校验参数错误
	 * -10002: 获取续保信息失败
	 * -10003: 服务器发生异常
	 */
	private int BusinessStatus;
	private String StatusMessage;
	private String CustKey;
	private UserInfo UserInfo;
	private SaveQuote SaveQuote;
	
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
	
	public String getCustKey() {
		return CustKey;
	}
	
	public void setCustKey(String custKey) {
		CustKey = custKey;
	}
	
	public UserInfo getUserInfo() {
		return UserInfo;
	}
	
	public void setUserInfo(UserInfo userInfo) {
		UserInfo = userInfo;
	}
	
	public SaveQuote getSaveQuote() {
		return SaveQuote;
	}
	
	public void setSaveQuote(SaveQuote saveQuote) {
		SaveQuote = saveQuote;
	}
	
	/**
	 * 用户信息
	 * 
	 * @author ahab
	 */
	public class UserInfo {
		/**
		 * 使用性质
		 * 1：家庭自用车（默认）
		 * 2：党政机关、事业团体
		 * 3：非营业企业客车
		 * 4：不区分营业非营业（仅支持人保报价）
		 * 5：出租租赁（仅支持人保报价）
		 * 6：营业货车（仅支持人保报价）
		 * 7：非营业货车（仅支持人保报价）
		 * 8: 城市公交
		 */
		private int CarUsedType;
		private String LicenseNo;			// 车牌号
		private String LicenseOwner;		// 车主姓名
		private String PostedName;			// 投保人
		private String InsuredName;			// 被保险人
		private double PurchasePrice;		// 新车购置价格
		/**
		 * 证件类型
		 * 1：身份证
		 * 2: 组织机构代码证
		 * 3：护照
		 * 4：军官证
		 * 5：港澳回乡证或台胞证
		 * 6：其他
		 * 7:港澳通行证
		 * 8:出生证
		 * 9: 营业执照（社会统一信用代码）
		 * 10：税务登记证
		 */
		private int IdType;	
		private String CredentislasNum;			// 证件号码(车主本人)
		private int CityCode;					// 城市Id
		private String EngineNo;				// 发动机号
		private String ModleName;				// 品牌型号
		private String RegisterDate;			// 车辆注册日期
		private String CarVin;					// 车辆识别代码
		private String ForceExpireDate;			// 交强险到期时间
		private String BusinessExpireDate;		// 商业险到期时间
		private String NextForceStartDate;		// 下年的交强险起保日期
		private String NextBusinessStartDate;	// 下年的商业险起保日期
		private int SearCount;					// 座位数量
		private String InsuredIdCard;			// 被保人证件号
		private int InsuredIdType;				// 被保人证件类型(参考 IdType)
		private String InsuredMobile;			// 被保人手机号
		private String HolderIdCard;			// 投保人证件号
		private int HolderIdType;				// 投保人证件类型(参考 IdType)
		private String HolderMobile;			// 投保人联系方式
		private double RateFactor1;				// 费率系数1(无赔款系数)
		private double RateFactor2;				// 费率系数2(自主渠道系数)
		private double RateFactor3;				// 费率系数3(自主核保系数)
		private double RateFactor4;				// 费率系数4(交通非法浮动系数)
		/**
		 * 燃料种类
		 * 1：汽油
		 * 2：柴油
		 * 3：电
		 * 4：混合油
		 * 5:天然气
		 * 6:液化石油气
		 * 7:甲醇
		 * 8：乙醇
		 * 9:太阳能
		 * 10:混合动力
		 * 11:无
		 * 12:其它
		 */
		private int FuelType;
		/**
		 * 条款种类
		 * 1:非营业用汽车用品
		 * 2：家庭自用汽车产品
		 * 3：营业用汽车产品
		 * 4：摩托车产品
		 * 5：拖拉机产品
		 * 6：特种车产品
		 */
		private int ProofType;
		/**
		 * 条款类型
		 * 1：销售发票
		 * 2：法院调解书
		 * 3：法院仲裁书
		 * 4：法院判决书
		 * 5: 仲裁裁决书
		 * 6：相关文书
		 * 7：批准文件
		 * 8：调拨证明
		 * 9：修理发票
		 */
		private int ClauseType;
		/**
		 * 号牌底色
		 * 1：蓝
		 * 2：黑
		 * 3：白
		 * 4：黄
		 * 5：其他
		 */
		private int LicenseColor;
		/**
		 * 行驶区域
		 * 1：境内
		 * 2：本省内
		 * 3：其他
		 */
		private int RunRegion;
		/**
		 * 0：续保失败，无法获取该属性
		 * 1：公车
		 * 2：私车
		 */
		private int IsPublic;		
		private String BizNo;			// 商业险保单号
		private String ForceNo;			// 交强险保单号
		private String ExhaustScale;	// 排量信息(依赖于请求参数)
		private String AutoMoldCode;	// 精友码(续保成功，也有可能是空)
		
		public int getCarUsedType() {
			return CarUsedType;
		}
		
		public void setCarUsedType(int carUsedType) {
			CarUsedType = carUsedType;
		}
		
		public String getLicenseNo() {
			return LicenseNo;
		}
		
		public void setLicenseNo(String licenseNo) {
			LicenseNo = licenseNo;
		}
		
		public String getLicenseOwner() {
			return LicenseOwner;
		}
		
		public void setLicenseOwner(String licenseOwner) {
			LicenseOwner = licenseOwner;
		}
		
		public String getPostedName() {
			return PostedName;
		}
		
		public void setPostedName(String postedName) {
			PostedName = postedName;
		}
		
		public String getInsuredName() {
			return InsuredName;
		}
		
		public void setInsuredName(String insuredName) {
			InsuredName = insuredName;
		}
		
		public double getPurchasePrice() {
			return PurchasePrice;
		}
		
		public void setPurchasePrice(double purchasePrice) {
			PurchasePrice = purchasePrice;
		}
		
		public int getIdType() {
			return IdType;
		}
		
		public void setIdType(int idType) {
			IdType = idType;
		}
		
		public String getCredentislasNum() {
			return CredentislasNum;
		}
		
		public void setCredentislasNum(String credentislasNum) {
			CredentislasNum = credentislasNum;
		}
		
		public int getCityCode() {
			return CityCode;
		}
		
		public void setCityCode(int cityCode) {
			CityCode = cityCode;
		}
		
		public String getEngineNo() {
			return EngineNo;
		}
		
		public void setEngineNo(String engineNo) {
			EngineNo = engineNo;
		}
		
		public String getModleName() {
			return ModleName;
		}
		
		public void setModleName(String modleName) {
			ModleName = modleName;
		}
		
		public String getRegisterDate() {
			return RegisterDate;
		}
		
		public void setRegisterDate(String registerDate) {
			RegisterDate = registerDate;
		}
		
		public String getCarVin() {
			return CarVin;
		}
		
		public void setCarVin(String carVin) {
			CarVin = carVin;
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
		
		public String getNextForceStartDate() {
			return NextForceStartDate;
		}
		
		public void setNextForceStartDate(String nextForceStartDate) {
			NextForceStartDate = nextForceStartDate;
		}
		
		public String getNextBusinessStartDate() {
			return NextBusinessStartDate;
		}
		
		public void setNextBusinessStartDate(String nextBusinessStartDate) {
			NextBusinessStartDate = nextBusinessStartDate;
		}
		
		public int getSearCount() {
			return SearCount;
		}
		
		public void setSearCount(int searCount) {
			SearCount = searCount;
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
		
		public double getRateFactor1() {
			return RateFactor1;
		}
		
		public void setRateFactor1(double rateFactor1) {
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
		
		public int getClauseType() {
			return ClauseType;
		}
		
		public void setClauseType(int clauseType) {
			ClauseType = clauseType;
		}
		
		public int getLicenseColor() {
			return LicenseColor;
		}
		
		public void setLicenseColor(int licenseColor) {
			LicenseColor = licenseColor;
		}
		
		public int getRunRegion() {
			return RunRegion;
		}
		
		public void setRunRegion(int runRegion) {
			RunRegion = runRegion;
		}
		
		public int getIsPublic() {
			return IsPublic;
		}
		
		public void setIsPublic(int isPublic) {
			IsPublic = isPublic;
		}
		
		public String getBizNo() {
			return BizNo;
		}
		
		public void setBizNo(String bizNo) {
			BizNo = bizNo;
		}
		
		public String getForceNo() {
			return ForceNo;
		}
		
		public void setForceNo(String forceNo) {
			ForceNo = forceNo;
		}
		
		public String getExhaustScale() {
			return ExhaustScale;
		}
		
		public void setExhaustScale(String exhaustScale) {
			ExhaustScale = exhaustScale;
		}
		
		public String getAutoMoldCode() {
			return AutoMoldCode;
		}
		
		public void setAutoMoldCode(String autoMoldCode) {
			AutoMoldCode = autoMoldCode;
		}
	}
	public class SaveQuote {
		private int Source;		// 资源枚举列表
		private double CheSun;				// 车损保额
		private double SanZhe;				// 第三方责任险保额
		private double DaoQiang;			// 全车盗抢保险保额
		private double SiJi;				// 车上人员责任险(司机)保额
		private double ChengKe;				// 车上人员责任险(乘客)保额
		private double BoLi;				// 玻璃单独破碎险保额：0-不投保；1-国产；2-进口
		private double HuaHen;				// 车身划痕损失险保额
		private double BuJiMianCheSun;		// 不计免赔险(车损)保额
		private double BuJiMianSanZhe;		// 不计免赔险(三者)保额
		private double BuJiMianDaoQiang;	// 不计免赔险(盗抢)保额
		private double SheShui;				// 涉水行驶损失险保额
		private double ZiRan;				// 自燃损失险保额
		private double BuJiMianChengKe;		// 不计免乘客保额
		private double BuJiMianSiJi;		// 不计免司机保额
		private double BuJiMianHuaHen;		// 不计免划痕保额
		private double BuJiMianSheShui;		// 不计免涉水保额
		private double BuJiMianZiRan;		// 不计免自然保额
		private double BuJiMianJingShenSunShi;		// 不计免精神损失保额
		private double HcSanFangTeYue;				// 机动车无法找到三方特约险保额
		private double HcJingShenSunShi;			// 精神损失险保额
		private String HcXiuLiChangType;			// 指定专修厂类型：-1-没有；0-国产；1-进口(依赖于请求参数)
		private String HcXiuLiChange;				// 指定修理厂险(依赖于请求参数)
		
		public int getSource() {
			return Source;
		}
		
		public void setSource(int source) {
			Source = source;
		}
		
		public double getCheSun() {
			return CheSun;
		}
		
		public void setCheSun(double cheSun) {
			CheSun = cheSun;
		}
		
		public double getSanZhe() {
			return SanZhe;
		}
		
		public void setSanZhe(double sanZhe) {
			SanZhe = sanZhe;
		}
		
		public double getDaoQiang() {
			return DaoQiang;
		}
		
		public void setDaoQiang(double daoQiang) {
			DaoQiang = daoQiang;
		}
		
		public double getSiJi() {
			return SiJi;
		}
		
		public void setSiJi(double siJi) {
			SiJi = siJi;
		}
		
		public double getChengKe() {
			return ChengKe;
		}
		
		public void setChengKe(double chengKe) {
			ChengKe = chengKe;
		}
		
		public double getBoLi() {
			return BoLi;
		}
		
		public void setBoLi(double boLi) {
			BoLi = boLi;
		}
		
		public double getHuaHen() {
			return HuaHen;
		}
		
		public void setHuaHen(double huaHen) {
			HuaHen = huaHen;
		}
		
		public double getBuJiMianCheSun() {
			return BuJiMianCheSun;
		}
		
		public void setBuJiMianCheSun(double buJiMianCheSun) {
			BuJiMianCheSun = buJiMianCheSun;
		}
		
		public double getBuJiMianSanZhe() {
			return BuJiMianSanZhe;
		}
		
		public void setBuJiMianSanZhe(double buJiMianSanZhe) {
			BuJiMianSanZhe = buJiMianSanZhe;
		}
		
		public double getBuJiMianDaoQiang() {
			return BuJiMianDaoQiang;
		}
		
		public void setBuJiMianDaoQiang(double buJiMianDaoQiang) {
			BuJiMianDaoQiang = buJiMianDaoQiang;
		}
		
		public double getSheShui() {
			return SheShui;
		}
		
		public void setSheShui(double sheShui) {
			SheShui = sheShui;
		}
		
		public double getZiRan() {
			return ZiRan;
		}
		
		public void setZiRan(double ziRan) {
			ZiRan = ziRan;
		}
		
		public double getBuJiMianChengKe() {
			return BuJiMianChengKe;
		}
		
		public void setBuJiMianChengKe(double buJiMianChengKe) {
			BuJiMianChengKe = buJiMianChengKe;
		}
		
		public double getBuJiMianSiJi() {
			return BuJiMianSiJi;
		}
		
		public void setBuJiMianSiJi(double buJiMianSiJi) {
			BuJiMianSiJi = buJiMianSiJi;
		}
		
		public double getBuJiMianHuaHen() {
			return BuJiMianHuaHen;
		}
		
		public void setBuJiMianHuaHen(double buJiMianHuaHen) {
			BuJiMianHuaHen = buJiMianHuaHen;
		}
		
		public double getBuJiMianSheShui() {
			return BuJiMianSheShui;
		}
		
		public void setBuJiMianSheShui(double buJiMianSheShui) {
			BuJiMianSheShui = buJiMianSheShui;
		}
		
		public double getBuJiMianZiRan() {
			return BuJiMianZiRan;
		}
		
		public void setBuJiMianZiRan(double buJiMianZiRan) {
			BuJiMianZiRan = buJiMianZiRan;
		}
		
		public double getBuJiMianJingShenSunShi() {
			return BuJiMianJingShenSunShi;
		}
		
		public void setBuJiMianJingShenSunShi(double buJiMianJingShenSunShi) {
			BuJiMianJingShenSunShi = buJiMianJingShenSunShi;
		}
		
		public double getHcSanFangTeYue() {
			return HcSanFangTeYue;
		}
		
		public void setHcSanFangTeYue(double hcSanFangTeYue) {
			HcSanFangTeYue = hcSanFangTeYue;
		}
		
		public double getHcJingShenSunShi() {
			return HcJingShenSunShi;
		}
		
		public void setHcJingShenSunShi(double hcJingShenSunShi) {
			HcJingShenSunShi = hcJingShenSunShi;
		}
		
		public String getHcXiuLiChangType() {
			return HcXiuLiChangType;
		}
		
		public void setHcXiuLiChangType(String hcXiuLiChangType) {
			HcXiuLiChangType = hcXiuLiChangType;
		}
		
		public String getHcXiuLiChange() {
			return HcXiuLiChange;
		}
		
		public void setHcXiuLiChange(String hcXiuLiChange) {
			HcXiuLiChange = hcXiuLiChange;
		}
	}
	
	public Renew toRenew() {
		Renew renew = new Renew();
		
		return new Renew();
	}
}
