package org.btkj.common.action;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Insurer;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.user.api.TenantService;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;

/**
 * 获取代理公司拥有的险企
 * 
 * @author ahab
 */
public class INSURER_LIST extends UserAction {
	
	@Resource
	private TenantService tenantService;
	@Resource
	private ConfigService configService;
	@Resource
	private VehicleService vehicleService;
	
	@Override
	protected Result<List<Insurer>> execute(Request request, App app, Client client, User operator) {
		int tid = request.getParam(Params.TID);
		Tenant tenant = tenantService.getTenantById(tid);
		if (null == tenant)
			return Result.result(BtkjCode.TENANT_NOT_EXIST);
		List<Integer> list = vehicleService.insurers(tenant);
		if (CollectionUtil.isEmpty(list))
			return Result.result(Collections.EMPTY_LIST);
		return Result.result(configService.insurers(list));
	}
}
