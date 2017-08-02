package org.btkj.lebaoba.vehicle.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RETURN")
public class OrderResult {

	private int Commission;
	// 投保状态  1，可投保（自动核保通过）  2，需修改（需要修复） 3，补资料（需要上传资料）  4，待审核（太平专有）  5，人工审核中  6.其他错误 7、核保失败
	private int InsuranceStatus;
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
	// 总商业险保额
	private String CommerceTotalAmount;
	// 总商业保费
	private String CommerceTotalPremium;
	// 总商业险佣金
	private String CommerceTotalCommission;
	// 总交强险保额
	private String CompulsoryTotalAmount;
	// 总交强险保费
	private String CompulsoryTotalPremium;
}
