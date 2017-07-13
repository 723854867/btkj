package org.btkj.manager.action.tenant;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.nonauto.api.NonAutoService;
import org.btkj.pojo.entity.NonAutoCategory;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.user.api.UserManageService;
import org.btkj.user.pojo.submit.TenantSettingsSubmit;
import org.btkj.vehicle.pojo.BonusScaleType;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.lang.CollectionUtils;

/**
 * 商户自己设置自己的一些属性
 * 
 * @author ahab
 *
 */
public class TENANT_SET extends TenantAction {
	
	@Resource
	private NonAutoService nonAutoService;
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<Void> execute(Request request, EmployeeForm ef) {
		TenantSettingsSubmit submit = request.getParam(Params.TENANT_SETTINGS_SUBMIT);
		_check(submit);
		userManageService.tenantSet(ef.getTenant(), submit);
		return Consts.RESULT.OK;
	}
	
	private void _check(TenantSettingsSubmit submit) {
		Set<Long> nonAutoBind = submit.getNonAutoBind();
		if (!CollectionUtils.isEmpty(nonAutoBind)) {
			List<NonAutoCategory> categories = nonAutoService.getCategoriesByIds(new ArrayList<Long>(nonAutoBind));
			nonAutoBind.clear();
			for (NonAutoCategory category : categories)
				nonAutoBind.add(category.get_id());
		}
		if ((null != submit.getBonusScaleCountMod() && !BonusScaleType.illegalMod(submit.getBonusScaleCountMod()))
				|| (null != submit.getBonusScaleRewardMod() && !BonusScaleType.illegalMod(submit.getBonusScaleRewardMod()))) 
			throw ConstConvertFailureException.errorConstException(Params.TENANT_SETTINGS_SUBMIT);
	}
}
