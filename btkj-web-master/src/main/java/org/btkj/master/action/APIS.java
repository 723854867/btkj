package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.config.pojo.entity.Api;
import org.btkj.master.LoggedAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.vo.Page;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class APIS extends LoggedAction {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<Pager<Api>> execute(Request request, Administrator operator) {
		Page page = new Page(request.getOptionalParam(Params.PAGE), request.getOptionalParam(Params.PAGE_SIZE));
		return Result.result(configManageService.apis(page));
	}
}
