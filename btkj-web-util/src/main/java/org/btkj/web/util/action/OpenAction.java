package org.btkj.web.util.action;

import java.math.BigInteger;

import org.btkj.pojo.model.ClientType;
import org.btkj.pojo.model.Credential;
import org.btkj.pojo.model.Version;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.consts.Const;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;

/**
 * 开放的接口
 * 
 * @author ahab
 */
public abstract class OpenAction implements Action {

	/**
	 * 执行具体的逻辑
	 * 
	 * @param session
	 * @return
	 */
	@Override
	public Result<?> execute(Request request) {
		Credential credential = request.getParam(Params.CREDENTIAL);
		int ctmod = clientTypeMod(request, credential);
		ClientType ct = credential.getClientType();
		if (!ct.contains(ctmod))
			return Result.result(Code.FORBID);
		return execute(request, credential);
	}
	
	protected abstract Result<?> execute(Request request, Credential credential);
	
	/**
	 * 该接口的 clientType mod 如果一个接口仅仅 app 端可以调用则为 1，如果 app 和 pc 和 manager 都可以调用，则为 7(1+2+4);
	 * 默认 app 和 pc 端都可以调用
	 * 
	 * @return
	 */
	protected int clientTypeMod(Request request, Credential credential) {
		return ClientType.APP.type() | ClientType.PC.type();
	}
	
	/**
	 * 必须要有 tenant
	 * 
	 * @param credential
	 * @param constant
	 */
	protected void assertHasTenant(Credential credential, Const<?> constant) {
		if (null == credential.getTenant())
			throw ConstConvertFailureException.errorConstException(constant);
	}
	
	/**
	 * 必须要有 user
	 * 
	 * @param credential
	 * @param constant
	 */
	protected void assertHasUser(Credential credential, Const<?> constant) {
		if (null == credential.getUser())
			throw ConstConvertFailureException.errorConstException(constant);
	}
	
	/**
	 * 该接口的模值，用在权限判断。如果该接口属于公共接口(所有人都可以调用)则返回null
	 * 
	 * @return
	 */
	public BigInteger mod() {
		return null;
	}
	
	public boolean hasPrivilege(BigInteger privilege) {
		BigInteger mod = mod();
		if (null == mod)
			return true;
		return privilege.and(mod).equals(mod);
	}
	
	/**
	 * 该 action 的版本，默认为 1.0
	 * 
	 * @return
	 */
	public Version version() {
		return Version.V_1_0;
	}
}
