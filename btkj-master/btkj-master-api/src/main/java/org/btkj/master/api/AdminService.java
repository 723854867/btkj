package org.btkj.master.api;

import org.rapid.util.common.message.Result;

public interface AdminService {

	/**
	 * 禁用
	 * 
	 * @param id
	 * @return
	 */
	Result<Void> seal(int id);
	
	/**
	 * 解禁
	 * 
	 * @param id
	 * @return
	 */
	Result<Void> unseal(int id);
}
