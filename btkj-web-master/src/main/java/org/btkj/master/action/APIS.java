package org.btkj.master.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.config.pojo.entity.Api;
import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Admin;
import org.btkj.master.pojo.param.ApisParam;
import org.rapid.util.common.message.Result;

public class APIS extends AdminAction<ApisParam> {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<List<Api>> execute(Admin admin, ApisParam param) {
		Map<String, Api> map = configManageService.apis(param.getModularId());
		return Result.result(new ArrayList<Api>(map.values()));
	}
}
