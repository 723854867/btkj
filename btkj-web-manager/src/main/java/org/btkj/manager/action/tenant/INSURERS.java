package org.btkj.manager.action.tenant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.config.Insurer;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.entity.vehicle.TenantInsurer;
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
	protected Result<List<Insurer>> execute(App app, User user, Tenant tenant, Employee employee, EmployeeParam param) {
		Map<String, TenantInsurer> map = vehicleService.insurers(tenant.getTid(), false);
		if (CollectionUtil.isEmpty(map))
			return Result.result(Collections.EMPTY_LIST);
		Set<Integer> set = new HashSet<Integer>();
		for (TenantInsurer temp : map.values()) 
			set.add(temp.getInsurerId());
		Map<Integer, Insurer> insurers = configService.insurers(set);
		return Result.result(new ArrayList<Insurer>(insurers.values()));
	}
}
