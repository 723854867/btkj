package org.btkj.user.api;

import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.info.EmployeeTip;
import org.btkj.pojo.model.identity.Employee;
import org.btkj.pojo.model.identity.User;
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
	 * @return
	 */
	TenantListInfo tenantListInfo(UserPO user);
}
