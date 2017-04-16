package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.info.tips.EmployeeTips;
import org.btkj.user.api.EmployeeService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.Action;
import org.rapid.util.common.message.Result;

/**
 * 雇员信息：
 * 
 * @author ahab
 */
public class EMPLOYEE_INFO implements Action {
	
	@Resource
	private ConfigService configService;
	@Resource
	private EmployeeService employeeService;

	@Override
	public Result<EmployeeTips> execute(Request request) {
		int employeeId = request.getParam(Params.EMPLOYEE_ID);
		Result<EmployeeTips> result = employeeService.employeeTips(employeeId);
		if (!result.isSuccess())
			return result;
		
		Region region = configService.getRegionById(result.attach().getRegionId());
		result.attach().setRegionName(region.getName());
		return result;
	}
}
