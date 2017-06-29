package org.btkj.manager.action.tenant.bonus;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.insur.vehicle.BonusRouteBody;
import org.btkj.pojo.submit.BonusSearcher;
import org.btkj.vehicle.api.BonusService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
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

	@Override
	protected Result<Void> execute(Request request, EmployeeForm ef) {
		BonusSearcher searcher = request.getParam(Params.BONUS_SEARCHER);
		if (!_checkSearcher(searcher, ef))
			throw ConstConvertFailureException.errorConstException(Params.BONUS_SEARCHER);
		return bonusService.bonusSettings(searcher);
	}
	
	private boolean _checkSearcher(BonusSearcher searcher, EmployeeForm ef) {
		searcher.setTid(ef.getTenant().getTid());
		BonusRouteBody routeData = searcher.getRouteBody();
		if (null == routeData)
			return false;
		routeData.setChildren(null);			// 只能设置本节点的参数，不能设置子节点的配置
		routeData.setCommercialRate(Math.min(routeData.getCommercialRate(), BtkjConsts.MAX_COMMISION_RATE));
		routeData.setCompulsoryRate(Math.min(routeData.getCompulsoryRate(), BtkjConsts.MAX_COMMISION_RATE));
		routeData.setCommercialRate(Math.max(routeData.getCommercialRate(), BtkjConsts.MIN_COMMISION_RATE));
		routeData.setCompulsoryRate(Math.max(routeData.getCompulsoryRate(), BtkjConsts.MIN_COMMISION_RATE));
		routeData.setCommercialRetainRate(Math.min(routeData.getCommercialRetainRate(), BtkjConsts.MAX_COMMISION_RETAIN_RATE));
		routeData.setCompulsoryRetainRate(Math.min(routeData.getCompulsoryRetainRate(), BtkjConsts.MAX_COMMISION_RETAIN_RATE));
		routeData.setCommercialRetainRate(Math.max(routeData.getCommercialRetainRate(), BtkjConsts.MIN_COMMISION_RETAIN_RATE));
		routeData.setCompulsoryRetainRate(Math.max(routeData.getCompulsoryRetainRate(), BtkjConsts.MIN_COMMISION_RETAIN_RATE));
		return true;
	}
}
