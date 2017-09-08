package org.btkj.manager.action.tenant;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.nonauto.api.NonAutoService;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.NonAutoCategory;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.param.user.TenantSetParam;
import org.btkj.user.api.UserManageService;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;

/**
 * 商户自己设置自己的一些属性
 * 
 * @author ahab
 *
 */
public class TENANT_SET extends EmployeeAction<TenantSetParam> {
	
	@Resource
	private NonAutoService nonAutoService;
	@Resource
	private UserManageService userManageService;
	
	@Override
	protected Result<?> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, TenantSetParam param) {
		if (!CollectionUtil.isEmpty(param.getNonAutoBind())){
			List<NonAutoCategory> categories = nonAutoService.categories();
			a : for (Long cid : param.getNonAutoBind()){
				for (NonAutoCategory category : categories) {
					if (cid == category.get_id())
						continue a;
				}
				return BtkjConsts.RESULT.NON_AUTO_CATEGORY_NOT_EXIST;
			}
		}
		return userManageService.tenantSet(tenant, param);
	}
}
