package org.btkj.master.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.info.config.AreaInfo;
import org.btkj.pojo.param.NilParam;
import org.btkj.web.util.action.AdminAction;
import org.rapid.util.common.message.Result;

public class AREAS extends AdminAction<NilParam> {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<List<AreaInfo>> execute(Admin admin, NilParam param) {
		return Result.result(new ArrayList<AreaInfo>(configManageService.areas().values()));
	}
}
