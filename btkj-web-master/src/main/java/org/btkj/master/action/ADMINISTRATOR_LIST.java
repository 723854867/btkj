package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.LoggedAction;
import org.btkj.master.api.MasterService;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.po.Administrator;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class ADMINISTRATOR_LIST extends LoggedAction {
	
	@Resource
	private MasterService masterService;

	@Override
	protected Result<Pager<Administrator>> execute(Request request, Administrator operator) {
		return masterService.administrators(request.getParam(Params.PAGE), request.getParam(Params.PAGE_SIZE));
	}
}
