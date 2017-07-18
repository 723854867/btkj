package org.btkj.master.action.tenant;

import org.btkj.master.LoggedAction;
import org.btkj.pojo.po.Administrator;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class TENANT_SET extends LoggedAction {

	@Override
	protected Result<Void> execute(Request request, Administrator operator) {
		String jianJieId = request.getParam(Params.JIAN_JIE_ID);
		return null;
	}
}
