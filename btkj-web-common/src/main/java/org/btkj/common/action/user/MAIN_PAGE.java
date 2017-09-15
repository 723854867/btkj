package org.btkj.common.action.user;

import javax.annotation.Resource;

import org.btkj.common.pojo.param.MainPageParam;
import org.btkj.config.api.ConfigService;
import org.btkj.nonauto.api.NonAutoService;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.config.Region;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.info.EmployeeTip;
import org.btkj.pojo.info.MainPageInfo;
import org.btkj.user.api.AppService;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.api.TenantService;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.StringUtil;

/**
 * 首页信息
 * 
 * @author ahab
 */
public class MAIN_PAGE extends UserAction<MainPageParam> {
	
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
	protected Result<MainPageInfo> execute(App app, User user, MainPageParam param) {
		EmployeeTip employee = null == param.getEmployeeId() ? null : employeeService.employeeTip(param.getEmployeeId());
		if (null != param.getEmployeeId() && null == employee)
			return Result.result(BtkjCode.EMPLOYEE_NOT_EXIST);
		if (null != employee && employee.getUid() != user.getUid())
			return Result.result(Code.FORBID);
		
		Result<MainPageInfo> result = appService.mainPage(user, employee);
		MainPageInfo pageInfo = result.attach();
		Region region = configService.region(0 == pageInfo.getRegionId() ? Consts.REGION_ID_CH: pageInfo.getRegionId());
		pageInfo.setRegion(region.getName());
		String nonAutoBind = null == employee ? null : employee.getNonAutoBind();
		pageInfo.setNonAutoCategories(null == employee ? nonAutoService.categories() : !StringUtil.hasText(nonAutoBind) ? null : nonAutoService.getCategoriesByIds(CollectionUtil.splitToLongList(nonAutoBind, Consts.SYMBOL_UNDERLINE)));
		return result;
	}
}
