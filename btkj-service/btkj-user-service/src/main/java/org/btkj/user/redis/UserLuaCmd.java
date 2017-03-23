package org.btkj.user.redis;

import org.rapid.data.storage.redis.ILuaCmd;

public enum UserLuaCmd implements ILuaCmd {

	/**
	 * 通过手机获取用户
	 */
	GET_USER_BY_MOBILE {
		@Override
		public int keyNum() {
			return 2;
		}
	},
	
	TOKEN_REPLACE {
		@Override
		public int keyNum() {
			return 3;
		}
	}, 
	
	REFRESH_USER {
		@Override
		public int keyNum() {
			return 2;
		}
	},
	
	/**
	 * 通过 token 获取用户，同时获取用户的锁
	 */
	LOCK_USER_BY_TOKEN {
		@Override
		public int keyNum() {
			return 2;
		}
	},
	
	/**
	 * 通过 token 获取用户，不会获取用户的锁
	 * 
	 */
	GET_USER_BY_TOKEN {
		@Override
		public int keyNum() {
			return 2;
		}
	},
	
	/**
	 * 刷新 employee
	 */
	REFRESH_EMPLOYEE {
		@Override
		public int keyNum() {
			return 2;
		}
	},
	
	/**
	 * 通过 uid 和 tid 获取 employee
	 */
	EMPLOYEE_GET_1 {
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
