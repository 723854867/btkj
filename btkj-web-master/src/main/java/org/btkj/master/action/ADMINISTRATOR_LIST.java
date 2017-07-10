package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.api.MasterService;
import org.btkj.pojo.entity.Administrator;
import org.btkj.pojo.model.Pager;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.message.Result;

public class ADMINISTRATOR_LIST extends AdministratorAction {
	
	@Resource
	private MasterService masterService;

	@Override
	protected Result<Pager<Administrator>> execute(Request request, Administrator operator) {
		return masterService.administrators(request.getParam(Params.PAGE), request.getParam(Params.PAGE_SIZE));
	}
}
