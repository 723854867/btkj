package org.btkj.lebaoba.vehicle.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.btkj.pojo.bo.InsurUnit;
import org.btkj.pojo.bo.PolicySchema;
import org.btkj.pojo.enums.VehicleUsedType;
import org.btkj.pojo.vo.VehiclePolicyTips;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.lang.StringUtil;

@XmlRootElement(name = "CheXianXML")
public class OrderSubmit {

	private LogUser logUser;
	private int ProxyCompanyID;
	private String CompanyProvince;
	private String ProductCode;
	private String CompulsoryPolicyEndDate;
	private String CompulsoryPolicyBeginDate;
	private String CommercePolicyEndDate;
	private String commercePolicyBeginDate;
	private String rateTemp;					// 无赔优惠系数
	private VehicleInfo vehicleInfo;
	private InsuredInfo BeInsuredInfo;
	private InsuredInfo ToInsuredInfo;
	private List<VehicleInsuranceItem> VehicleInsurance;

	@XmlElement(name = "logUser")
	public LogUser getLogUser() {
		return logUser;
	}

	public void setLogUser(LogUser logUser) {
		this.logUser = logUser;
	}

	@XmlElement(name = "ProxyCompanyID")
	public int getProxyCompanyID() {
		return ProxyCompanyID;
	}
	
	public void setProxyCompanyID(int proxyCompanyID) {
		ProxyCompanyID = proxyCompanyID;
	}

	@XmlElement(name = "CompanyProvince")
	public String getCompanyProvince() {
		return CompanyProvince;
	}

	public void setCompanyProvince(String companyProvince) {
		CompanyProvince = companyProvince;
	}

	@XmlElement(name = "PruductCode")
	public String getProductCode() {
		return ProductCode;
	}

	public void setProductCode(String productCode) {
		ProductCode = productCode;
	}

	@XmlElement(name = "CompulsoryPolicyEndDate")
	public String getCompulsoryPolicyEndDate() {
		return CompulsoryPolicyEndDate;
	}

	public void setCompulsoryPolicyEndDate(String compulsoryPolicyEndDate) {
		CompulsoryPolicyEndDate = compulsoryPolicyEndDate;
	}

	@XmlElement(name = "CompulsoryPolicyBeginDate")
	public String getCompulsoryPolicyBeginDate() {
		return CompulsoryPolicyBeginDate;
	}

	public void setCompulsoryPolicyBeginDate(String compulsoryPolicyBeginDate) {
		CompulsoryPolicyBeginDate = compulsoryPolicyBeginDate;
	}

	@XmlElement(name = "CommercePolicyEndDate")
	public String getCommercePolicyEndDate() {
		return CommercePolicyEndDate;
	}

	public void setCommercePolicyEndDate(String commercePolicyEndDate) {
		CommercePolicyEndDate = commercePolicyEndDate;
	}

	@XmlElement(name = "CommercePolicyBeginDate")
	public String getCommercePolicyBeginDate() {
		return commercePolicyBeginDate;
	}

	public void setCommercePolicyBeginDate(String commercePolicyBeginDate) {
		this.commercePolicyBeginDate = commercePolicyBeginDate;
	}
	
	@XmlElement(name = "rateTemp")
	public String getRateTemp() {
		return rateTemp;
	}
	
	public void setRateTemp(String rateTemp) {
		this.rateTemp = rateTemp;
	}

	@XmlElement(name = "vehicleInfo")
	public VehicleInfo getVehicleInfo() {
		return vehicleInfo;
	}

	public void setVehicleInfo(VehicleInfo vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}
	
	@XmlElement(name = "BeInsuredInfo")
	public InsuredInfo getBeInsuredInfo() {
		return BeInsuredInfo;
	}
	
	public void setBeInsuredInfo(InsuredInfo beInsuredInfo) {
		BeInsuredInfo = beInsuredInfo;
	}

	@XmlElement(name = "ToInsuredInfo")
	public InsuredInfo getToInsuredInfo() {
		return ToInsuredInfo;
	}

	public void setToInsuredInfo(InsuredInfo toInsuredInfo) {
		ToInsuredInfo = toInsuredInfo;
	}
	
	@XmlElementWrapper(name = "VehicleInsurance")
	@XmlElement(name = "VehicleInsuranceItem")
	public List<VehicleInsuranceItem> getVehicleInsurance() {
		return VehicleInsurance;
	}

	public void setVehicleInsurance(List<VehicleInsuranceItem> vehicleInsurance) {
		VehicleInsurance = vehicleInsurance;
	}
	
