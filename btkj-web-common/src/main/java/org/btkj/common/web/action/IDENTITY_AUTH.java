package org.btkj.common.web.action;

import org.btkj.common.web.IAction;
import org.btkj.common.web.Params;
import org.btkj.common.web.session.HttpSession;
import org.rapid.util.common.message.Result;

/**
 * 实名认证
 * 
 * @author ahab
 */
public class IDENTITY_AUTH implements IAction {

	@Override
	public Result<Void> execute(HttpSession session) {
		int tid = session.getParam(Params.TID);
		return null;
	}
}
