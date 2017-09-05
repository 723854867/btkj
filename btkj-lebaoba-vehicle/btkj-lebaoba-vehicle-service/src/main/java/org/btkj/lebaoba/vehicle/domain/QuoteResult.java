package org.btkj.lebaoba.vehicle.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.btkj.lebaoba.vehicle.domain.internal.Message;
import org.btkj.pojo.enums.CommercialInsuranceType;
import org.btkj.pojo.model.Insurance;
import org.btkj.pojo.model.PolicySchema;
import org.rapid.util.lang.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@XmlRootElement(name = "RETURN")
public class QuoteResult {
	
	private static final Logger logger = LoggerFactory.getLogger(QuoteResult.class);

	private Attach attach;
	private Message message;
	
	@XmlElement(name = "Table")
	public Attach getAttach() {
		return attach;
	}
	public void setAttach(Attach attach) {
		this.attach = attach;
	}
	@XmlElement(name = "MESSAGE")
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return null != message && null != message.getValue() && message.getValue().equals("OK");
	}
	public String error() {
		return null == message ? null : message.getValue();
	}
	public static class Attach {
		private int Commission;
		// 投保状态 1，可投保（自动核保通过） 2，需修改（需要修复） 3，补资料（需要上传资料） 4，待审核（太平专有） 5，人工审核中 6.其他错误
		// 7、核保失败
		private int InsuranceStatus;
		// 无赔优惠系数
		private String RateTemp;	
		// 商业险返回信息
		private String SYReturnStr;
		// 交强险返回信息
		private String JQReturnStr;
		// 折扣前总保费
		private String BasePremium;
		// 出险信息
		private String ReturnStr;
		// 公司Id
		private int CompanyID;
		// 保单ID
		private String PolicyNo;
		// 总保额
		private String TotalAmount;
		// 总保费
		private String TotalPremium;
		// 总佣金
		private String TotalCommision;
		// 商业险总保额
		private String CommerceTotalAmount;
		// 商业总保费
		private String CommerceTotalPremium;
		// 商业险佣金
		private String CommerceTotalCommission;
		// 交强险总保额
		private String CompulsoryTotalAmount;
		// 交强险总保费
		private String CompulsoryTotalPremium;
		// 交强险佣金
		private String CompulsoryTotalCommisson;
		// 商业险保单号
		private String CommercePolicyNo;
		// 交强险保单号
		private String CompulsoryPolicyNo;
		// 商业险出险信息
		private String SyDanger;
		// 交强险出险信息
		private String JqDanger;
		// 报价是否发生错误 1发生错误，2未发生错误
		private int IsAnError;
		// 上传资料类型
		private String UploadType;
		// 是否通融成功
		private boolean IsApply;
		// 核保信息
		private CprModel cprModel;
		// 保单错误对象信息返回
		private AiErrorInfo aiErrorInfo;
		// 商业险信息返回集合
		private List<AmountItem> cmList;
		// 交强险信息返回集合
		private List<AmountItem> cpList;

		@XmlElement(name = "Commission")
		public int getCommission() {
			return Commission;
		}
		public void setCommission(int commission) {
			Commission = commission;
		}
		@XmlElement(name = "InsuranceStatus")
		public int getInsuranceStatus() {
			return InsuranceStatus;
		}
		@XmlElement(name = "RateTemp")
		public String getRateTemp() {
			return RateTemp;
		}
		public void setRateTemp(String rateTemp) {
			RateTemp = rateTemp;
		}
		public void setInsuranceStatus(int insuranceStatus) {
			InsuranceStatus = insuranceStatus;
		}
		@XmlElement(name = "SYReturnStr")
		public String getSYReturnStr() {
			return SYReturnStr;
		}
		public void setSYReturnStr(String sYReturnStr) {
			SYReturnStr = sYReturnStr;
		}
		@XmlElement(name = "JQReturnStr")
		public String getJQReturnStr() {
			return JQReturnStr;
		}
		public void setJQReturnStr(String jQReturnStr) {
			JQReturnStr = jQReturnStr;
		}
		@XmlElement(name = "BasePremium")
		public String getBasePremium() {
			return BasePremium;
		}
		public void setBasePremium(String basePremium) {
			BasePremium = basePremium;
		}
		@XmlElement(name = "ReturnStr")
		public String getReturnStr() {
			return ReturnStr;
		}
		public void setReturnStr(String returnStr) {
			ReturnStr = returnStr;
		}
		@XmlElement(name = "CompanyID")
		public int getCompanyID() {
			return CompanyID;
		}
		public void setCompanyID(int companyID) {
			CompanyID = companyID;
		}
		@XmlElement(name = "PolicyNo")
		public String getPolicyNo() {
			return PolicyNo;
		}
		public void setPolicyNo(String policyNo) {
			PolicyNo = policyNo;
		}
		@XmlElement(name = "TotalAmount")
		public String getTotalAmount() {
			return TotalAmount;
		}
		public void setTotalAmount(String totalAmount) {
			TotalAmount = totalAmount;
		}
		@XmlElement(name = "TotalPremium")
		public String getTotalPremium() {
			return TotalPremium;
		}
		public void setTotalPremium(String totalPremium) {
			TotalPremium = totalPremium;
		}
		@XmlElement(name = "TotalCommision")
		public String getTotalCommision() {
			return TotalCommision;
		}
		public void setTotalCommision(String totalCommision) {
			TotalCommision = totalCommision;
		}
		@XmlElement(name = "CommerceTotalAmount")
		public String getCommerceTotalAmount() {
			return CommerceTotalAmount;
		}
		public void setCommerceTotalAmount(String commerceTotalAmount) {
			CommerceTotalAmount = commerceTotalAmount;
		}
		@XmlElement(name = "CommerceTotalPremium")
		public String getCommerceTotalPremium() {
			return CommerceTotalPremium;
		}
		public void setCommerceTotalPremium(String commerceTotalPremium) {
			CommerceTotalPremium = commerceTotalPremium;
		}
		@XmlElement(name = "CommerceTotalCommission")
		public String getCommerceTotalCommission() {
			return CommerceTotalCommission;
		}
		public void setCommerceTotalCommission(String commerceTotalCommission) {
			CommerceTotalCommission = commerceTotalCommission;
		}
		@XmlElement(name = "CompulsoryTotalAmount")
		public String getCompulsoryTotalAmount() {
			return CompulsoryTotalAmount;
		}
		public void setCompulsoryTotalAmount(String compulsoryTotalAmount) {
			CompulsoryTotalAmount = compulsoryTotalAmount;
		}
		@XmlElement(name = "CompulsoryTotalPremium")
		public String getCompulsoryTotalPremium() {
			return CompulsoryTotalPremium;
		}
		public void setCompulsoryTotalPremium(String compulsoryTotalPremium) {
			CompulsoryTotalPremium = compulsoryTotalPremium;
		}
		@XmlElement(name = "CompulsoryTotalCommisson")
		public String getCompulsoryTotalCommisson() {
			return CompulsoryTotalCommisson;
		}
		public void setCompulsoryTotalCommisson(String compulsoryTotalCommisson) {
			CompulsoryTotalCommisson = compulsoryTotalCommisson;
		}
		@XmlElement(name = "CommercePolicyNo")
		public String getCommercePolicyNo() {
			return CommercePolicyNo;
		}
		public void setCommercePolicyNo(String commercePolicyNo) {
			CommercePolicyNo = commercePolicyNo;
		}
		@XmlElement(name = "CompulsoryPolicyNo")
		public String getCompulsoryPolicyNo() {
			return CompulsoryPolicyNo;
		}
		public void setCompulsoryPolicyNo(String compulsoryPolicyNo) {
			CompulsoryPolicyNo = compulsoryPolicyNo;
		}
		@XmlElement(name = "SyDanger")
		public String getSyDanger() {
			return SyDanger;
		}
		public void setSyDanger(String syDanger) {
			SyDanger = syDanger;
		}
		@XmlElement(name = "JqDanger")
		public String getJqDanger() {
			return JqDanger;
		}
		public void setJqDanger(String jqDanger) {
			JqDanger = jqDanger;
		}
		@XmlElement(name = "IsAnError")
		public int getIsAnError() {
			return IsAnError;
		}
		public void setIsAnError(int isAnError) {
			IsAnError = isAnError;
		}
		@XmlElement(name = "UploadType")
		public String getUploadType() {
			return UploadType;
		}
		public void setUploadType(String uploadType) {
			UploadType = uploadType;
		}
		@XmlElement(name = "IsApply")
		public boolean isIsApply() {
			return IsApply;
		}
		public void setIsApply(boolean isApply) {
			IsApply = isApply;
		}
		@XmlElementWrapper(name="CommerceAmountList")
	    @XmlElement(name="AmountItem")
		public List<AmountItem> getCmList() {
			return cmList;
		}
		public void setCmList(List<AmountItem> cmList) {
			this.cmList = cmList;
		}
		@XmlElementWrapper(name="CompulsoryAmountList")
	    @XmlElement(name="AmountItem")
		public List<AmountItem> getCpList() {
			return cpList;
		}
		public void setCpList(List<AmountItem> cpList) {
			this.cpList = cpList;
		}
		@XmlElement(name = "cprModel")
		public CprModel getCprModel() {
			return cprModel;
		}
		public void setCprModel(CprModel cprModel) {
			this.cprModel = cprModel;
		}
		@XmlElement(name = "aiErrorInfo")
		public AiErrorInfo getAiErrorInfo() {
			return aiErrorInfo;
		}
		public void setAiErrorInfo(AiErrorInfo aiErrorInfo) {
			this.aiErrorInfo = aiErrorInfo;
		}
		public PolicySchema schema(int seat) {
			PolicySchema schema = new PolicySchema();
			schema.setCommercialTotal(null == CommerceTotalPremium ? "0" : CommerceTotalPremium);
			schema.setCompulsoryTotal(null == CompulsoryTotalPremium ? "0" : CompulsoryTotalPremium);
			if (!CollectionUtil.isEmpty(cmList)) {
				Map<CommercialInsuranceType, Insurance> insurances = new HashMap<CommercialInsuranceType, Insurance>();
				for (AmountItem item : cmList) {
					LeBaoBaInsurance insurance = LeBaoBaInsurance.match(item.getInsuranceCode());
					if (null == insurance) {
						logger.error("未识别的乐保吧商业险种 - {} - {}", item.getInsuranceCode(), item.getInsuranceName());
						continue;
					}
					insurance.insuranceMapping(insurances, item);
					switch (insurance) {
					case Z5:
						Insurance temp = insurances.get(CommercialInsuranceType.PASSENGER);
						if (null != temp)
							temp.setQuota(new BigDecimal(temp.getQuota()).divide(new BigDecimal(seat - 1)).toString());
						break;
					default:
						break;
					}
				}
				schema.setInsurances(insurances);
			}
			if (!CollectionUtil.isEmpty(cpList)) {
				for (AmountItem item : cpList) {
					LeBaoBaInsurance insurance = LeBaoBaInsurance.match(item.getInsuranceCode());
					if (null == insurance) {
						logger.error("未识别的乐保吧交强险种 - {} - {}", item.getInsuranceCode(), item.getInsuranceName());
						continue;
					}
					switch (insurance) {
					case J1:
						schema.setCompulsoryTotal(null == item.getPremium() ? "0" : item.getPremium());
						break;
					case CCS:
						schema.setVehicleVesselTotal(null == item.getPremium() ? "0" : item.getPremium());
						break;
					default:
						logger.error("错误的的乐保吧交强险种 - {} - {}", item.getInsuranceCode(), item.getInsuranceName());
						break;
					}
				}
			}
			return schema;
		}
	}
	
	public static class AmountItem {
		private String BenchMarkPremium; // 折扣前保费
		private String InsuranceCode; // 险种代码
		private String InsuranceName; // 险种名字
		private String Premium; // 保费
		private String Amount; // 保额
		private String Discount; // 折扣
		private String CommissionRate; // 佣金点
		private String CommissionFare; // 佣金钱
		private int OrderNo;
		@XmlElement(name = "BenchMarkPremium")
		public String getBenchMarkPremium() {
			return BenchMarkPremium;
		}
		public void setBenchMarkPremium(String benchMarkPremium) {
			BenchMarkPremium = benchMarkPremium;
		}
		@XmlElement(name = "InsuranceCode")
		public String getInsuranceCode() {
			return InsuranceCode;
		}
		public void setInsuranceCode(String insuranceCode) {
			InsuranceCode = insuranceCode;
		}
		@XmlElement(name = "InsuranceName")
		public String getInsuranceName() {
			return InsuranceName;
		}
		public void setInsuranceName(String insuranceName) {
			InsuranceName = insuranceName;
		}
		@XmlElement(name = "Premium")
		public String getPremium() {
			return Premium;
		}
		public void setPremium(String premium) {
			Premium = premium;
		}
		@XmlElement(name = "Amount")
		public String getAmount() {
			return Amount;
		}
		public void setAmount(String amount) {
			Amount = amount;
		}
		@XmlElement(name = "Discount")
		public String getDiscount() {
			return Discount;
		}
		public void setDiscount(String discount) {
			Discount = discount;
		}
		@XmlElement(name = "CommissionRate")
		public String getCommissionRate() {
			return CommissionRate;
		}
		public void setCommissionRate(String commissionRate) {
			CommissionRate = commissionRate;
		}
		@XmlElement(name = "CommissionFare")
		public String getCommissionFare() {
			return CommissionFare;
		}
		public void setCommissionFare(String commissionFare) {
			CommissionFare = commissionFare;
		}
		@XmlElement(name = "OrderNo")
		public int getOrderNo() {
			return OrderNo;
		}
		public void setOrderNo(int orderNo) {
			OrderNo = orderNo;
		}
	}

	private static class CprModel {
		// 保单Id
		private String PolicyID;
		// 商业险核保状态 (-1 未购买 0,待核保, 1 自动核保成功，2 自动核保失败，3 人工核保中 4 人工核保成功 5 人工核保失败)
		private String CommerceCheckStatus;
		// 商业险核保失败或成功反馈信息
		private String CommerceCheckReply;
		// 交强险核保状态 (-1 未购买 0,待核保, 1 自动核保成功，2 自动核保失败，3 人工核保中 4 人工核保成功 5 人工核保失败)
		private String CompulsoryCheckStatus;
		// 交强险核保失败或成功反馈信息
		private String CompulsoryCheckReply;
		@XmlElement(name = "PolicyID")
		public String getPolicyID() {
			return PolicyID;
		}
		public void setPolicyID(String policyID) {
			PolicyID = policyID;
		}
		@XmlElement(name = "CommerceCheckStatus")
		public String getCommerceCheckStatus() {
			return CommerceCheckStatus;
		}
		public void setCommerceCheckStatus(String commerceCheckStatus) {
			CommerceCheckStatus = commerceCheckStatus;
		}
		@XmlElement(name = "CommerceCheckReply")
		public String getCommerceCheckReply() {
			return CommerceCheckReply;
		}
		public void setCommerceCheckReply(String commerceCheckReply) {
			CommerceCheckReply = commerceCheckReply;
		}
		@XmlElement(name = "CompulsoryCheckStatus")
		public String getCompulsoryCheckStatus() {
			return CompulsoryCheckStatus;
		}
		public void setCompulsoryCheckStatus(String compulsoryCheckStatus) {
			CompulsoryCheckStatus = compulsoryCheckStatus;
		}
		@XmlElement(name = "CompulsoryCheckReply")
		public String getCompulsoryCheckReply() {
			return CompulsoryCheckReply;
		}
		public void setCompulsoryCheckReply(String compulsoryCheckReply) {
			CompulsoryCheckReply = compulsoryCheckReply;
		}
	}

	public static class AiErrorInfo {
		private String Id;
		// 车牌
		private String LicenseNo;
		// 车架
		private String VIN;
		// 1报价成功，2报价失败
		private int QuoteResult;
		// 错误类型 1 重复投保错误信息，2不能购买险种错误信息，3 必须购买险种信息，4 保额错误信息，5 握手错误信息，6 其它错误类别,7过户车错误类型，8 车型错误类型，9 人保跟单折扣错误，多个错误请以|分割 如1|2|3
		private String ErrorType;
		// 报价流水号（保单的ID）
		private String QuoteNo;
		// 错误信息
		private String ErrorInfo;
		// 创建时间
		private String CreateTime;
		@XmlElement(name = "Id")
		public String getId() {
			return Id;
		}
		public void setId(String id) {
			Id = id;
		}
		@XmlElement(name = "LicenseNo")
		public String getLicenseNo() {
			return LicenseNo;
		}
		public void setLicenseNo(String licenseNo) {
			LicenseNo = licenseNo;
		}
		@XmlElement(name = "VIN")
		public String getVIN() {
			return VIN;
		}
		public void setVIN(String vIN) {
			VIN = vIN;
		}
		@XmlElement(name = "QuoteResult")
		public int getQuoteResult() {
			return QuoteResult;
		}
		public void setQuoteResult(int quoteResult) {
			QuoteResult = quoteResult;
		}
		@XmlElement(name = "ErrorType")
		public String getErrorType() {
			return ErrorType;
		}
		public void setErrorType(String errorType) {
			ErrorType = errorType;
		}
		@XmlElement(name = "QuoteNo")
		public String getQuoteNo() {
			return QuoteNo;
		}
		public void setQuoteNo(String quoteNo) {
			QuoteNo = quoteNo;
		}
		@XmlElement(name = "ErrorInfo")
		public String getErrorInfo() {
			return ErrorInfo;
		}
		public void setErrorInfo(String errorInfo) {
			ErrorInfo = errorInfo;
		}
		@XmlElement(name = "CreateTime")
		public String getCreateTime() {
			return CreateTime;
		}
		public void setCreateTime(String createTime) {
			CreateTime = createTime;
		}
	}
}
