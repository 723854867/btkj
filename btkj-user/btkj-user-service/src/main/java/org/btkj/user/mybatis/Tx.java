package org.btkj.user.mybatis;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.TxCallback;
import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.exception.BusinessException;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.user.mybatis.dao.AppDao;
import org.btkj.user.mybatis.dao.EmployeeDao;
import org.btkj.user.mybatis.dao.TenantDao;
import org.btkj.user.mybatis.dao.UserDao;
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
	public TxCallback tenantAdd(Employee employee, int appId, int uid, String tname, String license, String licenseImage, String servicePhone, int expire) {
		AppPO apo = appDao.getByKeyForUpdate(appId);
		if (0 < apo.getMaxTenantsCount()) {			// 如果有代理商个数限制，则需要检查是否已经超出代理商的个数限制了
			int tenantNum = tenantDao.countByAppIdForUpdate(appId);
			if (tenantNum >= apo.getMaxTenantsCount())
				throw new BusinessException(BtkjCode.APP_TENANT_NUM_MAXIMUM);
		}
		TenantPO tenant = EntityGenerator.newTenant(appId, tname, license, licenseImage, servicePhone, expire);
		tenantDao.insert(tenant);
		EmployeePO ep = EntityGenerator.newEmployee(uid, tenant, null);
		employeeDao.insert(ep);
		return new TxCallback() {
			@Override
			public void finish() {
				tenantMapper.flush(tenant);
				employeeMapper.flush(ep);
				employee.setTenant(tenant);
				employee.setEntity(ep);
			}
		};
	}

	@Transactional
	public TxCallback insertEmployee(EmployeePO employee) {
		employeeDao.getByTidForUpdate(employee.getTid()); 
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
