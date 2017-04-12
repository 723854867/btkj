package org.btkj.common.action;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.message.Result;

public class INVITER_INFO extends TenantAction {

//	@Override
//	protected Result<InviterInfo> execute(Request request, Credential credential) {
//		Credential ucode = request.getParam(Params.UCODE);
//		if (ucode.getApp().getId() != credential.getApp().getId())
//			return Result.result(Code.FORBID);
//		ParamsUtil.checkCredential(Params.UCODE, ucode, CredentialSegment.inviteCodeMod());
//		Region region = Beans.cacheService.getById(BtkjTables.REGION.name(), ucode.getTenant().getRegionId());
//		return Result.result(new InviterInfo(ucode.getUser(), region));
//	}

	@Override
	protected Result<?> execute(Request request, Client client, App app, Tenant tenant, User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
