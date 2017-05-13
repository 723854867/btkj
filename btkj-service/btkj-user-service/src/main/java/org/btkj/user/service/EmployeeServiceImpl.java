package org.btkj.user.service;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.SpecialCommission;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.EmployeeInfo;
import org.btkj.pojo.info.EmployeeListInfo;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.EmployeeSearcher;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.redis.AppMapper;
import org.btkj.user.redis.EmployeeMapper;
import org.btkj.user.redis.SpecialCommissionMapper;
import org.btkj.user.redis.TenantMapper;
import org.btkj.user.redis.UserMapper;
import org.rapid.util.common.message.Result;
import org.springframework.dao.DuplicateKeyException;
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
	@Resource
	private SpecialCommissionMapper specialCommissionMapper;
	
	@Override
	public Result<Pager<EmployeeListInfo>> employeeList(EmployeeSearcher searcher) {
		return employeeMapper.employeeList(searcher);
	}
	
	@Override
	public Result<Void> employeeDisable(int id) {
		try {
			employeeMapper.UpdateState(id);
		} catch (DuplicateKeyException e) {
			return Result.result(BtkjCode.EMPLOYEE_NOT_EXIST);
		}
		 return Result.success();
	}
	
	@Override
	public Result<Void> employeeInfoSave(EmployeeInfo employeeInfo) {
			employeeMapper.EmployeeInfoSave(employeeInfo);
		 return Result.success();
	}
	
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
	
	@Override
	public EmployeeInfo employeeInfoRead(int id) {
		Employee employee = employeeMapper.getByKey(id);
		if (null == employee)
			return null;
		SpecialCommission specialCommission = specialCommissionMapper.getByeid(id);
		return new EmployeeInfo(employee,specialCommission);
	}
}