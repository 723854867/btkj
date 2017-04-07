package org.btkj.web.util.action;

import java.math.BigInteger;

import org.btkj.pojo.enums.ClientType;
import org.btkj.pojo.enums.CredentialSegment;
import org.btkj.pojo.model.Credential;
import org.btkj.pojo.model.Version;
import org.btkj.web.util.Params;
import org.btkj.web.util.ParamsUtil;
import org.btkj.web.util.Request;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;

/**
 * 租户接口：只要是租户能够调用的接口全部继承自该接口
 * 
 * @author ahab
 */
public abstract class TenantAction implements Action {

	/**
	 * 执行具体的逻辑
	 * 
	 * @param session
	 * @return
	 */
	@Override
	public Result<?> execute(Request request) {
		Credential credential = request.getParam(Params.CREDENTIAL);
		ParamsUtil.checkCredential(Params.CREDENTIAL, credential, credentialMod(credential));
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
	
	protected void assertHasClientType(Credential credential) { 
		if (null == credential)
			throw ConstConvertFailureException.errorConstException(Params.CREDENTIAL);
	}
	
	/**
	 * credential 的组成模值：credential 由 (clientType)(appId)[tenantId][uid]四部分组成
	 * 四个部分的模值分别是：1,2,4,8. 改值是多个部分模值的和值. 默认必须有前三部分，因此模值是 7.子类如果有不同的 credential
	 * 组成部分只需要重写该方法即可
	 * 
	 * @return
	 */
	protected int credentialMod(Credential credential) {
		return CredentialSegment.tenantGradeSegmentMod();
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
