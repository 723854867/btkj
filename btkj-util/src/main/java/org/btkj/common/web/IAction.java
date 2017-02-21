package org.btkj.common.web;

import org.btkj.common.web.session.HttpSession;
import org.rapid.util.common.message.Result;

public interface IAction {
	
	Result<?> execute(HttpSession session);
}
