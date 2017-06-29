package org.btkj.manager.action.tenant.bonus;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.submit.BonusSearcher;
import org.btkj.vehicle.api.BonusService;
import org.btkj.vehicle.model.VehicleCoefficientInfo;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;

/**
 * 获取系数表
 * 
 * @author ahab
 */
public class VEHICLE_COEFFICIENTS extends TenantAction {
	
	@Resource
	private BonusService bonusService;

	@Override
	protected Result<List<VehicleCoefficientInfo>> execute(Request request, EmployeeForm ef) {
		BonusSearcher searcher = request.getParam(Params.BONUS_SEARCHER);
		if (!_checkSearcher(searcher, ef))
			throw ConstConvertFailureException.errorConstException(Params.BONUS_SEARCHER);
		return bonusService.coefficients(searcher);
	}
	
	private boolean _checkSearcher(BonusSearcher searcher, EmployeeForm ef) {
		searcher.setTid(ef.getEmployee().getTid());
		if (null == searcher.getPath())
			return false;
		return true;
	}
}
