package org.btkj.master;

import org.rapid.data.storage.redis.ILuaCmd;

public enum LuaCmd implements ILuaCmd {
	
	ADMINISTRATOR_LOAD_BY_TOKEN {
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