	public static final OrderSubmit instance(String username, String password, String productCode, VehiclePolicyTips tips) {
		PolicySchema schema = tips.getSchema();
		OrderSubmit submit = new OrderSubmit();
		submit.setProductCode(productCode);
		if (StringUtil.hasText(schema.getCommercialStart())) {
			submit.setCommercePolicyBeginDate(DateUtil.convert(schema.getCommercialStart(), DateUtil.YYYY_MM_DD_HH_MM_SS, DateUtil.YYYY_MM_DDTHH_MM_SS, DateUtil.TIMEZONE_GMT_8));
			submit.setCommercePolicyEndDate(DateUtil.dateOyearTail(schema.getCommercialStart(), DateUtil.YYYY_MM_DD_HH_MM_SS, DateUtil.YYYY_MM_DDTHH_MM_SS, DateUtil.TIMEZONE_GMT_8));
			schema.setCommercialEnd(DateUtil.convert(submit.getCommercePolicyEndDate(), DateUtil.YYYY_MM_DDTHH_MM_SS, DateUtil.YYYY_MM_DD_HH_MM_SS, DateUtil.TIMEZONE_GMT_8));
		}
		if (StringUtil.hasText(schema.getCompulsiveStart())) {
			submit.setCompulsoryPolicyBeginDate(DateUtil.convert(schema.getCompulsiveStart(), DateUtil.YYYY_MM_DD_HH_MM_SS, DateUtil.YYYY_MM_DDTHH_MM_SS, DateUtil.TIMEZONE_GMT_8));
			submit.setCompulsoryPolicyEndDate(DateUtil.dateOyearTail(schema.getCompulsiveStart(), DateUtil.YYYY_MM_DD_HH_MM_SS, DateUtil.YYYY_MM_DDTHH_MM_SS, DateUtil.TIMEZONE_GMT_8));
			schema.setCompulsiveEnd(DateUtil.convert(submit.getCompulsoryPolicyEndDate(), DateUtil.YYYY_MM_DDTHH_MM_SS, DateUtil.YYYY_MM_DD_HH_MM_SS, DateUtil.TIMEZONE_GMT_8));
		}
		
		LogUser logUser = new LogUser();
		logUser.setUsername(username);
		logUser.setPassword(password);
		submit.setLogUser(logUser);
		
		InsuredInfo insuredInfo = new InsuredInfo();
		InsurUnit unit = tips.getInsured();
		insuredInfo.setIdType(LeBaoBaIdType.convert(unit.getIdType()).mark());
		insuredInfo.setName(unit.getName());
		insuredInfo.setIdNo(unit.getIdNo());
		insuredInfo.setMobile(unit.getMobile());
		CustomerType customerType = CustomerType.convert(unit.getType());
		insuredInfo.setCustomerType(null == customerType ? "01" : customerType.mark());
		submit.setBeInsuredInfo(insuredInfo);
		
		insuredInfo = new InsuredInfo();
		unit = tips.getInsurer();
		insuredInfo.setIdType(LeBaoBaIdType.convert(unit.getIdType()).mark());
		insuredInfo.setName(unit.getName());
		insuredInfo.setIdNo(unit.getIdNo());
		insuredInfo.setMobile(unit.getMobile());
		customerType = CustomerType.convert(unit.getType());
		insuredInfo.setCustomerType(null == customerType ? "01" : customerType.mark());
		submit.setToInsuredInfo(insuredInfo);
		
		VehicleInfo vehicleInfo = new VehicleInfo();
		vehicleInfo.setLicenseNo(tips.getLicense());
		vehicleInfo.setVin(tips.getVin());
		vehicleInfo.setEngineNo(tips.getEngine());
		vehicleInfo.setEnrollDate(DateUtil.convert(tips.getEnrollDate(), DateUtil.YYYY_MM_DD_HH_MM_SS, DateUtil.YYYY_MM_DDTHH_MM_SS, DateUtil.TIMEZONE_GMT_8));
		vehicleInfo.setTransferFlag(tips.isTransfer() ? 1 : 0);
		vehicleInfo.setTransferFlagTime(!StringUtil.hasText(tips.getIssueDate()) ? null : DateUtil.convert(tips.getIssueDate(), DateUtil.YYYY_MM_DD_HH_MM_SS, DateUtil.YYYY_MM_DDTHH_MM_SS, DateUtil.TIMEZONE_GMT_8));
		vehicleInfo.setUseNature(_useNature(tips.getVehicleUsedType()));
		vehicleInfo.setModelCode(tips.getName());
		vehicleInfo.setLicenseTypeCode("02");			// 默认小型汽车
		vehicleInfo.setCarTypeCode("K33");
		vehicleInfo.setVehicleType(1);
		vehicleInfo.setVehicleTypeCode("A012");
		vehicleInfo.setPrice(String.valueOf(tips.getPrice()));
		vehicleInfo.setPriceNoTax(String.valueOf(tips.getPriceNoTax()));
		vehicleInfo.setYear(String.valueOf(tips.getYear()));
		vehicleInfo.setSeat(tips.getSeat());
		vehicleInfo.setExhaust(tips.getExhaust());
		vehicleInfo.setLoadWeight(tips.getLoad());
		UnitInfo owner = new UnitInfo();
		unit = tips.getOwner();
		owner.setIdType(LeBaoBaIdType.convert(unit.getIdType()).mark());
		owner.setName(unit.getName());
		owner.setIdNo(unit.getIdNo());
		owner.setMobile(unit.getMobile());
		customerType = CustomerType.convert(unit.getType());
		owner.setCustomerType(null == customerType ? "01" : customerType.mark());
		vehicleInfo.setCarOwnerInfo(owner);
		submit.setVehicleInfo(vehicleInfo);
		submit.setVehicleInsurance(LeBaoBaInsurance.insuranceMapping(tips, submit.getCommercePolicyBeginDate(), DateUtil.convert(tips.getEnrollDate(), DateUtil.YYYY_MM_DD, DateUtil.YYYY_MM_DDTHH_MM_SS, DateUtil.TIMEZONE_GMT_8)));
		return submit;
	}
	
