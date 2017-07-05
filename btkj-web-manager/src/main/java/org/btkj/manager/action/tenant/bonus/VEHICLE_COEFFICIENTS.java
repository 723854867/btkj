package org.btkj.manager.action.tenant.bonus;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.submit.BonusSearcher;
import org.btkj.vehicle.api.BonusService;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.vehicle.pojo.model.VehicleCoefficientsInfo;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.Consts;
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
	@Resource
	private ConfigService configService;
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<List<VehicleCoefficientsInfo>> execute(Request request, EmployeeForm ef) {
		BonusSearcher searcher = request.getParam(Params.BONUS_SEARCHER);
		Result<List<VehicleCoefficientsInfo>> result = _checkSearcher(searcher, ef);
		if (!result.isSuccess())
			return result;
		return bonusService.coefficients(searcher);
	}
	
	private Result<List<VehicleCoefficientsInfo>> _checkSearcher(BonusSearcher searcher, EmployeeForm ef) {
		searcher.setTid(ef.getTenant().getTid());
		Region region = configService.subordinateProvince(ef.getTenant().getTid());
		searcher.setSubordinateProvince(null == region ? 0 : region.getId());
		if (null == searcher.getPath())
			throw ConstConvertFailureException.errorConstException(Params.BONUS_SEARCHER);
		List<Integer> list = vehicleService.insurers(ef.getTenant());
		for (int insurerId : list) {
			if (insurerId == searcher.getInsurerId())
				return Consts.RESULT.OK;
		}
		return BtkjConsts.RESULT.INSURER_NOT_EXIST;
	}
}
