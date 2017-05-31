package org.btkj.master;

import org.rapid.data.storage.redis.ILuaCmd;

public enum MasterLuaCmd implements ILuaCmd {
	
	ADMINISTRATOR_LOAD_BY_TOKEN {
		@Override
		public int keyNum() {
			return 2;
		}
	},
	
	TENANT_ADD_APPLY_FLUSH {
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
