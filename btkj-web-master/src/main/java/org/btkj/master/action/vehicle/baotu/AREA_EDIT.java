package org.btkj.master.action.vehicle.baotu;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.entity.Administrator;
import org.btkj.pojo.entity.Region;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.enums.CRUD_TYPE;
import org.rapid.util.common.enums.REGION_TYPE;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;

public class AREA_EDIT extends AdministratorAction {
	
	@Resource
	private ConfigService configService;
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<Void> execute(Request request, Administrator operator) {
		CRUD_TYPE crudType = request.getParam(Params.CRUD_TYPE);
		switch (crudType) {
		case CREATE:
			Region region = configService.getRegionById(request.getParam(Params.REGION));
			if (null == region || (region.getLevel() != REGION_TYPE.PROVINCE.mark() && region.getLevel() != REGION_TYPE.CITY.mark()))
				throw ConstConvertFailureException.errorConstException(Params.REGION);
			String name = request.getParam(Params.NAME);
			int renewalPeriod = request.getParam(Params.NUM);
			return vehicleManageService.cityAdd(region.getId(), name, renewalPeriod);
		case UPDATE:
			return vehicleManageService.cityUpdate(request.getParam(Params.REGION), request.getParam(Params.NAME), request.getParam(Params.NUM));
		case DELETE:
			vehicleManageService.cityDelete(request.getParam(Params.REGION));
			return Consts.RESULT.OK;
		default:
			return Consts.RESULT.FORBID;
		}
	}
}
