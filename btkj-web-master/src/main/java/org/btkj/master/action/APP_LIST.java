package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.pojo.entity.Administrator;
import org.btkj.pojo.info.AppListInfo;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.AppSearcher;
import org.btkj.user.api.AppService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.message.Result;

public class APP_LIST extends AdministratorAction {
	@Resource
	private AppService appService;

	@Override
	protected Result<Pager<AppListInfo>> execute(Request request, Administrator administrator) {
		AppSearcher searcher = request.getParam(Params.APP_SEARCHER);
		return appService.appList(searcher);
	}
}
