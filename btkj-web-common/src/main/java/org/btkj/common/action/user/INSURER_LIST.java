package org.btkj.common.action.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.entity.config.Insurer;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.param.IdParam;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;

/**
 * 获取代理公司拥有的险企
 * 
 * @author ahab
 */
public class INSURER_LIST extends UserAction<IdParam> {
	
	@Resource
	private ConfigService configService;
	@Resource
	private VehicleService vehicleService;
	
	@Override
	protected Result<List<Insurer>> execute(AppPO app, UserPO user, IdParam param) {
		List<Integer> tids = vehicleService.insurers(param.getId());
		if (CollectionUtil.isEmpty(tids))
			return Result.result(Collections.EMPTY_LIST);
		return Result.result(new ArrayList<Insurer>(configService.insurers(tids).values()));
	}
}
