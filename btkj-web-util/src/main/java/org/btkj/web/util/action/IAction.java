package org.btkj.web.util.action;

import org.btkj.pojo.model.Version;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.serializer.SerializeUtil;

import com.google.gson.Gson;

public interface IAction {

	Result<?> execute(Request request);
	
	default Gson gson() {
		return SerializeUtil.JsonUtil.GSON;
	}
	
	default Version version() {
		return Version.V_1_0;
	}
}
