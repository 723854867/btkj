package org.btkj.master.action;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.master.LoggedAction;
import org.btkj.pojo.po.Administrator;
import org.btkj.pojo.po.Insurer;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class INSURERS extends LoggedAction {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<List<Insurer>> execute(Request request, Administrator operator) {
		return Result.result(configManageService.insurers());
	}
}
