package org.btkj.manager.action.tenant;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.model.BonusRouteBody;
import org.btkj.vehicle.api.BonusService;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.vehicle.pojo.param.BonusPoundageEditParam;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

/**
 * 手续费设置
 * 
 * @author ahab
 */
public class BONUS_POUNDAGE_CONFIG_EDIT extends EmployeeAction<BonusPoundageEditParam> {
	
	@Resource
	private BonusService bonusService;
	@Resource
	private VehicleService vehicleService;
	
	@Override
	protected Result<Void> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, BonusPoundageEditParam param) {
		param.setTid(tenant.getTid());
		if (!param.isDelete()) {
			Result<Void> result = _checkParam(param);
			if (!result.isSuccess())
				return result;
		}
		return bonusService.bonusPoundageEdit(param);
	}
	
	private Result<Void> _checkParam(BonusPoundageEditParam param) {
		BonusRouteBody routeData = param.getRouteBody();
		if (null == routeData)
			return Consts.RESULT.FORBID;
		routeData.setChildren(null);					// 只能设置当前节点的参数，不能设置自己点配置
		routeData.setCommercialRate(Math.min(routeData.getCommercialRate(), BtkjConsts.LIMITS.MAX_COMMISION_RATE));
		routeData.setCompulsoryRate(Math.min(routeData.getCompulsoryRate(), BtkjConsts.LIMITS.MAX_COMMISION_RATE));
		routeData.setCommercialRate(Math.max(routeData.getCommercialRate(), BtkjConsts.LIMITS.MIN_COMMISION_RATE));
		routeData.setCompulsoryRate(Math.max(routeData.getCompulsoryRate(), BtkjConsts.LIMITS.MIN_COMMISION_RATE));
		routeData.setCommercialRetainRate(Math.min(routeData.getCommercialRetainRate(), BtkjConsts.LIMITS.MAX_COMMISION_RETAIN_RATE));
		routeData.setCompulsoryRetainRate(Math.min(routeData.getCompulsoryRetainRate(), BtkjConsts.LIMITS.MAX_COMMISION_RETAIN_RATE));
		routeData.setCommercialRetainRate(Math.max(routeData.getCommercialRetainRate(), BtkjConsts.LIMITS.MIN_COMMISION_RETAIN_RATE));
		routeData.setCompulsoryRetainRate(Math.max(routeData.getCompulsoryRetainRate(), BtkjConsts.LIMITS.MIN_COMMISION_RETAIN_RATE));
		List<Integer> list = vehicleService.insurers(param.getTid());
		for (int insurerId : list) {
			if (insurerId == param.getInsurerId())
				return Consts.RESULT.OK;
		}
		return BtkjConsts.RESULT.INSURER_NOT_EXIST;
	}
}
