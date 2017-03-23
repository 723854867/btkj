package org.btkj.common.web.action;

import org.btkj.common.CommonParams;
import org.btkj.common.web.Beans;
import org.btkj.common.web.Request;
import org.btkj.pojo.model.Credential;
import org.rapid.util.common.message.Result;

public abstract class CommonAction implements BtkjAction, Beans {

	@Override
	public final Result<?> execute(Request request) {
		return execute(request, request.getParam(CommonParams.CREDENTIAL));
	}
	
	protected abstract Result<?> execute(Request request, Credential credential);
}
