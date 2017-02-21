package org.btkj.common.web.action;

import org.btkj.common.web.IAction;
import org.btkj.common.web.Params;
import org.btkj.common.web.session.HttpSession;
import org.rapid.util.common.message.Result;

/**
 * 获取验证码
 * 
 * @author ahab
 */
public class CAPTCHA_OBTAIN implements IAction {

	@Override
	public Result<?> execute(HttpSession session) {
		String mobile = session.getParam(Params.MOBILE);
		return null;
	}
}
