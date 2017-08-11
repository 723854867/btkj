package org.btkj.master.action;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.pojo.param.Param;
import org.btkj.pojo.po.Insurer;
import org.rapid.util.common.message.Result;

public class INSURERS extends AdminAction<Param> {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<List<Insurer>> execute(Administrator admin, Param param) {
		return Result.result(configManageService.insurers());
	}
}
