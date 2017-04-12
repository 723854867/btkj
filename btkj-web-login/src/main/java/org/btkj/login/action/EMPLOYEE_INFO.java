package org.btkj.login.action;

import javax.annotation.Resource;
import javax.swing.plaf.synth.Region;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.info.tips.EmployeeTips;
import org.btkj.user.api.EmployeeService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.Action;
import org.rapid.util.common.cache.CacheService;
import org.rapid.util.common.message.Result;

/**
 * 雇员信息：
 * 
 * @author ahab
 */
public class EMPLOYEE_INFO implements Action {
	
	@Resource
	private CacheService<?> cacheService;
	@Resource
	private EmployeeService employeeService;

	@Override
	public Result<EmployeeTips> execute(Request request) {
		int employeeId = request.getParam(Params.EMPLOYEE_ID);
		Result<EmployeeTips> result = employeeService.employeeTips(employeeId);
		if (!result.isSuccess())
			return result;
		
		Tenant tenant = cacheService.getById(BtkjTables.TENANT.name(), result.attach().getTid());
		Region region = cacheService.getById(BtkjTables.REGION.name(), tenant.getRegion());
		result.attach().setRegion(region.getName());
		return result;
	}
}
