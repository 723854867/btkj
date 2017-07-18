package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.bo.EmployeeForm;
import org.btkj.pojo.po.Region;
import org.btkj.pojo.vo.EmployeeTips;
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
public class EMPLOYEE_TIPS implements Action {
	
	@Resource
	private ConfigService configService;
	@Resource
	private EmployeeService employeeService;

	@Override
	public Result<EmployeeTips> execute(Request request) {
		int employeeId = request.getParam(Params.EMPLOYEE_ID);
		EmployeeForm form = employeeService.getById(employeeId);
		if (null == form)
			return Result.result(BtkjCode.EMPLOYEE_NOT_EXIST);
		Region region = configService.getRegionById(form.getTenant().getRegion());
		return Result.result(new EmployeeTips(form, region));
	}
}
