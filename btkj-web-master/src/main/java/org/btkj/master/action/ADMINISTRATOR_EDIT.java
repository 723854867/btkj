package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.LoggedAction;
import org.btkj.master.api.MasterService;
import org.btkj.pojo.po.Administrator;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.enums.CRUD_TYPE;
import org.rapid.util.common.message.Result;

public class ADMINISTRATOR_EDIT extends LoggedAction {
	
	@Resource
	private MasterService masterService;

	@Override
	protected Result<?> execute(Request request, Administrator operator) {
		CRUD_TYPE crudType = request.getParam(Params.CRUD_TYPE);
		switch (crudType) {
		case CREATE:
			int id = masterService.administraorAdd(request.getParam(Params.NAME), request.getParam(Params.PWD));
			Result<Integer> result = Result.result(Code.OK);
			result.setAttach(id);
			return result;
		default:
			return Consts.RESULT.FORBID;
		}
	}
}
