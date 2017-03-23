package org.btkj.user.redis.hook;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.config.GlobalConfigContainer;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.info.tips.TenantTips;
import org.btkj.user.persistence.dao.EmployeeDao;
import org.btkj.user.redis.RedisKeyGenerator;
import org.rapid.data.storage.redis.Redis;
import org.springframework.stereotype.Component;

@Component
public class EmployeeHook {

	@Resource
	private Redis redis;
	@Resource
	private EmployeeDao employeeDao;
	@Resource
	private GlobalConfigContainer gcc;
	
	/**
	 * 获取用户的所拥有的代理公司列表:只保途 app
	 * 
	 * @return
	 */
	public List<TenantTips> tenantTipsList(int uid, int mainTid) {
		List<TenantTips> l = new ArrayList<TenantTips>();
		String key = RedisKeyGenerator.btkjUserEmployees(uid);
		List<String> tids = redis.hkeysAndRefresh(key, gcc.getGlobalConfig().getCacheExpire());
		if (null != tids && !tids.isEmpty()) {
			for (String str : tids) {
				int tid = Integer.valueOf(str);
				if (tid != mainTid)
					l.add(new TenantTips(tid));
			}
			return l;
		} else {
			List<Employee> employees = employeeDao.selectByUidAndAppId(uid, BtkjConsts.APP_ID_BAOTU);
			if (null == employees || employees.isEmpty())
				return l;
			String[] params = new String[employees.size() * 2 + 2];
			int index = 0;
			params[index++] = RedisKeyGenerator.btkjUserEmployees(uid);
			params[index++] = String.valueOf(gcc.getGlobalConfig().getCacheExpire());
			for (Employee employee : employees) {
				int tid = employee.getTid();
				if (tid != mainTid)
					l.add(new TenantTips(tid));
				params[index++] = String.valueOf(tid);
				params[index++] = String.valueOf(employee.getId()); 
			}
			redis.hmsetAndRefresh(params);
		}
		return l;
	}
	
	/**
	 * 判断用户是否是代理公司的雇员：保途 app
	 * 
	 * @param uid
	 * @param tid
	 * @return
	 */
	public boolean employeeOf(int uid, int tid) {
		// 首先直接在 redis 中找
		String id = redis.hgetAndRefresh(
				RedisKeyGenerator.btkjUserEmployees(uid), 
				String.valueOf(tid), 
				gcc.getGlobalConfig().getCacheExpire());
		if (null != id)
			return true;
		
		// 如果没找到去数据库找
		Employee employee = employeeDao.selectByUidAndTid(uid, tid);
		return null != employee;
	}
	
	/**
	 * 判断用户是否是代理公司的雇员：独立 app
	 * 
	 * @param appId
	 * @param mobile
	 * @return
	 */
	public boolean employeeOf(int appId, String mobile) { 
		return null != employeeDao.selectByAppIdAndMobile(appId, mobile);
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
}
