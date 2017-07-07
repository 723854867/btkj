package org.btkj.common.action;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.nonauto.api.NonAutoService;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.MainPageInfo;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.user.api.AppService;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.api.TenantService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtils;

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
	@Resource
	private TenantService tenantService;
	@Resource
	private NonAutoService nonAutoService;
	@Resource
	private EmployeeService employeeService;
	
	@Override
	protected Result<MainPageInfo> execute(Request request, App app, Client client, User user) {
		int employeeId = request.getOptionalParam(Params.EMPLOYEE_ID);
		EmployeeForm em = 0 == employeeId ? null : employeeService.getById(employeeId);
		if (0 != employeeId && null == em)
			return Result.result(BtkjCode.EMPLOYEE_NOT_EXIST);
		if (null != em && em.getUser().getUid() != user.getUid())
			return Result.result(Code.FORBID);
		
		Result<MainPageInfo> result = appService.mainPage(client, user, em);
		MainPageInfo pageInfo = result.attach();
		Region region = configService.getRegionById(0 == pageInfo.getRegionId() ? Consts.REGION_ID_CH: pageInfo.getRegionId());
		pageInfo.setRegion(region.getName());
		String nonAutoBind = null == em ? null : em.getTenant().getNonAutoBind();
		pageInfo.setNonAutoCategories(null == em ? nonAutoService.getAllCategories() : null == nonAutoBind ? null : nonAutoService.getCategoriesByIds(CollectionUtils.splitToLongList(nonAutoBind, Consts.SYMBOL_UNDERLINE)));
		return result;
	}
}
