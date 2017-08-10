package org.btkj.common.action.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.pojo.po.Insurer;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.UserOldAction;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;

/**
 * 获取代理公司拥有的险企
 * 
 * @author ahab
 */
public class INSURER_LIST extends UserOldAction {
	
	@Resource
	private ConfigService configService;
	@Resource
	private VehicleService vehicleService;
	
	@Override
	protected Result<List<Insurer>> execute(Request request, User user) {
		List<Integer> tids = vehicleService.insurers(request.getParam(Params.TID));
		if (CollectionUtil.isEmpty(tids))
			return Result.result(Collections.EMPTY_LIST);
		return Result.result(new ArrayList<Insurer>(configService.insurers(tids).values()));
	}
	
	@Override
	protected boolean userIntegrityVerify(User user) {
		return true;
	}
}
