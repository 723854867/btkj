package org.btkj.master.action.tenant;

import javax.annotation.Resource;

import org.btkj.master.LoggedAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.pojo.enums.Client;
import org.btkj.user.api.UserManageService;
import org.btkj.user.pojo.submit.TenantSearcher;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.enums.SORT_COL;
import org.rapid.util.common.message.Result;

public class TENANT_LIST extends LoggedAction {
	
	@Resource
	private UserManageService userManageService;
	
	@Override
	protected Result<?> execute(Request request, Administrator operator) {
		TenantSearcher searcher = request.getParam(Params.TENANT_SEARCHER);
		searcher.setClient(Client.BAO_TU_MANAGER);
		// 排序字段处理：只能做 created 和 updated 字段的排序
		String sortCol = searcher.getSortCol();
		if (null != sortCol) {
			if (!sortCol.equalsIgnoreCase(SORT_COL.CREATED.name()) && !sortCol.equalsIgnoreCase(SORT_COL.UPDATED.name()))
				searcher.setSortCol(null);
			else
				searcher.setSortCol(searcher.getSortCol().toLowerCase());
		}
		return userManageService.tenantPaging(searcher);
	}
}
