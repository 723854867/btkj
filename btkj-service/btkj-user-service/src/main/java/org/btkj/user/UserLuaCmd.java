package org.btkj.user;

import org.rapid.data.storage.redis.ILuaCmd;

public enum UserLuaCmd implements ILuaCmd {

	/**
	 * 通过手机获取用户
	 */
	USER_LOAD_BY_MOBILE {
		@Override
		public int keyNum() {
			return 2;
		}
	},
	
	USER_LOAD_BY_MOBILE_LOCK {
		@Override
		public int keyNum() {
			return 1;
		}
	},
	
	TOKEN_REPLACE {
		@Override
		public int keyNum() {
			return 2;
		}
	}, 
	
	TOKEN_REMOVE {
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
	
	APPLY_FLUSH {
		@Override
		public int keyNum() {
			return 3;
		};
	},
	
	APPLY_GET_AND_DEL {
		@Override
		public int keyNum() {
			return 3;
		}
	},
	
	EMPLOYEE_FLUSH {
		@Override
		public int keyNum() {
			return 2;
		}
	},
	
	EMPLOYEE_LOAD_BY_TID_UID {
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
