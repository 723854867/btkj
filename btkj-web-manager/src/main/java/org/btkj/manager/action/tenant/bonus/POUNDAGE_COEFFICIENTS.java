package org.btkj.manager.action.tenant.bonus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.enums.CoefficientType;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.Region;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.pojo.po.VehicleCoefficient;
import org.btkj.vehicle.api.BonusService;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.vehicle.pojo.model.VehicleCoefficientsInfo;
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
			param.setTid(tenant.getTid());
			Region region = configService.subordinateProvince(param.getTid());
			param.setSubordinateProvince(null == region ? 0 : region.getId());
			return bonusService.poundageCoefficients(param);
		}
	}
}
