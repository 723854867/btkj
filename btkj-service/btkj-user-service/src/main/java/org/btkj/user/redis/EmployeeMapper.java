package org.btkj.user.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.tips.TenantTips;
import org.btkj.user.UserLuaCmd;
import org.btkj.user.persistence.dao.EmployeeDao;
import org.rapid.data.storage.mapper.ProtostuffDBMapper;
import org.rapid.util.common.serializer.SerializeUtil;

/**
 * EMPLOYEE_DATA 中的 employee 数据的 left 和 right 不是最新值
 * 
 * @author ahab
 */
public class EmployeeMapper extends ProtostuffDBMapper<Integer, Employee, EmployeeDao> {
	
	private static final String EMPLOYEE_DATA				= "hash:employee:data";
	private static final String EMPLOYEE_USER				= "hash:employee:user:{0}"; 			// 用户的雇员列表：tid - employeeId

	public EmployeeMapper() {
		super(BtkjTables.EMPLOYEE, EMPLOYEE_DATA);
	}
	
	@Override
	public Employee insert(Employee entity) {
		dao.selectByTidForUpdate(entity.getTid()); // 间隙锁
		dao.updateForJoin(entity.getTid(), entity.getLeft());
		return super.insert(entity);
	}
	
	public Employee getByTidAndUid(int tid, int uid) {
		byte[] data = redis.invokeLua(UserLuaCmd.EMPLOYEE_LOAD_BY_TID_UID, 
				SerializeUtil.RedisUtil.encode(
						userEmployeeKey(uid), 
						employeeDataKey(), 
						tid));
		if (null != data)
			return deserial(data);
		Employee employee = dao.selectByTidAndUid(tid, uid);
		if (null != employee)
			flush(employee);
		return employee;
	}
	
	private CacheController cacheController;
	
	/**
	 * 获取用户的所拥有的代理公司列表
	 * 
	 * @return
	 */
	public List<TenantTips> tenantTipsList(User user, int mainTid) {
		Set<Integer> tids = cacheController.tenantList(user.getUid());
		if (null == tids || tids.isEmpty())
			return null;
		List<TenantTips> list = new ArrayList<TenantTips>();
		for (int tid : tids) {
			if (tid == mainTid)
				continue;
			list.add(new TenantTips(tid));
		}
		return list;
	}
	
	/**
	 * 正在审核的代理公司列表：只限保途 app
	 * 
	 * @return
	 */
	public List<TenantTips> auditTenantTipsList(int uid) { 
		List<TenantTips> list = new ArrayList<TenantTips>();
//		String key = RedisKeyGenerator.userApplyList(uid);
		Set<String> set = redis.hkeys(null);
		for (String str : set) 
			list.add(new TenantTips(Integer.valueOf(str)));
		return list;
	}
	
	/**
	 * 获取用户拥有的代理公司数量
	 * 
	 * @param uid
	 * @return
	 */
	public int tenantNum(int uid) { 
		return cacheController.tenantList(uid).size();
	}
	
	@Override
	protected void flush(Employee entity) {
		redis.invokeLua(UserLuaCmd.EMPLOYEE_FLUSH, 
				SerializeUtil.RedisUtil.encode(
						redisKey, 
						userEmployeeKey(entity.getUid()), 
						entity.getId(), 
						entity.getTid(), 
						serial(entity)));
	}
	
	public static final String employeeDataKey() {
		return EMPLOYEE_DATA;
	}
	
	public static final String userEmployeeKey(int uid) { 
		return MessageFormat.format(EMPLOYEE_USER, String.valueOf(uid));
	}
}
