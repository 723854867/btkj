package org.btkj.manager.action.user;

import java.util.Map;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.config.pojo.TarType;
import org.btkj.config.pojo.info.ModularDocument;
import org.btkj.manager.action.UserAction;
import org.btkj.pojo.param.NilParam;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.UserPO;
import org.rapid.util.common.message.Result;

public class MODULARS_USER extends UserAction<NilParam> {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<Map<Integer, ModularDocument>> execute(AppPO app, UserPO user, NilParam param) {
		return Result.result(configManageService.modulars(TarType.USER, app.getId()));
	}
}
