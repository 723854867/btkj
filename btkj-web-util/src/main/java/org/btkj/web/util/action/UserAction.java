package org.btkj.web.util.action;

import org.btkj.pojo.model.Credential;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;

/**
 * 必须要有 User 的 action
 * 
 * @author ahab
 *
 */
public abstract class UserAction extends OpenAction {

	@Override
	public Result<?> execute(Request request) {
		Credential credential = request.getParam(Params.CREDENTIAL);
		if (null == credential.getUser())
			throw ConstConvertFailureException.errorConstException(Params.CREDENTIAL);
		return execute(request, credential);
	}
}
