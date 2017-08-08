package org.btkj.user.api;

import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.pojo.po.TenantPO;
import org.btkj.user.pojo.info.TenantListInfo;
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
	 * 处理请求
	 * 
	 * @param tid
	 * @param agree
	 */
	Result<Employee> applyProcess(int tid, int uid, boolean agree);
	
	/**
	 * 添加代理公司
	 * 
	 * @param app 如果是为多租户app添加代理公司则需要该参数
	 * @param region 代理公司地区
	 * @param tname 代理公司租户名字
	 * @param user 顶级用户
	 * @param license 营业执照号码
	 * @param licenseImage 营业执照副本
	 * @param servicePhone 客服电话
	 * @return
	 */
	Result<Employee> tenantAdd(int appId, String mobile, String contacts, String contactsMobile, String tname, String license, 
			String licenseImage, String servicePhone, int expire);
	
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
