package org.btkj.user.persistence;

import javax.annotation.Resource;

import org.btkj.pojo.Region;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.user.BeanGenerator;
import org.btkj.user.persistence.dao.EmployeeDao;
import org.btkj.user.redis.mapper.AppMapper;
import org.btkj.user.redis.mapper.EmployeeMapper;
import org.btkj.user.redis.mapper.TenantMapper;
import org.btkj.user.redis.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tx")
public class Tx {
	
	@Resource
	private AppMapper appMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private TenantMapper tenantMapper;
	@Resource
	private EmployeeMapper employeeMapper;
	@Resource
	private EmployeeDao employeeDao;
	
	@Transactional
	public void tenantJoin(Employee employee) {
		employeeDao.selectByTidForUpdate(employee.getTid());		// 间隙锁
		employeeDao.updateForJoin(employee.getTid(), employee.getLeft() - 1);
		employeeDao.insert(employee);
	}
	
	public void tenantJoin(int appId, String mobile, String name, String identity) {
		User user = BeanGenerator.newUser(appId, mobile, identity, name);
	}
	
	@Transactional
	public void tenantAdd(App app, Region region, String tenantName, String mobile, String name, String identity) {
		Tenant tenant = BeanGenerator.newTenant(region, app.getId(), tenantName);
		tenant.setPrivilege("0");
		tenantMapper.insert(tenant);
		User user = BeanGenerator.newUser(app.getId(), mobile, identity, name);
		userMapper.insert(user);
		Employee employee = BeanGenerator.newEmployee(user, tenant, null);
		employeeMapper.insert(employee);
	}
	
	@Transactional
	public void tenantAdd(String appName, Region region, String tenantName, String mobile, String name, String identity) {
		App app = BeanGenerator.newApp(appName);
		appMapper.insert(app);
		Tenant tenant = BeanGenerator.newTenant(region, app.getId(), tenantName);
		tenant.setPrivilege("0");
		tenantMapper.insert(tenant);
		User user = BeanGenerator.newUser(app.getId(), mobile, identity, name);
		userMapper.insert(user);
		Employee employee = BeanGenerator.newEmployee(user, tenant, null);
		employeeMapper.insert(employee);
	}
}
