package org.btkj.manager.action.tenant.poundage;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.vehicle.api.BonusService;
import org.btkj.vehicle.pojo.model.PoundageNodeConfigInfo;
import org.btkj.vehicle.pojo.param.PoundageNodeConfigParam;
import org.rapid.util.common.message.Result;

/**
 * 获取指定手续费节点的配置
 * 
 * @author ahab
 */
public class POUNDAGE_NODE_CONFIG_INFO extends EmployeeAction<PoundageNodeConfigParam> {
	
	@Resource
	private BonusService bonusService;

	@Override
	protected Result<PoundageNodeConfigInfo> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, PoundageNodeConfigParam param) {
		param.setTid(tenant.getTid());
		return Result.result(bonusService.poundageNodeConfig(param));
	}
}
