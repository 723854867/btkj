package org.btkj.manager.action.tenant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.enums.CoefficientType;
import org.btkj.vehicle.api.BonusService;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.vehicle.pojo.model.VehicleCoefficientType;
import org.btkj.vehicle.pojo.param.PoundageCoefficientsParam;
import org.rapid.util.common.message.Result;

/**
 * 获取系数表
 * 
 * @author ahab
 */
public class POUNDAGE_COEFFICIENTS extends EmployeeAction<PoundageCoefficientsParam> {
	
	@Resource
	private BonusService bonusService;
	@Resource
	private ConfigService configService;
	@Resource
	private VehicleService vehicleService;
	@Resource
	private VehicleManageService vehicleManageService;
	
	@Override
	protected Result<?> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, PoundageCoefficientsParam param) {
		if (param.isAll()) {
			List<VehicleCoefficient> coefficients = vehicleManageService.coefficients(employee.getTid());
			Map<CoefficientType, VehicleCoefficientType> map = new HashMap<CoefficientType, VehicleCoefficientType>();
			for (CoefficientType type : CoefficientType.values()) {
				if (!type.isCustom())
					continue;
				VehicleCoefficientType info = map.get(type);
				if (null == info) {
					info = new VehicleCoefficientType(type);
					map.put(type, info);
				}
			}
			for (VehicleCoefficient coefficient : coefficients) {
				VehicleCoefficientType info = map.get(CoefficientType.match(coefficient.getType()));
				info.addCoefficient(coefficient, null);
			}
			return Result.result(new ArrayList<VehicleCoefficientType>(map.values()));
		} else {
			param.setTid(tenant.getTid());
			Region region = configService.subordinateProvince(tenant.getRegion());
			param.setSubordinateProvince(null == region ? 0 : region.getId());
			return bonusService.poundageCoefficients(param);
		}
	}
}
