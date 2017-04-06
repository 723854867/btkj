package org.btkj.web.util.action;

import org.btkj.pojo.model.Version;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

/**
 * 执行业务逻辑
 * 
 * @author ahab
 */
public interface Action {
	
	Result<?> execute(Request request);
	
	Version version();
}
