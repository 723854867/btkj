package org.btkj.manager.action.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.manager.action.UserAction;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.info.config.AreaInfo;
import org.btkj.pojo.param.NilParam;
import org.rapid.util.common.message.Result;

public class AREAS extends UserAction<NilParam> {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<List<AreaInfo>> execute(AppPO app, UserPO user, NilParam param) {
		return Result.result(new ArrayList<AreaInfo>(configManageService.areas().values()));
	}
}
