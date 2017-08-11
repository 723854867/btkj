package org.btkj.manager.action.user;

import java.util.Map;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.config.pojo.TarType;
import org.btkj.config.pojo.info.ModularDocument;
import org.btkj.manager.action.UserAction;
import org.btkj.pojo.param.Param;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.UserPO;
import org.rapid.util.common.message.Result;

public class MODULARS_TENANT extends UserAction<Param> {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<Map<Integer, ModularDocument>> execute(AppPO app, UserPO user, Param param) {
		return Result.result(configManageService.modulars(TarType.TENANT, app.getId()));
	}
}