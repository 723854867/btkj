package org.btkj.user.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.tips.EmployeeTips;
import org.btkj.pojo.model.Pager;
import org.btkj.user.UserLuaCmd;
import org.btkj.user.persistence.dao.EmployeeDao;
import org.rapid.data.storage.mapper.ProtostuffDBMapper;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.serializer.SerializeUtil;

/**
 * EMPLOYEE_DATA 中的 employee 数据的 left 和 right 不是最新值
 * 
 * @author ahab
 */
public class EmployeeMapper extends ProtostuffDBMapper<Integer, Employee, EmployeeDao> {
	
	private static final String EMPLOYEE_DATA				= "hash:employee:data";
	private static final String USER_EMPLOYEES				= "hash:user:{0}:employees"; 		// 用户的雇员列表：tid - employeeId
	private static final String EMPLOYEE_LIST_CONTROLLER	= "employee:list:controller";			// 雇员列表缓存控制键
	
	@Resource
	private UserMapper userMapper;
	
	public EmployeeMapper() {
		super(BtkjTables.EMPLOYEE, EMPLOYEE_DATA);
	}
	
	@Override
	public Employee insert(Employee entity) {
		dao.selectByTidForUpdate(entity.getTid()); // 间隙锁
		dao.updateForJoin(entity.getTid(), entity.getLeft());
		return super.insert(entity);
	}
	
	/**
	 * 分页获取员工信息
	 * 
	 * @param pager
	 * @param tid
	 */
	@SuppressWarnings("unchecked")
	public Result<Pager<EmployeeTips>> employeeList(int tid, int page, int pageSize) {
		List<EmployeeTips> tipsList = new ArrayList<EmployeeTips>();
		int total = dao.countByTid(tid);
		if (total == 0)
			return Result.result(Pager.EMPLTY);
		if(page < 1 )
			page = 1;
		if(pageSize < 0)
			pageSize = 10;
		int start = (page - 1) * pageSize;
		int count = pageSize;
		List<Employee> employees = dao.selectByTid(tid, start, count);
		for (Employee employee : employees) {
			Set<Integer> set = new HashSet<Integer>();
			set.add(employee.getUid());
			set.add(employee.getParentId());
			List<Integer> uids = new ArrayList<Integer>(set);
			List<User> users = userMapper.getUsers(uids);
			EmployeeTips tips = new EmployeeTips(employee, users.get(0));
			if (null != users.get(1))
			tips.setParentName(users.get(1).getName());
			tipsList.add(tips);
		}
			return Result.result(new Pager<EmployeeTips>(total, tipsList));
	}
	
	public Employee getByTidAndUid(int tid, int uid) {
		byte[] data = redis.invokeLua(UserLuaCmd.EMPLOYEE_LOAD_BY_TID_UID, 
				SerializeUtil.RedisUtil.encode(
						userEmployeesKey(uid), 
						employeeDataKey(), 
						tid));
		if (null != data)
			return deserial(data);
		Employee employee = dao.selectByTidAndUid(tid, uid);
		if (null != employee)
			flush(employee);
		return employee;
	}
	
	/**
	 * 获取用户的所拥有的代理公司列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Set<String> ownedTenants(User user) {
		Set<String> set = redis.invokeLua(UserLuaCmd.EMPLOYEE_LIST, 
						EMPLOYEE_LIST_CONTROLLER, 
						userEmployeesKey(user.getUid()),
						String.valueOf(user.getUid()));
		if (null == set) {
			List<Employee> employees = dao.selectByUid(user.getUid());
			if (null == employees || employees.isEmpty())
				return Collections.EMPTY_SET;
			set = new HashSet<String>(employees.size());
			byte[][] data = new byte[employees.size() * 3 + 2][];
			int index = 0;
			data[index++] = SerializeUtil.RedisUtil.encode(EMPLOYEE_DATA);
			data[index++] = SerializeUtil.RedisUtil.encode(userEmployeesKey(user.getUid()));
			for (Employee employee : employees) {
				data[index++] = SerializeUtil.RedisUtil.encode(employee.getId());
				data[index++] = SerializeUtil.RedisUtil.encode(employee.getTid());
				data[index++] = serial(employee);
				set.add(String.valueOf(employee.getTid()));
			}
			redis.invokeLua(UserLuaCmd.EMPLOYEE_FLUSH, data);
		} 
		return set;
	}
	
	/**
	 * 获取用户拥有的代理公司数量
	 * 
	 * @param uid
	 * @return
	 */
	public int tenantNum(int uid) { 
//		return cacheController.tenantList(uid).size();
		return 0;
	}
	
	@Override
	protected void flush(Employee entity) {
		redis.invokeLua(UserLuaCmd.EMPLOYEE_FLUSH, 
				SerializeUtil.RedisUtil.encode(
						redisKey, 
						userEmployeesKey(entity.getUid()), 
						entity.getId(), 
						entity.getTid(), 
						serial(entity)));
	}
	
	public static final String employeeDataKey() {
		return EMPLOYEE_DATA;
	}
	
	public static final String userEmployeesKey(int uid) { 
		return MessageFormat.format(USER_EMPLOYEES, String.valueOf(uid));
	}
}
