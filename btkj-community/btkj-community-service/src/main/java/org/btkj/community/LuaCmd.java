package org.btkj.community;

import org.rapid.data.storage.redis.ILuaCmd;

public enum LuaCmd implements ILuaCmd {
	
	/**
	 * 咨询缓存刷新
	 */
	FLUSH_ARTICLE {
		@Override
		public int keyNum() {
			return 4;
		}
	};

	@Override
	public String key() {
		return name().toLowerCase();
	}
}
