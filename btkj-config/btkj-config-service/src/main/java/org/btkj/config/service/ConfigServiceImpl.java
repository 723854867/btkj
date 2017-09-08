package org.btkj.config.service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.config.redis.ApiMapper;
import org.btkj.config.redis.AreaMapper;
import org.btkj.config.redis.InsurerMapper;
import org.btkj.config.redis.ModularMapper;
import org.btkj.config.redis.PrivilegeMapper;
import org.btkj.config.redis.RegionMapper;
import org.btkj.pojo.entity.config.Area;
import org.btkj.pojo.entity.config.Insurer;
import org.btkj.pojo.entity.config.Region;
import org.rapid.util.common.Consts;
import org.springframework.stereotype.Service;

@Service("configService")
public class ConfigServiceImpl implements ConfigService {
	
	@Resource
	private ApiMapper apiMapper;
	@Resource
	private AreaMapper areaMapper;
	@Resource
	private RegionMapper regionMapper;
	@Resource
	private ModularMapper modularMapper;
	@Resource
	private InsurerMapper insurerMapper;
	@Resource
	private PrivilegeMapper privilegeMapper;
	
	@Override
	public Region region(int region) {
		return regionMapper.getByKey(region);
	}
	
	@Override
	public Map<Integer, Region> regions(Collection<Integer> regionIds) {
		return regionMapper.getByKeys(regionIds);
	}
	
	@Override
	public boolean isSubRegion(int region1, int region2) {
		Region r1 = regionMapper.getByKey(region1);
		Region r2 = regionMapper.getByKey(region2);
		if (null == r1 || null == r2 || r2.getLevel() < r1.getLevel())
			return false;
		if (region1 == region2 || r2.getParentId() == region1)
			return true;
		return isSubRegion(region1, r2.getParentId());
	}
	
	@Override
	public Region subordinateProvince(int region) {
		Region r = regionMapper.getByKey(region);
		if (null == r)
			return null;
		if (r.getLevel() == Consts.RegionLevel.COUNTRY.mark())
			return null;
		if (r.getLevel() == Consts.RegionLevel.PROVINCE.mark())
			return r;
		return subordinateProvince(r.getParentId());
	}
	
	@Override
	public LinkedList<Region> regionRoute(int region) {
		LinkedList<Region> list = new LinkedList<Region>();
		_regionRoute(list, region);
		return list;
	}
	
	private void _regionRoute(LinkedList<Region> regions, int region) {
		Region r = regionMapper.getByKey(region);
		if (null == r)
			return;
		regions.addFirst(r);
		if (0 == r.getParentId())
			return;
		_regionRoute(regions, r.getParentId());
	}
	
	@Override
	public Insurer insurer(int insurerId) {
		return insurerMapper.getByKey(insurerId);
	}
	
	@Override
	public Map<Integer, Insurer> insurers(Collection<Integer> tids) {
		return insurerMapper.getByKeys(tids);
	}
	
	@Override
	public Area area(int areaId) {
		return areaMapper.getByKey(areaId);
	}
	
	@Override
	public boolean checkAdminPrivilege(String pkg, int adminId) {
//		Api api = apiMapper.getByKey(pkg);
//		if (null == api)
//			return true;
//		Map<String, Privilege> privileges = privilegeMapper.privileges(TarType.ADMIN, adminId);
//		for (Privilege privilege : privileges.values()) {
//			if (privilege.getModularId() == api.getModularId())
//				return true;
//		}
		return false;
	}
	
	@Override
	public boolean checkUserPrivilege(String pkg, int appId, int uid) {
//		Api api = apiMapper.getByKey(pkg);
//		if (null == api)
//			return true;
//		boolean find = false;
//		Map<String, Privilege> privileges = privilegeMapper.privileges(TarType.APP, appId);
//		for (Privilege privilege : privileges.values()) {
//			if (privilege.getModularId() == api.getModularId()) {
//				find = true;
//				break;
//			}
//		}
//		if (!find)
//			return false;
//		privileges = privilegeMapper.privileges(TarType.USER, uid);
//		for (Privilege privilege : privileges.values()) {
//			if (privilege.getModularId() == api.getModularId()) 
//				return true;
//		}
		return false;
	}
	
	@Override
	public boolean checkEmployeePrivilege(String pkg, int appId, int tid, int employeeId) {
//		Api api = apiMapper.getByKey(pkg);
//		if (null == api)
//			return true;
//		boolean find = false;
//		Map<String, Privilege> privileges = privilegeMapper.privileges(TarType.APP, appId);
//		for (Privilege privilege : privileges.values()) {
//			if (privilege.getModularId() == api.getModularId()) {
//				find = true;
//				break;
//			}
//		}
//		if (!find)
//			return false;
//		find = false;
//		privileges = privilegeMapper.privileges(TarType.TENANT, tid);
//		for (Privilege privilege : privileges.values()) {
//			if (privilege.getModularId() == api.getModularId()) {
//				find = true;
//				break;
//			}
//		}
//		if (!find)
//			return false;
//		privileges = privilegeMapper.privileges(TarType.EMPLOYEE, employeeId);
//		for (Privilege privilege : privileges.values()) {
//			if (privilege.getModularId() == api.getModularId()) 
//				return true;
//		}
		return false;
	}
}