package org.btkj.manager.action.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.config.pojo.info.AreaInfo;
import org.btkj.manager.action.UserAction;
import org.btkj.pojo.param.Param;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.UserPO;
import org.rapid.util.common.message.Result;

public class AREAS extends UserAction<Param> {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<List<AreaInfo>> execute(AppPO app, UserPO user, Param param) {
		return Result.result(new ArrayList<AreaInfo>(configManageService.areas().values()));
	}
}
