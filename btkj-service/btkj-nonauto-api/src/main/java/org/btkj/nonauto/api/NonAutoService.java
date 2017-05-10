package org.btkj.nonauto.api;

import org.btkj.pojo.entity.NonAutoCategory;

/**
 * 非车险服务类
 * 
 * @author ahab
 */
public interface NonAutoService {

	/**
	 * 新增非车险类型
	 * 
	 * @param category
	 */
	void edit(NonAutoCategory category);
}