	private static int _useNature(VehicleUsedType usedType) {
		switch (usedType) {
		case HOME_USE:
		case ENTERPRISE:
		case ORGAN:
		case NO_BIZ_TRUCK:
		case MOTOR:
		case PARTICULAR:
		case TRACTOR:
			return 2;
		default:
			return 1;
		}
	}

	private static class LogUser {
		private String Username;
		private String Password;
		@XmlElement(name = "Username")
		public String getUsername() {
			return Username;
		}
		public void setUsername(String username) {
			Username = username;
		}
		@XmlElement(name = "Password")
		public String getPassword() {
			return Password;
		}
		public void setPassword(String password) {
			Password = password;
		}
	}

	public static class VehicleInfo {
		private String ID;
		private int LicenseFlag;
		private Integer NonLocalFlag;
		private String LicenseNo;
		private String Vin;
		private String EngineNo;
		private String ModelCode;
		private String EnrollDate;
		private int LoanFlag;
		private int TransferFlag;
		private String TransferFlagTime;
		private String LicenseTypeCode;
		private String CarTypeCode;
		private int VehicleType;
		private String VehicleTypeCode;
		private String VehicleTypeDetailCode;
		private int UseNature;
		private String CountryNature;
		private Integer IsRenewal;
		private String InsVehicleId;
		private String Price;
		private String PriceNoTax;
		private String Year;
		private String Name;
		private String Exhaust;
		private String BrandName;
		private String LoadWeight;
		private String KerbWeight;
		private int Seat;
		private Integer TaxType = 1;
		private String VehicleIMG = "1";
		private UnitInfo CarOwnerInfo;
		@XmlElement(name = "ID")
		public String getID() {
			return ID;
		}
		public void setID(String iD) {
			ID = iD;
		}
		@XmlElement(name = "LicenseFlag")
		public int getLicenseFlag() {
			return LicenseFlag;
		}
		public void setLicenseFlag(int licenseFlag) {
			LicenseFlag = licenseFlag;
		}
		@XmlElement(name = "NonLocalFlag")
		public Integer getNonLocalFlag() {
			return NonLocalFlag;
		}
		public void setNonLocalFlag(Integer nonLocalFlag) {
			NonLocalFlag = nonLocalFlag;
		}
		@XmlElement(name = "LicenseNo")
		public String getLicenseNo() {
			return LicenseNo;
		}
		public void setLicenseNo(String licenseNo) {
			LicenseNo = licenseNo;
		}
		@XmlElement(name = "Vin")
		public String getVin() {
			return Vin;
		}
		public void setVin(String vin) {
			Vin = vin;
		}
		@XmlElement(name = "EngineNo")
		public String getEngineNo() {
			return EngineNo;
		}
		public void setEngineNo(String engineNo) {
			EngineNo = engineNo;
		}
		@XmlElement(name = "ModelCode")
		public String getModelCode() {
			return ModelCode;
		}
		public void setModelCode(String modelCode) {
			ModelCode = modelCode;
		}
		@XmlElement(name = "EnrollDate")
		public String getEnrollDate() {
			return EnrollDate;
		}
		public void setEnrollDate(String enrollDate) {
			EnrollDate = enrollDate;
		}
		@XmlElement(name = "LoanFlag")
		public int getLoanFlag() {
			return LoanFlag;
		}
		public void setLoanFlag(int loanFlag) {
			LoanFlag = loanFlag;
		}
		@XmlElement(name = "TransferFlag")
		public int getTransferFlag() {
			return TransferFlag;
		}
		public void setTransferFlag(int transferFlag) {
			TransferFlag = transferFlag;
		}
		@XmlElement(name = "TransferFlagTime")
		public String getTransferFlagTime() {
			return TransferFlagTime;
		}
		public void setTransferFlagTime(String transferFlagTime) {
			TransferFlagTime = transferFlagTime;
		}
		@XmlElement(name = "LicenseTypeCode")
		public String getLicenseTypeCode() {
			return LicenseTypeCode;
		}
		public void setLicenseTypeCode(String licenseTypeCode) {
			LicenseTypeCode = licenseTypeCode;
		}
		@XmlElement(name = "CarTypeCode")
		public String getCarTypeCode() {
			return CarTypeCode;
		}
		public void setCarTypeCode(String carTypeCode) {
			CarTypeCode = carTypeCode;
		}
		@XmlElement(name = "VehicleType")
		public int getVehicleType() {
			return VehicleType;
		}
		public void setVehicleType(int vehicleType) {
			VehicleType = vehicleType;
		}
		@XmlElement(name = "VehicleTypeCode")
		public String getVehicleTypeCode() {
			return VehicleTypeCode;
		}
		public void setVehicleTypeCode(String vehicleTypeCode) {
			VehicleTypeCode = vehicleTypeCode;
		}
		@XmlElement(name = "VehicleTypeDetailCode")
		public String getVehicleTypeDetailCode() {
			return VehicleTypeDetailCode;
		}
		public void setVehicleTypeDetailCode(String vehicleTypeDetailCode) {
			VehicleTypeDetailCode = vehicleTypeDetailCode;
		}
		@XmlElement(name = "UseNature")
		public int getUseNature() {
			return UseNature;
		}
		public void setUseNature(int useNature) {
			UseNature = useNature;
		}
		@XmlElement(name = "CountryNature")
		public String getCountryNature() {
			return CountryNature;
		}
		public void setCountryNature(String countryNature) {
			CountryNature = countryNature;
		}
		@XmlElement(name = "IsRenewal")
		public Integer getIsRenewal() {
			return IsRenewal;
		}
		public void setIsRenewal(Integer isRenewal) {
			IsRenewal = isRenewal;
		}
		@XmlElement(name = "InsVehicleId")
		public String getInsVehicleId() {
			return InsVehicleId;
		}
		public void setInsVehicleId(String insVehicleId) {
			InsVehicleId = insVehicleId;
		}
		@XmlElement(name = "Price")
		public String getPrice() {
			return Price;
		}
		public void setPrice(String price) {
			Price = price;
		}
		@XmlElement(name = "PriceNoTax")
		public String getPriceNoTax() {
			return PriceNoTax;
		}
		public void setPriceNoTax(String priceNoTax) {
			PriceNoTax = priceNoTax;
		}
		@XmlElement(name = "Year")
		public String getYear() {
			return Year;
		}
		public void setYear(String year) {
			Year = year;
		}
		@XmlElement(name = "Name")
		public String getName() {
			return Name;
		}
		public void setName(String name) {
			Name = name;
		}
		@XmlElement(name = "Exhaust")
		public String getExhaust() {
			return Exhaust;
		}
		public void setExhaust(String exhaust) {
			Exhaust = exhaust;
		}
		@XmlElement(name = "BrandName")
		public String getBrandName() {
			return BrandName;
		}
		public void setBrandName(String brandName) {
			BrandName = brandName;
		}
		@XmlElement(name = "LoadWeight")
		public String getLoadWeight() {
			return LoadWeight;
		}
		public void setLoadWeight(String loadWeight) {
			LoadWeight = loadWeight;
		}
		@XmlElement(name = "KerbWeight")
		public String getKerbWeight() {
			return KerbWeight;
		}
		public void setKerbWeight(String kerbWeight) {
			KerbWeight = kerbWeight;
		}
		@XmlElement(name = "Seat")
		public int getSeat() {
			return Seat;
		}
		public void setSeat(int seat) {
			Seat = seat;
		}
		@XmlElement(name = "TaxType")
		public Integer getTaxType() {
			return TaxType;
		}
		public void setTaxType(Integer taxType) {
			TaxType = taxType;
		}
		@XmlElement(name = "VehicleIMG")
		public String getVehicleIMG() {
			return VehicleIMG;
		}
		public void setVehicleIMG(String vehicleIMG) {
			VehicleIMG = vehicleIMG;
		}
		@XmlElement(name = "CarOwnerInfo")
		public UnitInfo getCarOwnerInfo() {
			return CarOwnerInfo;
		}
		public void setCarOwnerInfo(UnitInfo carOwnerInfo) {
			CarOwnerInfo = carOwnerInfo;
		}
	}

