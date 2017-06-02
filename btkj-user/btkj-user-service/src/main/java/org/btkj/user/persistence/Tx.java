package org.btkj.user.persistence;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.TxCallback;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.exception.BusinessException;
import org.btkj.user.BeanGenerator;
import org.btkj.user.persistence.dao.AppDao;
import org.btkj.user.persistence.dao.EmployeeDao;
import org.btkj.user.persistence.dao.TenantDao;
import org.btkj.user.persistence.dao.UserDao;
import org.btkj.user.redis.EmployeeMapper;
import org.btkj.user.redis.TenantMapper;
import org.btkj.user.redis.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 所有事物中的 mapper 不能直接 insert，防止前面 insert 成功刷入缓存了，
 * 但是 后面的 insert 失败导致所有 db 数据回滚，但是redis缓存数据没有回滚
 * 
 * @author ahab
 */
@Service("tx")
public class Tx {

	@Resource
	private AppDao appDao;
	@Resource
	private UserDao userDao;
	@Resource
	private TenantDao tenantDao;
	@Resource
	private EmployeeDao employeeDao;
	
	@Resource
	private UserMapper userMapper;
	@Resource
	private TenantMapper tenantMapper;
	@Resource
	private EmployeeMapper employeeMapper;
	
	@Transactional
	public TxCallback tenantAdd(App app, Region region, String tname, User user) {
		appDao.selectByKeyForUpdate(app.getId());
		if (0 < app.getMaxTenantsCount()) {			// 如果有代理商个数限制，则需要检查是否已经超出代理商的个数限制了
			int tenantNum = tenantDao.countByAppIdForUpdate(app.getId());
			if (tenantNum >= app.getMaxTenantsCount())
				throw new BusinessException(BtkjCode.APP_TENANT_NUM_MAXIMUM);
		}
		Tenant tenant = BeanGenerator.newTenant(region.getId(), app.getId(), tname);
		tenantDao.insert(tenant);
		Employee employee = BeanGenerator.newEmployee(user, tenant, null);
		employeeDao.insert(employee);
		return new TxCallback() {
			@Override
			public void finish() {
				tenantMapper.flush(tenant);
				employeeMapper.flush(employee);
			}
		};
	}

	@Transactional
	public TxCallback insertEmployee(Employee employee) {
		employeeDao.selectByTidForUpdate(employee.getTid()); 
		employeeDao.updateForJoin(employee.getTid(), employee.getLeft());
		employeeDao.insert(employee);
		return new TxCallback() {
			@Override
			public void finish() {
				employeeMapper.flush(employee);
			}
		};
	}
}
