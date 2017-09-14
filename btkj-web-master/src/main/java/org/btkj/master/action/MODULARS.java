package org.btkj.master.action;

import java.util.Map;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.master.pojo.param.ModularsParam;
import org.btkj.pojo.BtkjUtil;
import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.enums.ModularType;
import org.btkj.pojo.info.config.ModularDocument;
import org.btkj.web.util.action.AdminAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

public class MODULARS extends AdminAction<ModularsParam> {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<Map<Integer, ModularDocument>> execute(Admin admin, ModularsParam param) {
		if (param.getType() != ModularType.ADMIN)
			param.setTarId(null);
		if (!BtkjUtil.isTopRole(admin))
			return Consts.RESULT.NO_PRIVILEGE;
		if (null != param.getTarId()) {
			if (param.getTarId() == admin.getId())
				return Consts.RESULT.FORBID;
			Admin target = cloudService.admin(param.getTarId());
			if (null == target)
				return Consts.RESULT.USER_NOT_EXIST;
		}
		return Result.result(configManageService.modulars(param.getTarId(), param.getType()));
	}
}
