package org.btkj.courier.redis;

import org.rapid.data.storage.redis.ILuaCmd;

public enum CourierLuaCmd implements ILuaCmd {

	/**
	 * 校对验证码
	 */
	CAPTCHA_VERIFY() {
		@Override
		public int keyNum() {
			return 2;
		}
	};

	@Override
	public String key() {
		return name().toLowerCase();
	}
}
