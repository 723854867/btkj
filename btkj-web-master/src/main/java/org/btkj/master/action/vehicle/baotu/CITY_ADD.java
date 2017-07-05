package org.btkj.master.action.vehicle.baotu;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.entity.Administrator;
import org.btkj.pojo.entity.Region;
import org.btkj.vehicle.api.VehicleConfigService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;

public class CITY_ADD extends AdministratorAction {
	
	@Resource
	private ConfigService configService;
	@Resource
	private VehicleConfigService vehicleConfigService;

	@Override
	protected Result<Void> execute(Request request, Administrator operator) {
		Region region = configService.getRegionById(request.getParam(Params.REGION));
		if (null == region)
			throw ConstConvertFailureException.errorConstException(Params.REGION);
		String name = request.getParam(Params.NAME);
		int renewalPeriod = request.getParam(Params.NUM);
		vehicleConfigService.addCity(region.getId(), name, renewalPeriod);
		return Consts.RESULT.OK;
	}
}
