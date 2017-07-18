package org.btkj.manager.action.platform;

import javax.annotation.Resource;

import org.btkj.manager.action.PlatformAction;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.user.api.UserManageService;
import org.btkj.user.pojo.info.UserPagingInfo;
import org.btkj.user.pojo.submit.UserSearcher;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.enums.SORT_COL;
import org.rapid.util.common.message.Result;

public class USER_LIST extends PlatformAction {
	
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<Pager<UserPagingInfo>> execute(Request request, AppPO app, UserPO operator) {
		UserSearcher searcher = request.getParam(Params.USER_SEARCHER);
		searcher.setAppId(app.getId());
		searcher.setClient(Client.TENANT_MANAGER);
		// 排序字段处理：只能做 created 和 updated 字段的排序
		String sortCol = searcher.getSortCol();
		if (null != sortCol) {
			if (!sortCol.equalsIgnoreCase(SORT_COL.CREATED.name()) && !sortCol.equalsIgnoreCase(SORT_COL.UPDATED.name())
					&& !sortCol.equals("app_login_time") && !sortCol.equals("pc_login_time"))
				searcher.setSortCol(null);
			else
				searcher.setSortCol(searcher.getSortCol().toLowerCase());
		}
		return userManageService.userPaging(searcher);
	}
}
