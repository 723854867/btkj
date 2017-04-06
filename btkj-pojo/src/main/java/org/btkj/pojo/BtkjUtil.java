package org.btkj.pojo;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.model.Credential;

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
	
	public static final boolean isBaoTuApp(App app) {
		return app.getId() == BtkjConsts.APP_ID_BAOTU;
	}
	
	public static final boolean isBaoTuApp(Credential credential) {
		return credential.getApp().getId() == BtkjConsts.APP_ID_BAOTU;
	}
	
	/**
	 * 判断 credential 中的 tenant 是否为 null
	 * 
	 * @param credential
	 * @return
	 */
	public static final boolean hasTenant(String tid) {
		return !tid.equals(BtkjConsts.NULL_TENANT_CREDENTIAL);
	}
}
