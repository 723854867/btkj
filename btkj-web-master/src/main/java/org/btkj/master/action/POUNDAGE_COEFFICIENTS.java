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
import org.btkj.pojo.param.Param;
import org.btkj.pojo.po.VehicleCoefficient;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.model.VehicleCoefficientsInfo;
import org.rapid.util.common.message.Result;

public class POUNDAGE_COEFFICIENTS extends AdminAction<Param> {
	
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<?> execute(Administrator admin, Param param) {
		List<VehicleCoefficient> coefficients = vehicleManageService.coefficients(BtkjConsts.GLOBAL_TENANT_ID);
		Map<CoefficientType, VehicleCoefficientsInfo> map = new HashMap<CoefficientType, VehicleCoefficientsInfo>();
		for (CoefficientType type : CoefficientType.values()) {
			if (type.isCustom())
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
	}
}
