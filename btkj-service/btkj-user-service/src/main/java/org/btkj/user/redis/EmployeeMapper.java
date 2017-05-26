package org.btkj.user.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.SpecialBonus;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.EmployeeInfo;
import org.btkj.pojo.info.EmployeeListInfo;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.EmployeeSearcher;
import org.btkj.user.BeanGenerator;
import org.btkj.user.persistence.dao.EmployeeDao;
import org.btkj.user.persistence.dao.SpecialBonusDao;
import org.rapid.data.storage.mapper.RedisProtostuffDBMapper;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtils;

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
	@Resource
	private SpecialBonusMapper specialBonusMapper;
	@Resource
	private SpecialBonusDao specialBonusDao;
	
	public EmployeeMapper() {
		super(BtkjTables.EMPLOYEE, "hash:db:employee");
	}
	
	@Override
	public Employee insert(Employee entity) {
		throw new UnsupportedOperationException("EmployeeMapper unsupported insert immediately!");
	}
	
	/**
	 * 商家后台管理保存修改雇员部分属性
	 */
	public void employeeEdit(EmployeeInfo employeeInfo) {
		Employee employee = BeanGenerator.newEmployeeSave(employeeInfo);
		dao.update(employee);
		SpecialBonus specialbonus = BeanGenerator.newSpecialBonus(employeeInfo);
		if(null == specialBonusMapper.getByeid(employee.getId())){
			specialbonus.setCreated(DateUtils.currentTime());
			specialBonusMapper.insert(specialbonus);
	    }else 
			specialBonusMapper.update(specialbonus);
	}
	
	/**
	 * 分页获取员工信息
	 * 
	 * @param pager
	 * @param tid
	 */
	@SuppressWarnings("unchecked")
	public Result<Pager<EmployeeListInfo>> employeeList(EmployeeSearcher searcher) {
		int total = dao.searchCount(searcher);
		if (0 == total)
			return Result.result(Pager.EMPLTY);
		searcher.calculate(total);
		List<EmployeeListInfo> employees = dao.search(searcher);
		Pager<EmployeeListInfo> pager = new Pager<EmployeeListInfo>(searcher.getTotal(), employees);
		return Result.result(pager);
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
		List<byte[]> list = redis.protostuffCacheListLoadWithData(BtkjConsts.CACHE_CONTROLLER, _listKey(user.getUid()), redisKey, _listController(user.getUid()));
		if (null != list) {
			employees = new ArrayList<Employee>();
			for (byte[] buffer : list) 
				employees.add(deserial(buffer));
		} else {
			employees = dao.selectByUid(user.getUid());
			redis.protostuffCacheListFlush(BtkjConsts.CACHE_CONTROLLER, redisKey, _listKey(user.getUid()), _listController(user.getUid()), employees);
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
