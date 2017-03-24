package org.btkj.user.redis.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.info.tips.TenantTips;
import org.btkj.user.persistence.dao.EmployeeDao;
import org.btkj.user.redis.RedisKeyGenerator;
import org.btkj.user.redis.UserCacheController;
import org.btkj.user.redis.UserLuaCmd;
import org.rapid.data.storage.mapper.Mapper;
import org.rapid.util.common.serializer.SerializeUtil;

public class EmployeeMapper extends Mapper<Integer, Employee, EmployeeDao> {
	
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
		return null;
	}

	@Override
	public Employee getByKey(Integer key) {
		return null;
	}

	@Override
	public void update(Employee entity) {
		
	}
	
	public Employee get(int uid, int tid) {
		byte[] data = redis.invokeLua(UserLuaCmd.GET_USER_BY_MOBILE,
				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.userEmployeeKey(uid)),
				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.employeeDataKey()),
				SerializeUtil.RedisUtil.encode(tid));
		if (null != data)
			return SerializeUtil.ProtostuffUtil.deserial(data, Employee.class);
		Employee employee = dao.selectByUidAndTid(uid, tid);
		if (null == employee)
			return null;
		_refresh(employee);
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
	
	private void _refresh(Employee employee) {
		redis.invokeLua(UserLuaCmd.REFRESH_EMPLOYEE,
				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.employeeDataKey()),
				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.userEmployeeKey(employee.getUid())),
				SerializeUtil.RedisUtil.encode(employee.getId()),
				SerializeUtil.ProtostuffUtil.serial(employee),
				SerializeUtil.RedisUtil.encode(employee.getTid()));
	}
	
	public void setUserCacheController(UserCacheController userCacheController) {
		this.userCacheController = userCacheController;
	}
}
