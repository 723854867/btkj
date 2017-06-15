package org.btkj.user;

import org.rapid.data.storage.redis.ILuaCmd;

public enum LuaCmd implements ILuaCmd {

	/**
	 * 通过手机获取用户
	 */
	USER_LOAD_BY_MOBILE {
		@Override
		public int keyNum() {
			return 1;
		}
	},
	
	USER_LOAD_BY_MOBILE_LOCK {
		@Override
		public int keyNum() {
			return 2;
		}
	},
	
	USER_FLUSH {
		@Override
		public int keyNum() {
			return 2;
		}
	},
	
	/**
	 * 通过 token 获取用户，同时获取用户的锁
	 */
	USER_LOAD_BY_TOKEN_LOCK {
		@Override
		public int keyNum() {
			return 2;
		}
	},
	
	USER_LOAD_BY_TOKEN {
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
