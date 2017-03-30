package org.btkj.user.redis.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.tips.TenantTips;
import org.btkj.user.BeanGenerator;
import org.btkj.user.persistence.Tx;
import org.btkj.user.persistence.dao.EmployeeDao;
import org.btkj.user.redis.RedisKeyGenerator;
import org.btkj.user.redis.UserCacheController;
import org.btkj.user.redis.UserLuaCmd;
import org.rapid.data.storage.mapper.Mapper;
import org.rapid.util.common.Assert;
import org.rapid.util.common.serializer.SerializeUtil;

public class EmployeeMapper extends Mapper<Integer, Employee, EmployeeDao> {

	private Tx tx;
	private UserCacheController userCacheController;
	
	public EmployeeMapper() {
		super(BtkjTables.EMPLOYEE);
	}

	@Override
	public void init() {
		// do nothing
	}

	@Override
	public Employee insert(Employee entity) {
		dao.insert(entity);
		userCacheController.refreshEmployee(entity);
		return entity;
	}

	@Override
	public Employee getByKey(Integer key) {
		return null;
	}

	@Override
	public void update(Employee entity) {
		
	}
	
	public Employee get(int uid, int tid) {
		byte[] data = redis.invokeLua(UserLuaCmd.EMPLOYEE_GET_BY_UID_AND_TID,
				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.userEmployeeKey(uid)),
				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.employeeDataKey()),
				SerializeUtil.RedisUtil.encode(tid));
		if (null != data)
			return SerializeUtil.ProtostuffUtil.deserial(data, Employee.class);
		Employee employee = dao.selectByTidAndUid(tid, uid);
		if (null == employee)
			return null;
		userCacheController.refreshEmployee(employee);
		return employee;
	}
	
	/**
	 * 获取用户的所拥有的代理公司列表:只保途 app
	 * 
	 * @return
	 */
	public List<TenantTips> tenantTipsList(int appId, int uid, int mainTid) {
		Set<Integer> tids = userCacheController.tenants(uid, appId);
		if (null == tids || tids.isEmpty())
			return null;
		List<TenantTips> list = new ArrayList<TenantTips>();
		for (int tid : tids)
			list.add(new TenantTips(tid));
		return list;
	}
	
	/**
	 * 正在审核的代理公司列表：只限保途 app
	 * 
	 * @return
	 */
	public List<TenantTips> auditTenantTipsList(int uid) { 
		List<TenantTips> list = new ArrayList<TenantTips>();
		String key = RedisKeyGenerator.btkjApplyKey(uid);
		Set<String> set = redis.hkeys(key);
		for (String str : set) 
			list.add(new TenantTips(Integer.valueOf(str)));
		return list;
	}
	
	/**
	 * 加入代理商：保途 app
	 * 
	 * @param tenant
	 * @param user
	 * @param chief
	 */
	public void join(Tenant tenant, User user, User chief) {
		Employee employee = get(chief.getUid(), tenant.getTid());
		Assert.notNull(employee);
		employee = BeanGenerator.newEmployee(user, tenant, employee);
		tx.tenantJoin(employee);
	}
	
	/**
	 * 加入代理商：独立 app
	 * 
	 * @param tenant
	 * @param name
	 * @param identity
	 * @param chief
	 */
	public void join(Tenant tenant, String name, String identity, int chief) {
		
	}
	
	public void setTx(Tx tx) {
		this.tx = tx;
	}
	
	public void setUserCacheController(UserCacheController userCacheController) {
		this.userCacheController = userCacheController;
	}
}
