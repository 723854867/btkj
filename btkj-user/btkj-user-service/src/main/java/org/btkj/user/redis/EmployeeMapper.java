package org.btkj.user.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.user.mybatis.dao.EmployeeDao;
import org.btkj.user.pojo.info.EmployeePagingInfo;
import org.btkj.user.pojo.param.EmployeesParam;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtil;

/**
 * EMPLOYEE_DATA 中的 employee 数据的 left 和 right 不是最新值
 * 
 * @author ahab
 */
public class EmployeeMapper extends RedisDBAdapter<Integer, EmployeePO, EmployeeDao> {
	
	private final String USER_SET				= "set:employee：user:{0}";
	private final String CONTROLLER				= "employee：controller:{0}:";			// 基于用户的缓存控制键
	
	@Resource
	private UserMapper userMapper;
	
	public EmployeeMapper() {
		super(new ByteProtostuffSerializer<EmployeePO>(), "hash:db:employee");
	}
	
	/**
	 * 分页获取员工信息
	 * 
	 * @param pager
	 * @param tid
	 */
	public Pager<EmployeePagingInfo> employees(EmployeesParam param) {
		int total = dao.count(param);
		if (0 == total)
			return Pager.EMPLTY;
		param.calculate(total);
		return new Pager<EmployeePagingInfo>(total, dao.employees(param));
	}
	
	public boolean isEmployee(int tid, int uid) {
		return null != dao.getByTidAndUid(tid, uid);
	} 
	
	/**
	 * 获取用户的所拥有的代理公司列表
	 * 
	 * @return
	 */
	public List<EmployeePO> ownedTenants(int uid) {
		Map<Integer, EmployeePO> map =_checkLoad(uid);
		if (null != map)
			return new ArrayList<EmployeePO>(map.values());
		List<byte[]> list = redis.hmsget(redisKey, _userSetKey(uid));
		if (null == list)
			return Collections.EMPTY_LIST;
		List<EmployeePO> l = new ArrayList<EmployeePO>();
		for (byte[] buffer : list)
			l.add(serializer.antiConvet(buffer));
		return l;
	}
	
	private Map<Integer, EmployeePO> _checkLoad(int uid) {
		if (!checkLoad(_controllerField(uid)))
			return null;
		Map<Integer, EmployeePO> map = dao.getByUid(uid);
		if (!CollectionUtil.isEmpty(map))
			flush(map);
		return map;
	}
	
	@Override
	public void flush(EmployeePO entity) {
		redis.hmsset(redisKey, entity, serializer, _userSetKey(entity.getUid()));
	}
	
	@Override
	public void flush(Map<Integer, EmployeePO> entities) {
		Map<Integer, List<EmployeePO>> map = new HashMap<Integer, List<EmployeePO>>();
		for (EmployeePO temp : entities.values()) {
			List<EmployeePO> list = map.get(temp.getUid());
			if (null == list) {
				list = new ArrayList<EmployeePO>();
				map.put(temp.getUid(), list);
			}
			list.add(temp);
		}
		for (Entry<Integer, List<EmployeePO>> entry : map.entrySet())
			redis.hmsset(redisKey, entry.getValue(), serializer, _userSetKey(entry.getKey()));
	}
	
	public String _userSetKey(int uid) { 
		return MessageFormat.format(USER_SET, String.valueOf(uid));
	}
	
	private String _controllerField(int uid) {
		return MessageFormat.format(CONTROLLER, String.valueOf(uid));
	}
}
