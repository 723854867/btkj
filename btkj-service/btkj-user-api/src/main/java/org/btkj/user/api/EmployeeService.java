package org.btkj.user.api;

import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.info.tips.EmployeeTips;
import org.rapid.util.common.message.Result;

public interface EmployeeService {
	
	Employee getByTidAndUid(int tid, int uid);

	Result<EmployeeTips> employeeTips(int employeeId);
}