	public static class UnitInfo {
		private String CustomerType;		// 客户类型   01-个人，02-机关，03-企业
		private String IdType;				// 证件类型 01-身份证 驾驶证-02, 军人证-03，护照-04，临时身份证-05，港澳通行证-06，台湾通行证-07     21-组织机构代码 22-税务登记证  23-营业执照（三证合一）  24-其他证件
		private String Name;
		private String IdNo;				// 证件号
		private String Address;				// 地址
		private String Mobile;				// 电话
		private Integer Sex;				// 性别
		private String Birthday;			// 出生日期
		private Integer age;				// 年龄
		@XmlElement(name = "CustomerType")				
		public String getCustomerType() {
			return CustomerType;
		}
		public void setCustomerType(String customerType) {
			CustomerType = customerType;
		}
		@XmlElement(name = "IdType")				
		public String getIdType() {
			return IdType;
		}
		public void setIdType(String idType) {
			IdType = idType;
		}
		@XmlElement(name = "Name")				
		public String getName() {
			return Name;
		}
		public void setName(String name) {
			Name = name;
		}
		@XmlElement(name = "IdNo")				
		public String getIdNo() {
			return IdNo;
		}
		public void setIdNo(String idNo) {
			IdNo = idNo;
		}
		@XmlElement(name = "Address")				
		public String getAddress() {
			return Address;
		}
		public void setAddress(String address) {
			Address = address;
		}
		@XmlElement(name = "Mobile")				
		public String getMobile() {
			return Mobile;
		}
		public void setMobile(String mobile) {
			Mobile = mobile;
		}
		@XmlElement(name = "Sex")				
		public Integer getSex() {
			return Sex;
		}
		public void setSex(Integer sex) {
			Sex = sex;
		}
		@XmlElement(name = "Birthday")				
		public String getBirthday() {
			return Birthday;
		}
		public void setBirthday(String birthday) {
			Birthday = birthday;
		}
		@XmlElement(name = "Age")				
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
	}

