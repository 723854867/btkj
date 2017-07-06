package org.btkj.manager.action.tenant.employee;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.Pager;
import org.btkj.user.api.UserManageService;
import org.btkj.user.pojo.info.EmployeePagingInfo;
import org.btkj.user.pojo.submit.EmployeeSearcher;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.enums.SORT_COL;
import org.rapid.util.common.message.Result;

public class EMPLOYEE_LIST extends TenantAction {
	
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<Pager<EmployeePagingInfo>> execute(Request request, EmployeeForm ef) {
		EmployeeSearcher searcher = request.getParam(Params.EMPLOYEE_SEARCHER);
		searcher.setTid(ef.getTenant().getTid());
		searcher.setAppId(ef.getApp().getId());
		// 排序字段处理：只能做 created 和 updated 字段的排序
		String sortCol = searcher.getSortCol();
		if (null != sortCol) {
			if (!sortCol.equalsIgnoreCase(SORT_COL.CREATED.name()) && !sortCol.equalsIgnoreCase(SORT_COL.UPDATED.name()))
				searcher.setSortCol(null);
			else
				searcher.setSortCol(searcher.getSortCol().toLowerCase());
		}
		return userManageService.employeePaging(searcher);
	}
}
