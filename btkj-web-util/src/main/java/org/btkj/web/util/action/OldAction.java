package org.btkj.web.util.action;

import org.btkj.pojo.enums.Client;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;

/**
 * 执行业务逻辑
 * 
 * @author ahab
 */
public abstract class OldAction implements IAction {
	
	/**
	 * 默认从
	 * 
	 * @param request
	 * @return
	 */
	protected Client client(Request request) {
		return request.getParam(Params.CLIENT);
	}
}
