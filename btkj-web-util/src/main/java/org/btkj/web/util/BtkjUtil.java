package org.btkj.web.util;

public class BtkjUtil {

	/**
	 * 判断是否是保途的app
	 * 
	 * @param appId
	 * @return
	 */
	public static final boolean isBaoTuApp(int appId) {
		return appId == BtkjConsts.APP_ID_BAOTU;
	}
}
