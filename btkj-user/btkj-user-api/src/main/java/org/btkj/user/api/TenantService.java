package org.btkj.user.api;

import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.vo.EmployeeTip;
import org.btkj.user.pojo.info.TenantListInfo;
import org.btkj.user.pojo.param.TenantAddParam;
import org.rapid.util.common.message.Result;

public interface TenantService {
	
	/**
	 * 通过 tid 获取 tenant
	 * 
	 * @param tid
	 * @return
	 */
	TenantPO tenant(int tid);
	
	/**
	 * 申请加入代理公司
	 * 
	 * @param user
	 * @param chief
	 * @return
	 */
	Result<?> apply(User user, Employee chief);
	
	/**
	 * 添加代理公司
	 * 
	 * @return
	 */
	Result<EmployeeTip> tenantAdd(int appId, TenantAddParam param);
	
	/**
	 * 代理公司列表
	 * 
	 * @param client
	 * @param app
	 * @param user
	 * @return
	 */
	TenantListInfo tenantListInfo(User user);
}
