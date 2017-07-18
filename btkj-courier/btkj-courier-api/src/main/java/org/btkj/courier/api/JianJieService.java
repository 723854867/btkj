package org.btkj.courier.api;

import org.btkj.pojo.po.UserPO;
import org.btkj.pojo.vo.JianJiePoliciesInfo;

/**
 * 简捷服务类
 * 
 * @author ahab
 */
public interface JianJieService {

	/**
	 * 添加业务员
	 * 
	 * @param user
	 */
	void addUser(UserPO user);
	
	/**
	 * 获取指定代理公司的所有保单
	 * 
	 * @param jianJieId
	 * @param begin
	 * @param end
	 */
	JianJiePoliciesInfo vehiclePolicies(String jianJieId, String begin, String end); 
}
