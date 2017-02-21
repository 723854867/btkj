package org.btkj.common.web.action;

import org.btkj.common.web.IAction;
import org.btkj.common.web.Params;
import org.btkj.common.web.session.HttpSession;
import org.rapid.util.common.message.Result;

/**
 * 首页
 * 
 * @author ahab
 */
public class MAIN_PAGE implements IAction {

	@Override
	public Result<?> execute(HttpSession session) {
		String token = session.getOptionalParam(Params.TOKEN);
		if (null == token) {
			
		}
		return null;
	}
}
