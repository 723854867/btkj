package org.btkj.web.util.action;

import org.btkj.pojo.bo.Version;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public interface IAction {

	Result<?> execute(Request request);
	
	default Version version() {
		return Version.V_1_0;
	}
}
