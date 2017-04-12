package org.btkj.user.persistence;

import javax.annotation.Resource;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.model.AppCreateModel;
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
	public void tenantJoin(User user, Tenant tenant, Employee chief) {
		Employee employee = BeanGenerator.newEmployee(user, tenant, chief);
		employeeDao.selectByTidForUpdate(employee.getTid()); // 间隙锁
		employeeDao.updateForJoin(employee.getTid(), employee.getLeft());
		employeeDao.insert(employee);
	}

	@Transactional
	public void tenantAdd(App app, Region region, String tenantName, String pwd, User user) {
		Tenant tenant = BeanGenerator.newTenant(region.getId(), app.getId(), tenantName, pwd);
		tenantMapper.insert(tenant);
		Employee employee = BeanGenerator.newEmployee(user, tenant, null);
		employeeMapper.insert(employee);
	}

	@Transactional
	public void tenantAdd(App app, Region region, String tenantName, String mobile, String name, String identity,
			String pwd) {
		Tenant tenant = BeanGenerator.newTenant(region.getId(), app.getId(), tenantName, pwd);
		tenantMapper.insert(tenant);
		User user = BeanGenerator.newUser(app.getId(), mobile, name, identity);
		userMapper.insert(user);
		Employee employee = BeanGenerator.newEmployee(user, tenant, null);
		employeeMapper.insert(employee);
	}

	@Transactional
	public AppCreateModel addApp(int region, String appName, int maxTenantsCount, int tenantRegion, String tenantName,
			String pwd, String mobile, String name, String identity) {
		App app = BeanGenerator.newApp(region, name, maxTenantsCount);
		appMapper.insert(app);
		User user = BeanGenerator.newUser(app.getId(), mobile, name, identity);
		userMapper.insert(user);
		Tenant tenant = BeanGenerator.newTenant(tenantRegion, app.getId(), tenantName, pwd);
		tenantMapper.insert(tenant);		Employee employee = BeanGenerator.newEmployee(user, tenant, null);
		employeeMapper.insert(employee);
		return new AppCreateModel(app, tenant, user);
	}
}
