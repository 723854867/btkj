package org.btkj.master.action;

import java.util.Map;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.config.pojo.TarType;
import org.btkj.config.pojo.info.ModularDocument;
import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.pojo.param.Param;
import org.rapid.util.common.message.Result;

public class MODULARS_ADMIN extends AdminAction<Param> {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<Map<Integer, ModularDocument>> execute(Administrator admin, Param param) {
		return Result.result(configManageService.modulars(TarType.ADMIN, 0));
	}
}
