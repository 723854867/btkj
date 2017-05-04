package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.courier.api.CourierService;
import org.btkj.pojo.entity.Administrator;
import org.btkj.pojo.entity.Region;
import org.btkj.user.api.AppService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;

/**
 * 添加 app - 必须指定第一个租户和 root 用户
 * 
 * @author ahab
 */
public class APP_ADD extends AdministratorAction {
	
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
		int maxTenantsCount = request.getParam(Params.MAX_TENANTS_COUNT);
		boolean tenantAddAutonomy = request.getOptionalParam(Params.TENANT_ADD_AUTONOMY);
		return Result.result(appService.addApp(region.getId(), name, maxTenantsCount, tenantAddAutonomy));
	}
}
