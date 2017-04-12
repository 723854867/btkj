package org.btkj.common.action.impl;

import org.btkj.common.Beans;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.enums.CredentialSegment;
import org.btkj.pojo.info.InviterInfo;
import org.btkj.pojo.model.Credential;
import org.btkj.web.util.Params;
import org.btkj.web.util.ParamsUtil;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

public class INVITER_INFO extends TenantAction {

	@Override
	protected Result<InviterInfo> execute(Request request, Credential credential) {
		Credential ucode = request.getParam(Params.UCODE);
		if (ucode.getApp().getId() != credential.getApp().getId())
			return Result.result(Code.FORBID);
		ParamsUtil.checkCredential(Params.UCODE, ucode, CredentialSegment.inviteCodeMod());
		Region region = Beans.cacheService.getById(BtkjTables.REGION.name(), ucode.getTenant().getRegionId());
		return Result.result(new InviterInfo(ucode.getUser(), region));
	}

	@Override
	protected int credentialMod(Credential credential) {
		return CredentialSegment.appGradeSegmentMod();
	}
}
