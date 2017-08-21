package org.btkj.master.action;

import java.util.Map;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.config.pojo.TarType;
import org.btkj.config.pojo.info.ModularDocument;
import org.btkj.master.AdminAction;
import org.btkj.master.MasterUtil;
import org.btkj.master.pojo.entity.Admin;
import org.btkj.pojo.enums.ModularType;
import org.btkj.pojo.param.NilParam;
import org.rapid.util.common.message.Result;

public class MODULARS_BT extends AdminAction<NilParam> {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<Map<Integer, ModularDocument>> execute(Admin admin, NilParam param) {
		return Result.result(configManageService.modulars(MasterUtil.hasFullPrivileges(admin) ? 0 : admin.getId(), TarType.ADMIN, ModularType.BT));
	}
}
