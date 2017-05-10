package org.btkj.user.service;

import javax.annotation.Resource;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.EmoloyeeInfo;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.Pager;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.redis.AppMapper;
import org.btkj.user.redis.EmployeeMapper;
import org.btkj.user.redis.TenantMapper;
import org.btkj.user.redis.UserMapper;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Service;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	
	@Resource
	private AppMapper appMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private TenantMapper tenantMapper;
	@Resource
	private EmployeeMapper employeeMapper;
	
	@Override
	public Result<Pager<EmoloyeeInfo>> employeeList(int tid, int page, int pageSize) {
		return employeeMapper.employeeList(tid, page, pageSize);
	}
	
	@Override
//	public Result employeeDisable(int id) {
////		return employeeMapper.UpdateState(id);
//	}
	
	@Override
	public EmployeeForm getById(int employeeId) {
		Employee employee = employeeMapper.getByKey(employeeId);
		if (null == employee)
			return null;
		Tenant tenant = tenantMapper.getByKey(employee.getTid());
		App app = appMapper.getByKey(tenant.getAppId());
		User user = userMapper.getByKey(employee.getUid());
		return new EmployeeForm(app, user, tenant, employee);
	}
}