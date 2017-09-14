package org.btkj.master.action;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.pojo.entity.config.Insurer;
import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.param.NilParam;
import org.btkj.web.util.action.AdminAction;
import org.rapid.util.common.message.Result;

public class INSURERS extends AdminAction<NilParam> {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<List<Insurer>> execute(Admin admin, NilParam param) {
		return Result.result(configManageService.insurers());
	}
}
