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
			return 3;
		}
	},
	
	EMPLOYEE_GET {
		@Override
		public int keyNum() {
			return 1;
		}
	},
	
	REFRESH_EMPLOYEES {
		@Override
		public int keyNum() {
			return 2;
		}
	},
	
	APPLY_ADD {
		@Override
		public int keyNum() {
			return 2;
		};
	},
	
	APPLY_GET_AND_DEL {
		@Override
		public int keyNum() {
			return 3;
		}
	},
	
	EMPLOYEE_GET_BY_UID_AND_TID {
		@Override
		public int keyNum() {
			return 2;
		}
	},
	
	EMPLOYEE_LIST {
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
