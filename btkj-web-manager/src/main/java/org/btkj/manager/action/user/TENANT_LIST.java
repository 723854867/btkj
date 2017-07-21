package org.btkj.manager.action.user;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.manager.action.UserAction;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.po.Region;
import org.btkj.user.api.UserManageService;
import org.btkj.user.pojo.info.TenantPagingInfo;
import org.btkj.user.pojo.submit.TenantSearcher;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.enums.SORT_COL;
import org.rapid.util.common.message.Result;

public class TENANT_LIST extends UserAction {
	
	@Resource
	private ConfigService configService;
	@Resource
	private UserManageService userManageService;
	
	@Override
	protected Result<?> execute(Request request, User user) {
		TenantSearcher searcher = request.getParam(Params.TENANT_SEARCHER);
		searcher.setAppId(user.getAppId());
		searcher.setClient(Client.TENANT_MANAGER);
		// 排序字段处理：只能做 created 和 updated 字段的排序
		String sortCol = searcher.getSortCol();
		if (null != sortCol) {
			if (!sortCol.equalsIgnoreCase(SORT_COL.CREATED.name()) && !sortCol.equalsIgnoreCase(SORT_COL.UPDATED.name()))
				searcher.setSortCol(null);
			else
				searcher.setSortCol(searcher.getSortCol().toLowerCase());
		}
		Result<Pager<TenantPagingInfo>> result = userManageService.tenantPaging(searcher);
		Set<Integer> set = new HashSet<Integer>();
		for (TenantPagingInfo info : result.attach().getList())
			set.add(info.getRegionId());
		Map<Integer, Region> regions = configService.regions(set);
		for (TenantPagingInfo info : result.attach().getList()) {
			Region region = regions.get(info.getRegionId());
			info.setRegionName(null != region ? region.getName() : null);
		}
		return result;
	}
}
