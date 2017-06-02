package org.btkj.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.SpecialBonus;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.EmployeeState;
import org.btkj.pojo.info.EmployeeInfo;
import org.btkj.pojo.info.EmployeeListInfo;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.EmployeeSearcher;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.redis.AppMapper;
import org.btkj.user.redis.EmployeeMapper;
import org.btkj.user.redis.SpecialBonusMapper;
import org.btkj.user.redis.TenantMapper;
import org.btkj.user.redis.UserMapper;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtils;
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
	private SpecialBonusMapper specialBonusMapper;
	
	@Override
	public Result<Pager<EmployeeListInfo>> employeeList(EmployeeSearcher searcher) {
		return employeeMapper.employeeList(searcher);
	}
	
	@Override
	public Result<Void> employeeState(int id, EmployeeState state) {
		Employee employee = employeeMapper.getByKey(id);
		if(null == employee)
			return Result.result(BtkjCode.EMPLOYEE_NOT_EXIST);
		if (employee.getState() != state.mark()) {
			employee.setState(state.mark());
			employee.setUpdated(DateUtils.currentTime());
			employeeMapper.update(employee);
		}
		return Result.success();
	}
	
	@Override
	public Result<Void> employeeEdit(EmployeeInfo employeeInfo) {
		Employee employee = employeeMapper.getByKey(employeeInfo.getId());
		if(null == employee)
			return Result.result(BtkjCode.EMPLOYEE_NOT_EXIST); 
			employeeMapper.employeeEdit(employeeInfo);
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
	public EmployeeInfo employeeInfo(int id) {
		Employee employee = employeeMapper.getByKey(id);
		if (null == employee)
			return null;
		SpecialBonus specialBonus = specialBonusMapper.getByeid(id);
		if(null == specialBonus)
			return new EmployeeInfo(employee);
		else
		return new EmployeeInfo(employee,specialBonus);
	}
	
	@Override
	public Pager<Employee> team(EmployeeForm form) {
		
		return null;
	}
	
	@Override
	public List<Employee> employees(List<Integer> ids) {
		return employeeMapper.getWithinKey(ids);
	}
}