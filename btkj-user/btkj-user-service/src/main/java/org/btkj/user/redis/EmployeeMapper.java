package org.btkj.user.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.model.Pager;
import org.btkj.user.mybatis.dao.EmployeeDao;
import org.btkj.user.pojo.info.EmployeePagingInfo;
import org.btkj.user.pojo.submit.EmployeeSearcher;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

/**
 * EMPLOYEE_DATA 中的 employee 数据的 left 和 right 不是最新值
 * 
 * @author ahab
 */
public class EmployeeMapper extends RedisDBAdapter<Integer, Employee, EmployeeDao> {
	
	private String LIST							= "set:employee:list:{0}";	// 用户 employee 列表：主要是用来记录有多少个代理公司
	private String LIST_CONTROLLER				= "employee：controller:{0}";
	
	@Resource
	private UserMapper userMapper;
	
	public EmployeeMapper() {
		super(new ByteProtostuffSerializer<Employee>(), "hash:db:employee");
	}
	
	@Override
	public void insert(Employee entity) {
		throw new UnsupportedOperationException("EmployeeMapper unsupported insert immediately!");
	}
	
	/**
	 * 分页获取员工信息
	 * 
	 * @param pager
	 * @param tid
	 */
	public Pager<EmployeePagingInfo> paging(EmployeeSearcher searcher) {
		int total = dao.count(searcher);
		if (0 == total)
			return Pager.EMPLTY;
		searcher.calculate(total);
		return new Pager<EmployeePagingInfo>(searcher.getTotal(), dao.paging(searcher));
	}
	
	public boolean isEmployee(int tid, int uid) {
		return null != dao.getByTidAndUid(tid, uid);
	} 
	
	/**
	 * 获取用户的所拥有的代理公司列表
	 * 
	 * @return
	 */
	public List<Employee> ownedTenants(User user) {
		List<Employee> employees = null;
		List<byte[]> list = redis.hsgetIfMarked(BtkjConsts.CACHE_CONTROLLER_KEY, _listKey(user.getUid()), redisKey, _listController(user.getUid()));
		if (null != list) {
			employees = new ArrayList<Employee>();
			for (byte[] buffer : list) 
				employees.add(serializer.antiConvet(buffer));
		} else {
			employees = dao.getByUid(user.getUid());
			redis.hssetMark(BtkjConsts.CACHE_CONTROLLER_KEY, redisKey, _listKey(user.getUid()), _listController(user.getUid()), employees, serializer);
		}
		return employees;
	}
	
	/**
	 * 获取指定雇员的团队
	 * 
	 * @param employee
	 * @return
	 */
	public List<Employee> team(Employee employee, int depth) {
		return dao.team(employee.getId(), employee.getLeft(), employee.getRight(), employee.getLevel() + depth - 1);
	}
	
	@Override
	public void flush(Employee entity) {
		redis.hsset(redisKey, entity.key(), serializer.convert(entity), _listKey(entity.getUid()));
	}
	
	public String _listKey(int uid) { 
		return MessageFormat.format(LIST, String.valueOf(uid));
	}
	
	private String _listController(int uid) {
		return MessageFormat.format(LIST_CONTROLLER, String.valueOf(uid));
	}
}