	public static class InsuredInfo extends UnitInfo {
		private String GeneralNumber;				// 总机号码，客户类型为 02 和 03 时赋值(只针对平安)
		private String LinkmanName;					// 联系人姓名，客户类型为 02 和 03 时赋值(只针对平安)
		@XmlElement(name = "GeneralNumber")			
		public String getGeneralNumber() {
			return GeneralNumber;
		}
		public void setGeneralNumber(String generalNumber) {
			GeneralNumber = generalNumber;
		}
		@XmlElement(name = "LinkmanName")				
		public String getLinkmanName() {
			return LinkmanName;
		}
		public void setLinkmanName(String linkmanName) {
			LinkmanName = linkmanName;
		}
	}

	public static class VehicleInsuranceItem {
		private String Code;
		private String Amount;
		private Integer Quantity;
		private Integer UnitAmount;
		public VehicleInsuranceItem() {}
		public VehicleInsuranceItem(LeBaoBaInsurance insurance) {
			this.Code = insurance.name();
		}
		public VehicleInsuranceItem(LeBaoBaInsurance insurance, String amount) {
			this.Code = insurance.name();
			this.Amount = amount;
		}
		@XmlElement(name = "Code")
		public String getCode() {
			return Code;
		}
		public void setCode(String code) {
			Code = code;
		}
		@XmlElement(name = "Amount")
		public String getAmount() {
			return Amount;
		}
		public void setAmount(String amount) {
			Amount = amount;
		}
		@XmlElement(name = "Quantity")
		public Integer getQuantity() {
			return Quantity;
		}
		public void setQuantity(Integer quantity) {
			Quantity = quantity;
		}
		@XmlElement(name = "UnitAmount")
		public Integer getUnitAmount() {
			return UnitAmount;
		}
		public void setUnitAmount(Integer unitAmount) {
			UnitAmount = unitAmount;
		}
	}
}
