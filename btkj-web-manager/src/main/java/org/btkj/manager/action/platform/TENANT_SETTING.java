package org.btkj.manager.action.platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.manager.action.PlatformAction;
import org.btkj.nonauto.api.NonAutoService;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.NonAutoCategory;
import org.btkj.pojo.entity.User;
import org.btkj.user.api.UserManageService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.lang.CollectionUtils;

/**
 * 商家设置
 * 
 * @author ahab
 */
public class TENANT_SETTING extends PlatformAction {
	
	@Resource
	private NonAutoService nonAutoService;
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<?> execute(Request request, App app, User operator) {
		int tid = request.getParam(Params.TID);
		String nonAutoBind = request.getOptionalParam(Params.NON_AUTO_BIND);
		Set<Long> set = null;
		try {
			set = null == nonAutoBind ? null : CollectionUtils.splitToLongSet(nonAutoBind, Consts.SYMBOL_UNDERLINE);
		} catch (Exception e) {
			throw ConstConvertFailureException.errorConstException(Params.NON_AUTO_BIND);
		}
		List<NonAutoCategory> categories = null == set ? null : nonAutoService.getCategoriesByIds(new ArrayList<Long>(set));
		if (null != set) {
			set.clear();
			for (NonAutoCategory category : categories)
				set.add(category.get_id());
		}
		return userManageService.tenantSetting(tid, set);
	}
}
