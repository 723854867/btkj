package org.btkj.manager.action.user;

import java.util.Map;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.config.pojo.TarType;
import org.btkj.config.pojo.info.ModularDocument;
import org.btkj.manager.ManagerUtil;
import org.btkj.manager.action.UserAction;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.enums.ModularType;
import org.btkj.pojo.param.NilParam;
import org.rapid.util.common.message.Result;

public class MODULARS_USER extends UserAction<NilParam> {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<Map<Integer, ModularDocument>> execute(AppPO app, UserPO user, NilParam param) {
		int tarId = user.getUid();
		TarType tarType = TarType.USER;
		if (ManagerUtil.hasFullPrivileges(user)) {
			tarId = app.getId();
			tarType = TarType.APP;
		}
		return Result.result(configManageService.modulars(tarId, tarType, ModularType.APP));
	}
}
