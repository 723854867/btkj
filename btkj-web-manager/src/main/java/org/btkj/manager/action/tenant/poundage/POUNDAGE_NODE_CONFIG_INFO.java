package org.btkj.manager.action.tenant.poundage;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.vehicle.api.BonusService;
import org.btkj.vehicle.pojo.param.PoundageErogidicParam;
import org.btkj.vehicle.pojo.param.PoundageErogidicParam.Type;
import org.rapid.util.common.message.Result;

/**
 * 获取指定手续费节点的配置
 * 
 * @author ahab
 */
public class POUNDAGE_NODE_CONFIG_INFO extends EmployeeAction<PoundageErogidicParam> {
	
	@Resource
	private BonusService bonusService;

	@Override
	protected Result<?> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, PoundageErogidicParam param) {
		param.setTid(tenant.getTid());
		param.setType(Type.NODE_CONFIG);
		return bonusService.poundageErgodic(param);
	}
}
