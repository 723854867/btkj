package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.login.pojo.info.EmployeeInfo;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.po.Region;
import org.btkj.user.api.EmployeeService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.OldAction;
import org.rapid.util.common.message.Result;

/**
 * 雇员信息：
 * 
 * @author ahab
 */
public class EMPLOYEE_TIPS extends OldAction {
	
	@Resource
	private ConfigService configService;
	@Resource
	private EmployeeService employeeService;

	@Override
	public Result<EmployeeInfo> execute(Request request) {
		Employee employee = employeeService.employee(request.getParam(Params.EMPLOYEE_ID));
		if (null == employee)
			return Result.result(BtkjCode.EMPLOYEE_NOT_EXIST);
		Region region = configService.region(employee.getRegion());
		return Result.result(new EmployeeInfo(employee, region));
	}
}
