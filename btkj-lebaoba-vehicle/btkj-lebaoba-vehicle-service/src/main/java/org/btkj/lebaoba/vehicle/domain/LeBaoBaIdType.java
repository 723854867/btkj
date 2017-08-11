package org.btkj.lebaoba.vehicle.domain;

import org.btkj.pojo.enums.IDType;

public enum LeBaoBaIdType {

	// 身份证
	IDENTITY("01"),			
	
	// 驾驶证
	DRIVING_LICENSE("02"),
	
	// 军人证
	MILITARY_CARD("03"),
	
	// 护照
	PASSPORT("04"),
	
	// 临时身份证
	TEMP_IDENTITY("05"),
	
	// 港澳回乡证或台胞证
	RE_ENTRY_PERMIT("07"),
	
	// 港澳通行证
	EEP_HK_MACAU("06"),	
	
	// 组织机构代码证
	ORGANIZATION_LICENSE("21"),
	
	// 营业执照
	BUSINESS_LICENSE("23"),
	
	// 税务登记证
	TAX_REGISTRATION_CERTIFICATE("22"),
	
	// 其他证件
	OTHER("24");
	
	private String mark;
	
	private LeBaoBaIdType(String mark) {
		this.mark = mark;
	}
	
	public String mark() {
		return mark;
	}
	
	public static final LeBaoBaIdType convert(IDType idType) {
		switch (idType) {
		case IDENTITY:
			return IDENTITY;
		case ORGANIZATION_LICENSE:
			return ORGANIZATION_LICENSE;
		case PASSPORT:
			return PASSPORT;
		case MILITARY_CARD:
			return MILITARY_CARD;
		case EEP_HK_MACAU:
			return EEP_HK_MACAU;
		case RE_ENTRY_PERMIT:
			return RE_ENTRY_PERMIT;
		case DRIVING_LICENSE:
			return DRIVING_LICENSE;
		case BUSINESS_LICENSE:
			return BUSINESS_LICENSE;
		case TAX_REGISTRATION_CERTIFICATE:
			return TAX_REGISTRATION_CERTIFICATE;
		case TEMP_IDENTITY:
			return TEMP_IDENTITY;
		default:
			return OTHER;
		}
	}
}
