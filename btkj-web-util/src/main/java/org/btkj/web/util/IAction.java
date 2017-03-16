package org.btkj.web.util;

import java.math.BigInteger;

import org.btkj.pojo.model.Version;
import org.btkj.web.util.request.NetworkRequest;
import org.rapid.util.common.message.Result;

public interface IAction<REQUEST extends NetworkRequest> {
	
	/**
	 * 该接口的模值，用在权限判断。如果该接口属于公共接口(所有人都可以调用)则返回null
	 * 
	 * @return
	 */
	default BigInteger mod() {
		return null;
	}
	
	default boolean hasPrivilege(BigInteger privilege) {
		BigInteger mod = mod();
		if (null == mod)
			return true;
		return privilege.and(mod).equals(mod);
	}
	
	/**
	 * 执行具体的逻辑
	 * 
	 * @param session
	 * @return
	 */
	Result<?> execute(REQUEST session);
	
	/**
	 * 该 action 的版本，默认为 1.0
	 * 
	 * @return
	 */
	default Version version() {
		return Version.V_1_0;
	}
}
