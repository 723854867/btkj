package org.btkj.manager.action.tenant.employee;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.Pager;
import org.btkj.user.api.UserManageService;
import org.btkj.user.api.UserService;
import org.btkj.user.pojo.info.EmployeePagingInfo;
import org.btkj.user.pojo.submit.EmployeeSearcher;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.enums.SORT_COL;
import org.rapid.util.common.message.Result;

public class EMPLOYEE_LIST extends TenantAction {
	
	@Resource
	private UserService userService;
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
		Result<Pager<EmployeePagingInfo>> result = userManageService.employeePaging(searcher);
		Set<Integer> set = new HashSet<Integer>();
		for (EmployeePagingInfo info : result.attach().getList()) {
			set.add(info.getUid());
			set.add(info.getParentId());
		}
		List<User> users = userService.users(new ArrayList<Integer>(set));
		for (EmployeePagingInfo info : result.attach().getList()) {
			for (User user : users) {
				if (info.getUid() == user.getUid()) {
					info.setName(user.getName());
					info.setMobile(user.getMobile());
				}
				if (info.getParentId() == user.getUid()) {
					info.setParentName(user.getName());
					info.setParentMobile(user.getMobile());
				}
			}
		}
		return result;
	}
}
