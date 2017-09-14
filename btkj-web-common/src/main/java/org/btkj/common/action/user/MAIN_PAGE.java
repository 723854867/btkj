package org.btkj.common.action.user;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.nonauto.api.NonAutoService;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.config.Region;
import org.btkj.pojo.info.MainPageInfo;
import org.btkj.pojo.model.identity.Employee;
import org.btkj.pojo.model.identity.User;
import org.btkj.user.api.AppService;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.api.TenantService;
import org.btkj.web.util.Params;
import org.btkj.web.util.action.OldUserAction;
import org.btkj.web.util.action.Request;
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
public class MAIN_PAGE extends OldUserAction {
	
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
	protected Result<MainPageInfo> execute(Request request, User user) {
		int employeeId = request.getOptionalParam(Params.EMPLOYEE_ID);
		Employee employee = 0 == employeeId ? null : employeeService.employee(employeeId);
		if (0 != employeeId && null == employee)
			return Result.result(BtkjCode.EMPLOYEE_NOT_EXIST);
		if (null != employee && employee.getUid() != user.getUid())
			return Result.result(Code.FORBID);
		
		Result<MainPageInfo> result = appService.mainPage(user, employee);
		MainPageInfo pageInfo = result.attach();
		Region region = configService.region(0 == pageInfo.getRegionId() ? Consts.REGION_ID_CH: pageInfo.getRegionId());
		pageInfo.setRegion(region.getName());
		String nonAutoBind = null == employee ? null : employee.getTenant().getNonAutoBind();
		pageInfo.setNonAutoCategories(null == employee ? nonAutoService.categories() : !StringUtil.hasText(nonAutoBind) ? null : nonAutoService.getCategoriesByIds(CollectionUtil.splitToLongList(nonAutoBind, Consts.SYMBOL_UNDERLINE)));
		return result;
	}
	
	@Override
	protected boolean userIntegrityVerify(User user) {
		return true;
	}
}
