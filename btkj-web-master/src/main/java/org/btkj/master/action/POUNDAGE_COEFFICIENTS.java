package org.btkj.master.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.enums.CoefficientType;
import org.btkj.pojo.param.NilParam;
import org.btkj.pojo.po.VehicleCoefficient;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.model.VehicleCoefficientType;
import org.rapid.util.common.message.Result;

public class POUNDAGE_COEFFICIENTS extends AdminAction<NilParam> {
	
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<?> execute(Administrator admin, NilParam param) {
		List<VehicleCoefficient> coefficients = vehicleManageService.coefficients(BtkjConsts.GLOBAL_TENANT_ID);
		Map<CoefficientType, VehicleCoefficientType> map = new HashMap<CoefficientType, VehicleCoefficientType>();
		for (CoefficientType type : CoefficientType.values()) {
			if (type.isCustom())
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
	}
}
