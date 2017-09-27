package org.btkj.master.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.entity.vehicle.TenantInsurer;
import org.btkj.pojo.param.IdParam;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.web.util.action.AdminAction;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;

public class TENANT_INSURERS extends AdminAction<IdParam> {
	
	@Resource
	private ConfigService configService;
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<List<TenantInsurer>> execute(Admin admin, IdParam param) {
		Map<String, TenantInsurer> map = vehicleService.insurers(param.getId(), null);
		if (CollectionUtil.isEmpty(map))
			return Result.result(Collections.EMPTY_LIST);
		return Result.result(new ArrayList<TenantInsurer>(map.values()));
	}
}
