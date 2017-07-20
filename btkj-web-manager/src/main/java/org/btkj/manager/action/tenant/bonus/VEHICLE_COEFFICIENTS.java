package org.btkj.manager.action.tenant.bonus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.enums.CoefficientType;
import org.btkj.pojo.po.Region;
import org.btkj.pojo.po.VehicleCoefficient;
import org.btkj.pojo.vo.BonusSearcher;
import org.btkj.vehicle.api.BonusService;
import org.btkj.vehicle.api.VehicleManageService;
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
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<?> execute(Request request, Employee employee) {
		BonusSearcher searcher = request.getOptionalParam(Params.BONUS_SEARCHER);
		if (null == searcher) {
			List<VehicleCoefficient> coefficients = vehicleManageService.coefficients(employee.getTid());
			Map<CoefficientType, VehicleCoefficientsInfo> map = new HashMap<CoefficientType, VehicleCoefficientsInfo>();
			for (CoefficientType type : CoefficientType.values()) {
				if (!type.isCustom())
					continue;
				VehicleCoefficientsInfo info = map.get(type);
				if (null == info) {
					info = new VehicleCoefficientsInfo(type);
					map.put(type, info);
				}
			}
			for (VehicleCoefficient coefficient : coefficients) {
				VehicleCoefficientsInfo info = map.get(CoefficientType.match(coefficient.getType()));
				info.addCoefficient(coefficient, null);
			}
			return Result.result(new ArrayList<VehicleCoefficientsInfo>(map.values()));
		} else {
			Result<List<VehicleCoefficientsInfo>> result = _checkSearcher(searcher, employee);
			if (!result.isSuccess())
				return result;
			return bonusService.coefficients(searcher);
		}
	}
	
	private Result<List<VehicleCoefficientsInfo>> _checkSearcher(BonusSearcher searcher, Employee employee) {
		searcher.setTid(employee.getTid());
		Region region = configService.subordinateProvince(employee.getTid());
		searcher.setSubordinateProvince(null == region ? 0 : region.getId());
		if (null == searcher.getPath())
			throw ConstConvertFailureException.errorConstException(Params.BONUS_SEARCHER);
		List<Integer> list = vehicleService.insurers(employee.getTid());
		for (int insurerId : list) {
			if (insurerId == searcher.getInsurerId())
				return Consts.RESULT.OK;
		}
		return BtkjConsts.RESULT.INSURER_NOT_EXIST;
	}
}
