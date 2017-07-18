package org.btkj.manager.action.platform;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.manager.action.PlatformAction;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.Region;
import org.btkj.pojo.po.UserPO;
import org.btkj.user.api.UserManageService;
import org.btkj.user.pojo.info.TenantPagingInfo;
import org.btkj.user.pojo.submit.TenantSearcher;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.enums.SORT_COL;
import org.rapid.util.common.message.Result;

public class TENANT_LIST extends PlatformAction {
	
	@Resource
	private ConfigService configService;
	@Resource
	private UserManageService userManageService;
	
	@Override
	protected Result<Pager<TenantPagingInfo>> execute(Request request, AppPO app, UserPO operator) {
		TenantSearcher searcher = request.getParam(Params.TENANT_SEARCHER);
		searcher.setAppId(app.getId());
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
		List<Region> regions = configService.getRegions(new ArrayList<Integer>(set));
		for (Region region : regions) {
			for (TenantPagingInfo info : result.attach().getList()) {
				if (info.getTid() == region.getId())
					info.setRegionName(region.getName());
			}
		}
		return result;
	}
}
