package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.courier.api.CourierService;
import org.btkj.master.LoggedAction;
import org.btkj.pojo.po.Administrator;
import org.btkj.pojo.po.Region;
import org.btkj.user.api.AppService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;

/**
 * @author ahab
 */
public class APP_ADD extends LoggedAction {
	
	@Resource
	private AppService appService;
	@Resource
	private ConfigService  configService;
	@Resource
	private CourierService courierService;

	@Override
	protected Result<?> execute(Request request, Administrator administrator) {
		Region region = configService.getRegionById(request.getParam(Params.REGION));
		if (null == region)
			throw ConstConvertFailureException.errorConstException(Params.REGION);
		
		String name = request.getParam(Params.NAME);
		int maxTenantsCount = request.getOptionalParam(Params.MAX_TENANTS_COUNT);
		int maxArticlesCount = request.getOptionalParam(Params.MAX_ARTICLES_COUNT);
		return Result.result(appService.addApp(region.getId(), name, maxTenantsCount, maxArticlesCount));
	}
}
