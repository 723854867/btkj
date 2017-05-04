package org.btkj.common.action;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.AppMainPageInfo;
import org.btkj.pojo.info.mainpage.IMainPageInfo;
import org.btkj.user.api.AppService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.region.RegionUtil;

/**
 * 首页信息
 * 
 * @author ahab
 */
public class MAIN_PAGE extends UserAction {
	
	@Resource
	private AppService appService;
	@Resource
	private ConfigService configService;
	
	@Override
	protected Result<IMainPageInfo> execute(Request request, App app, Client client, User user) {
		Result<IMainPageInfo> result = appService.mainPage(client, user, request.getOptionalParam(Params.EMPLOYEE_ID));
		if (result.attach() instanceof AppMainPageInfo) {
			AppMainPageInfo pageInfo = (AppMainPageInfo) result.attach();
			Region region = configService.getRegionById(0 == pageInfo.getRegionId() ? RegionUtil.CH_REGION_ID : pageInfo.getRegionId());
			pageInfo.setRegion(region.getName());
		}
		return result;
	}
}
