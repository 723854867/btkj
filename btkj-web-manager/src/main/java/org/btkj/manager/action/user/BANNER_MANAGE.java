package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.manager.action.UserAction;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.user.api.UserManageService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.Consts;
import org.rapid.util.common.enums.CRUD_TYPE;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;

/**
 * 轮播图编辑
 * 
 * @author ahab
 */
public class BANNER_MANAGE extends UserAction {
	
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<?> execute(Request request, User user) {
		CRUD_TYPE crudType = request.getParam(Params.CRUD_TYPE);
		switch (crudType) {
		case CREATE:
			int tid = request.getOptionalParam(Params.TID);
			String icon = request.getParam(Params.ICON);
			String link = request.getParam(Params.LINK);
			int idx = request.getParam(Params.IDX);
			if (idx < 1 || idx > 3)
				throw ConstConvertFailureException.errorConstException(Params.IDX);
			return userManageService.bannerAdd(user.getAppId(), tid, idx, icon, link);
		case DELETE:
			int id = request.getOptionalParam(Params.ID);
			return userManageService.bannerDelete(id);
		case UPDATE:
			id = request.getOptionalParam(Params.ID);
			icon = request.getParam(Params.ICON);
			link = request.getParam(Params.LINK);
			return userManageService.bannerEdit(id, icon, link);
		default:
			return Consts.RESULT.FORBID;
		}
	}
}
