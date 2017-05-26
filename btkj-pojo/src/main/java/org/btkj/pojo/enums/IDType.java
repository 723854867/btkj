package org.btkj.pojo.enums;

import org.rapid.util.common.Validator;

/**
 * 证件类型
 * 
 * @author ahab
 */
public enum IDType {

	/**
	 * 身份证
	 */
	IDENTITY(1) {
		@Override
		public boolean check(String idNo) {
			return Validator.isIdentity(idNo);
		}
	},
	
	/**
	 * 组织机构代码证
	 */
	ORGANIZATION_LICENSE(2),
	
	/**
	 * 护照
	 */
	PASSPORT(3),
	
	/**
	 * 军官证
	 */
	MILITARY_CARD(4),
	
	/**
	 * 港澳回乡证或台胞证
	 */
	RE_ENTRY_PERMIT(5),
	
	/**
	 * 港澳通行证
	 */
	EEP_HK_MACAU(6),
	
	/**
	 * 驾驶证
	 */
	DRIVING_LICENSE(7),
	
	/**
	 * 营业执照
	 */
	BUSINESS_LICENSE(8),
	
	/**
	 * 税务登记证
	 */
	TAX_REGISTRATION_CERTIFICATE(9),
	
	/**
	 * 临时身份证
	 */
	TEMP_IDENTITY(10),
	
	/**
	 * 其他
	 */
	OTHER(20);
	
	private int mark;
	
	private IDType(int mark) {
		this.mark = mark;
	}
	
	public boolean check(String idNo) {
		return true;
	}
	
	public int mark() {
		return mark;
	}
}
