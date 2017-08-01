package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.nonauto.api.NonAutoService;
import org.btkj.nonauto.pojo.param.NonAutoProductListParam;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.NonAutoProduct;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.web.util.action.EmployeeAction;
import org.rapid.util.common.message.Result;

public class NON_AUTO_PRODUCT_LIST extends EmployeeAction<NonAutoProductListParam> {
	
	@Resource
	private NonAutoService nonAutoService;

	@Override
	protected Result<Pager<NonAutoProduct>> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, NonAutoProductListParam param) {
		return Result.result(nonAutoService.products(param));
	}
}
