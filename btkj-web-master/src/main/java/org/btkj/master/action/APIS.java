package org.btkj.master.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.master.AdminAction;
import org.btkj.master.pojo.param.ApisParam;
import org.btkj.pojo.entity.config.Api;
import org.btkj.pojo.entity.master.Admin;
import org.rapid.util.common.message.Result;

public class APIS extends AdminAction<ApisParam> {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<List<Api>> execute(Admin admin, ApisParam param) {
		Map<Integer, Api> map = configManageService.apis(param.getModularId());
		return Result.result(new ArrayList<Api>(map.values()));
	}
}
