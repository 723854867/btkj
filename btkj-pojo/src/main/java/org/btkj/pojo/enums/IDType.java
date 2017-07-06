package org.btkj.pojo.enums;

import org.rapid.util.validator.Validator;

/**
 * 证件类型
 * 
 * @author ahab
 */
public enum IDType {

	/**
	 * 身份证
	 */
	IDENTITY(1, UnitType.PERSONAL.mark()) {
		@Override
		public boolean check(String idNo) {
			return Validator.isIdentity(idNo);
		}
	},
	
	/**
	 * 组织机构代码证
	 */
	ORGANIZATION_LICENSE(2, UnitType.ENTERPRISE.mark() | UnitType.OFFICE.mark()),
	
	/**
	 * 护照
	 */
	PASSPORT(3, UnitType.PERSONAL.mark()),
	
	/**
	 * 军官证
	 */
	MILITARY_CARD(4, UnitType.PERSONAL.mark()),
	
	/**
	 * 港澳回乡证或台胞证
	 */
	RE_ENTRY_PERMIT(5, UnitType.PERSONAL.mark()),
	
	/**
	 * 港澳通行证
	 */
	EEP_HK_MACAU(6, UnitType.PERSONAL.mark()),
	
	/**
	 * 驾驶证
	 */
	DRIVING_LICENSE(7, UnitType.PERSONAL.mark()),
	
	/**
	 * 营业执照
	 */
	BUSINESS_LICENSE(8, UnitType.ENTERPRISE.mark()),
	
	/**
	 * 税务登记证
	 */
	TAX_REGISTRATION_CERTIFICATE(9, UnitType.ENTERPRISE.mark() | UnitType.OFFICE.mark()),
	
	/**
	 * 临时身份证
	 */
	TEMP_IDENTITY(10, UnitType.PERSONAL.mark()),
	
	/**
	 * 其他
	 */
	OTHER(20, UnitType.PERSONAL.mark());
	
	private int mark;
	private int unitMod;
	
	private IDType(int mark, int unitMod) {
		this.mark = mark;
		this.unitMod = unitMod;
	}
	
	public boolean check(String idNo) {
		return true;
	}
	
	public int mark() {
		return mark;
	}
	
	public int unitMod() {
		return unitMod;
	}
}
