package org.btkj.manager.action;

import org.btkj.pojo.enums.Client;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.UserAction;

public abstract class PlatformAction extends UserAction {

	@Override
	protected Client client(Request request) {
		return Client.PC;
	}
}
