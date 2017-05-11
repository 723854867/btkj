package org.btkj.user.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.EmoloyeeInfo;
import org.btkj.pojo.model.Pager;
import org.btkj.user.Config;
import org.btkj.user.persistence.dao.EmployeeDao;
import org.rapid.data.storage.mapper.RedisProtostuffDBMapper;
import org.rapid.util.common.message.Result;

import com.mysql.cj.api.x.UpdateStatement;

/**
 * EMPLOYEE_DATA 中的 employee 数据的 left 和 right 不是最新值
 * 
 * @author ahab
 */
public class EmployeeMapper extends RedisProtostuffDBMapper<Integer, Employee, EmployeeDao> {
	
	private String LIST							= "set:employee:list:{0}";	// 用户 employee 列表：主要是用来记录有多少个代理公司
	private String LIST_CONTROLLER				= "employee：controller:{0}";
	
	@Resource
	private UserMapper userMapper;
	
	public EmployeeMapper() {
		super(BtkjTables.EMPLOYEE, "hash:db:employee");
	}
	
	@Override
	public Employee insert(Employee entity) {
		dao.selectByTidForUpdate(entity.getTid()); // 间隙锁
		dao.updateForJoin(entity.getTid(), entity.getLeft());
		return super.insert(entity);
	}
	
	/**
	 * 禁用员工
	 */
	public void UpdateState(int id){
		
	}
	
	/**
	 * 分页获取员工信息
	 * 
	 * @param pager
	 * @param tid
	 */
	@SuppressWarnings("unchecked")
	public Result<Pager<EmoloyeeInfo>> employeeList(int tid, int page, int pageSize) {
		List<EmoloyeeInfo> tipsList = new ArrayList<EmoloyeeInfo>();
		int total = dao.countByTid(tid);
		if (total == 0)
			return Result.result(Pager.EMPLTY);
		if(page < 1 )
			page = 1;
		if(pageSize < 0)
			pageSize = 10;
		int start = (page - 1) * pageSize;
		int count = pageSize;
		int id = 0;
		String name = null;
		String payType = null;
		String state = null;
		String mobile = null;
		int appId = 0;
		int uid = userMapper.getUserByMobile(appId, mobile).getUid();
		int type = 1;
		List<Employee> employees = dao.selectByTid(tid, start, count, id, name,  payType, state);
		for (Employee employee : employees) {
			User user = userMapper.getByKey(employee.getUid());
			EmoloyeeInfo tips = new EmoloyeeInfo(employee, user);
			Employee employeeParent = dao.selectByTidAndUid(employee.getTid(), employee.getParentId());
			if(null != employeeParent)
			tips.setParentName(employeeParent.getName());
			tipsList.add(tips);
		}
			return Result.result(new Pager<EmoloyeeInfo>(total, tipsList));
	}
	
	public boolean isEmployee(int tid, int uid) {
		return null != dao.selectByTidAndUid(tid, uid);
	} 
	
	/**
	 * 获取用户的所拥有的代理公司列表
	 * 
	 * @return
	 */
	public List<Employee> ownedTenants(User user) {
		List<Employee> employees = null;
		List<byte[]> list = redis.protostuffCacheListLoadWithData(Config.CACHE_CONTROLLER, _listKey(user.getUid()), redisKey, _listController(user.getUid()));
		if (null != list) {
			employees = new ArrayList<Employee>();
			for (byte[] buffer : list) 
				employees.add(deserial(buffer));
		} else {
			employees = dao.selectByUid(user.getUid());
			redis.protostuffCacheListFlush(Config.CACHE_CONTROLLER, redisKey, _listKey(user.getUid()), _listController(user.getUid()), employees);
		}
		return employees;
	}
	
	@Override
	public void flush(Employee entity) {
		redis.protostuffCacheFlush(redisKey, entity, _listKey(entity.getUid()));
	}
	
	public String _listKey(int uid) { 
		return MessageFormat.format(LIST, String.valueOf(uid));
	}
	
	private String _listController(int uid) {
		return MessageFormat.format(LIST_CONTROLLER, String.valueOf(uid));
	}
}
