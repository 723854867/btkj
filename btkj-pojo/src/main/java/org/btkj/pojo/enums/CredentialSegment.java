package org.btkj.pojo.enums;

/**
 * credential 的组成部分
 * 
 * @author ahab
 */
public enum CredentialSegment {
	
	CLIENT_TYPE(1, 1),
	
	APP_ID(2, 3),
	
	TENANT_ID(4, 4),
	
	UID(8, 0) {
		@Override
		public int digitsNum() {
			throw new UnsupportedOperationException("uid segment has no fixed digits number!");
		}
	};

	private int mod;
	private int digitsNum;
	
	private CredentialSegment(int mod, int digitsNum) {
		this.mod = mod;
		this.digitsNum = digitsNum;
	}
	
	public int mod() {
		return mod;
	}
	
	public int digitsNum() {
		return this.digitsNum;
	}
	
	/**
	 * 所有的组成部分全部要有：其实就是一个邀请码
	 * 
	 * @return
	 */
	public static final int allSegmentMod() {
		return CLIENT_TYPE.mod | APP_ID.mod | TENANT_ID.mod | UID.mod;
	}
	
	/**
	 * app 级别的 credential，能识别到 app
	 * 
	 * @return
	 */
	public static final int appGradeSegmentMod() {
		return CLIENT_TYPE.mod | APP_ID.mod;
	}
	
	/**
	 * tenant 级别的 credential，能识别到 tenant
	 * 
	 * @return
	 */
	public static final int tenantGradeSegmentMod() {
		return CLIENT_TYPE.mod | APP_ID.mod | TENANT_ID.mod;
	}
	
	/**
	 * 邀请码的组成部分
	 * @return
	 */
	public static final int inviteCodeMod() {
		return APP_ID.mod | TENANT_ID.mod | UID.mod;
	}
}
