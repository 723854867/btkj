package org.btkj.manager.web.action.apply;

import org.btkj.manager.Beans;
import org.btkj.manager.web.Request;
import org.btkj.manager.web.action.Action;
import org.btkj.pojo.info.ApplyInfo;
import org.btkj.pojo.model.Pager;
import org.btkj.web.util.Params;
import org.rapid.util.common.message.Result;

public class APPLY_LIST extends Action {

	@Override
	public Result<Pager<ApplyInfo>> execute(Request session) {
		int tid = session.getParam(Params.TID);
		int page = session.getOptionalParam(Params.PAGE);
		int pageSize = session.getOptionalParam(Params.PAGE_SIZE);
		return Beans.tenantService.applyList(tid, page, pageSize);
	}
}
