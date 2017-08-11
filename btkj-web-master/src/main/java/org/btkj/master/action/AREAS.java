package org.btkj.master.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.config.pojo.info.AreaInfo;
import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.pojo.param.Param;
import org.rapid.util.common.message.Result;

public class AREAS extends AdminAction<Param> {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<List<AreaInfo>> execute(Administrator admin, Param param) {
		return Result.result(new ArrayList<AreaInfo>(configManageService.areas().values()));
	}
}
