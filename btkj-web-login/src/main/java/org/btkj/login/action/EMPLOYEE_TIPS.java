package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.login.pojo.info.EmployeeInfo;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.config.Region;
import org.btkj.pojo.info.EmployeeTip;
import org.btkj.pojo.param.IdParam;
import org.btkj.user.api.EmployeeService;
import org.btkj.web.util.action.Action;
import org.rapid.util.common.message.Result;

/**
 * 雇员信息：
 * 
 * @author ahab
 */
public class EMPLOYEE_TIPS extends Action<IdParam> {
	
	@Resource
	private ConfigService configService;
	@Resource
	private EmployeeService employeeService;

	@Override
	protected Result<?> execute(IdParam param) {
		EmployeeTip employee = employeeService.employeeTip(param.getId());
		if (null == employee)
			return Result.result(BtkjCode.EMPLOYEE_NOT_EXIST);
		Region region = configService.region(employee.getTregion());
		return Result.result(new EmployeeInfo(employee, region));
	}
}
