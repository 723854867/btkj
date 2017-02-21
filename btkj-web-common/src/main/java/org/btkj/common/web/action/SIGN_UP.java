package org.btkj.common.web.action;

import org.btkj.common.web.IAction;
import org.btkj.common.web.Params;
import org.btkj.common.web.session.HttpSession;
import org.rapid.util.common.message.Result;

/**
 * 注册，完善信息
 * 
 * @author ahab
 */
public class SIGN_UP implements IAction {

	@Override
	public Result<?> execute(HttpSession session) {
		String name = session.getParam(Params.NAME);
		String mobile = session.getParam(Params.MOBILE);
		String avatar = session.getOptionalParam(Params.AVATAR);
		return null;
	}
}
