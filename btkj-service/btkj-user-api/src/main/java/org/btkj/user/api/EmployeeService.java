package org.btkj.user.api;

import org.btkj.pojo.info.tips.EmployeeTips;
import org.rapid.util.common.message.Result;

public interface EmployeeService {

	Result<EmployeeTips> employeeTips(int employeeId);
}
