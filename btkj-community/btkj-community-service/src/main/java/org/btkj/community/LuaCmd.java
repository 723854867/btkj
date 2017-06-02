package org.btkj.community;

import org.rapid.data.storage.redis.ILuaCmd;

public enum LuaCmd implements ILuaCmd {
	
	/**
	 * 咨询分页
	 */
	STORE_ARTICLES {
		@Override
		public int keyNum() {
			return 0;
		}
	};

	@Override
	public String key() {
		return name().toLowerCase();
	}
}
