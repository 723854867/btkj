package org.btkj.manager.web.action.apply;

import org.btkj.manager.web.Request;
import org.btkj.manager.web.action.Action;
import org.btkj.pojo.Region;
import org.btkj.web.util.Params;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;

/**
 * 添加代理商
 * 
 * @author ahab
 */
public class TENANT_ADD extends Action {

	@Override
	public Result<?> execute(Request session) {
		int regionCode = session.getParam(Params.REGION);
		Region region = cacheService.getRegionByCode(regionCode);
		if (null == region)
			throw ConstConvertFailureException.errorConstException(Params.REGION);
		String appName = session.getOptionalParam(Params.APP_NAME);
		String tenantName = session.getParam(Params.TENANT_NAME);
		String name = session.getParam(Params.NAME);
		String mobile = session.getParam(Params.MOBILE);
		String identity = session.getParam(Params.IDENTITY);
		return tenantService.tenantAdd(region, appName, tenantName, name, mobile, identity);
	}
}
