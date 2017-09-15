package org.btkj.user.api;

import java.util.Collection;
import java.util.Map;

import org.btkj.pojo.entity.user.TenantPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.info.EmployeeTip;
import org.btkj.pojo.info.user.TenantListInfo;
import org.btkj.pojo.param.user.TenantAddParam;
import org.rapid.util.common.message.Result;

public interface TenantService {
	
	/**
	 * 通过 tid 获取 tenant
	 * 
	 * @param tid
	 * @return
	 */
	TenantPO tenant(int tid);
	
	Map<Integer, TenantPO> tenants(Collection<Integer> tenants);
	
	void update(TenantPO tenant);
	
	/**
	 * 申请加入代理公司
	 * 
	 * @param user
	 * @param chief
	 * @return
	 */
	Result<?> apply(UserPO user, EmployeeTip chief);
	
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
	
	/**
	 * 禁用
	 * 
	 * @param tid
	 * @return
	 */
	Result<Void> seal(int appId, int tid);
	
	/**
	 * 解禁
	 * 
	 * @param tid
	 * @return
	 */
	Result<Void> unseal(int appId, int tid);
}
