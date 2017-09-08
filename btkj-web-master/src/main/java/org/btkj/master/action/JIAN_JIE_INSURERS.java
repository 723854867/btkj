package org.btkj.master.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.master.AdminAction;
import org.btkj.master.pojo.info.JianJieInsurerInfo;
import org.btkj.pojo.entity.Insurer;
import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.entity.vehicle.JianJieInsurer;
import org.btkj.pojo.param.IdParam;
import org.btkj.vehicle.api.VehicleManageService;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;

public class JIAN_JIE_INSURERS extends AdminAction<IdParam> {
	
	@Resource
	private ConfigService configService;
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<List<JianJieInsurerInfo>> execute(Admin admin, IdParam param) {
		Map<Integer, JianJieInsurer> map = vehicleManageService.jianJieInsurers(param.getId());
		if (CollectionUtil.isEmpty(map))
			return Result.result(Collections.EMPTY_LIST);
		Set<Integer> set = new HashSet<Integer>();
		for (JianJieInsurer insurer : map.values())
			set.add(insurer.getInsurerId());
		Map<Integer, Insurer> insurers = configService.insurers(set);
		List<JianJieInsurerInfo> list = new ArrayList<JianJieInsurerInfo>(map.size());
		for (JianJieInsurer insurer : map.values())
			list.add(new JianJieInsurerInfo(insurer, insurers.get(insurer.getInsurerId())));
		return Result.result(list);
	}
}
