package org.btkj.manager.action.tenant.bonus;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.bo.BonusRouteBody;
import org.btkj.pojo.bo.EmployeeForm;
import org.btkj.pojo.vo.BonusSearcher;
import org.btkj.vehicle.api.BonusService;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;

/**
 * 佣金设置
 * 
 * @author ahab
 */
public class VEHICLE_BONUS_SET extends TenantAction {
	
	@Resource
	private BonusService bonusService;
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<Void> execute(Request request, EmployeeForm ef) {
		BonusSearcher searcher = request.getParam(Params.BONUS_SEARCHER);
		Result<Void> result = _checkSearcher(searcher, ef);
		if (!result.isSuccess())
			return result;
		return bonusService.bonusSettings(searcher);
	}
	
	private Result<Void> _checkSearcher(BonusSearcher searcher, EmployeeForm ef) {
		searcher.setTid(ef.getTenant().getTid());
		BonusRouteBody routeData = searcher.getRouteBody();
		if (null == routeData)
			throw ConstConvertFailureException.errorConstException(Params.BONUS_SEARCHER);
		routeData.setChildren(null);			// 只能设置本节点的参数，不能设置子节点的配置
		routeData.setCommercialRate(Math.min(routeData.getCommercialRate(), BtkjConsts.MAX_COMMISION_RATE));
		routeData.setCompulsoryRate(Math.min(routeData.getCompulsoryRate(), BtkjConsts.MAX_COMMISION_RATE));
		routeData.setCommercialRate(Math.max(routeData.getCommercialRate(), BtkjConsts.MIN_COMMISION_RATE));
		routeData.setCompulsoryRate(Math.max(routeData.getCompulsoryRate(), BtkjConsts.MIN_COMMISION_RATE));
		routeData.setCommercialRetainRate(Math.min(routeData.getCommercialRetainRate(), BtkjConsts.MAX_COMMISION_RETAIN_RATE));
		routeData.setCompulsoryRetainRate(Math.min(routeData.getCompulsoryRetainRate(), BtkjConsts.MAX_COMMISION_RETAIN_RATE));
		routeData.setCommercialRetainRate(Math.max(routeData.getCommercialRetainRate(), BtkjConsts.MIN_COMMISION_RETAIN_RATE));
		routeData.setCompulsoryRetainRate(Math.max(routeData.getCompulsoryRetainRate(), BtkjConsts.MIN_COMMISION_RETAIN_RATE));
		
		List<Integer> list = vehicleService.insurers(ef.getTenant());
		for (int insurerId : list) {
			if (insurerId == searcher.getInsurerId())
				return Consts.RESULT.OK;
		}
		return BtkjConsts.RESULT.INSURER_NOT_EXIST;
	}
}
