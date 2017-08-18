package org.btkj.manager.action.tenant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.Insurer;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.vehicle.api.VehicleService;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;

public class INSURERS extends EmployeeAction<EmployeeParam> {

	@Resource
	private ConfigService configService;
	@Resource
	private VehicleService vehicleService;
	
	@Override
	protected Result<List<Insurer>> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeParam param) {
		List<Integer> tids = vehicleService.insurers(tenant.getTid());
		if (CollectionUtil.isEmpty(tids))
			return Result.result(Collections.EMPTY_LIST);
		return Result.result(new ArrayList<Insurer>(configService.insurers(tids).values()));
	}
}
