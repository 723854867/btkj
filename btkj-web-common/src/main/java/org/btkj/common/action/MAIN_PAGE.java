package org.btkj.common.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.nonauto.api.NonAutoService;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.NonAutoBind;
import org.btkj.pojo.entity.NonAutoCategory;
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
import org.rapid.util.common.consts.code.Code;
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
		Region region = configService.getRegionById(0 == pageInfo.getRegionId() ? RegionUtil.CH_REGION_ID : pageInfo.getRegionId());
		pageInfo.setRegion(region.getName());
			
		List<NonAutoCategory> categories = null;
		if (null == em)
			categories = nonAutoService.getAllCategories();
		else {
			List<NonAutoBind> binds = tenantService.getNonAutoBinds(em.getTenant().getTid());
			if (null != binds && !binds.isEmpty()) {
				List<Long> cids = new ArrayList<Long>(binds.size());
				for (int i = 0, len = binds.size(); i < len; i++)
					cids.add(Long.valueOf((binds.get(i).getCid())));
				categories = nonAutoService.getCategoriesByIds(cids);
			}
		}
		pageInfo.setNonAutoCategories(categories);
		return result;
	}
}
