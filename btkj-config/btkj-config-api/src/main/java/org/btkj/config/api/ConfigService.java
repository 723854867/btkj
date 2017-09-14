package org.btkj.config.api;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import org.btkj.pojo.entity.config.Area;
import org.btkj.pojo.entity.config.Insurer;
import org.btkj.pojo.entity.config.Region;

public interface ConfigService {
	
	/**
	 * 通过 region code 获取 Region
	 * 
	 * @param region
	 * @return
	 */
	Region region(int region);
	
	void updateInsurer(Insurer insurer);

	/**
	 * 一次性获取多条行政区划数据
	 * 
	 * @param regionIds
	 * @return
	 */
	Map<Integer, Region> regions(Collection<Integer> regionIds);
	
	/**
	 * 判断 region2 是否是 region 1的子行政区划
	 * 
	 * @param region1
	 * @param region2
	 * @return
	 */
	boolean isSubRegion(int region1, int region2);
	
	/**
	 * 根据行政区划代码获取所属省份的行政区划代码：
	 * 如果行政区划代码是全国返回 null
	 * 
	 * @param region
	 * @return
	 */
	Region subordinateProvince(int region);
	
	/**
	 * 根据 region 获取该 region 的全地址路径
	 * 
	 * @param region
	 * @return
	 */
	LinkedList<Region> regionRoute(int region);
	
	/**
	 * 根据保险公司ID获取保险公司
	 * 
	 * @param insurerId
	 * @return
	 */
	Insurer insurer(int insurerId);
	
	/**
	 * 获取险企列表
	 * 
	 * @param list
	 * @return
	 */
	Map<Integer, Insurer> insurers(Collection<Integer> tids);
	
	/**
	 * 获取指定的地区
	 * 
	 * @param areaId
	 * @return
	 */
	Area area(int areaId);
	
	/**
	 * 超管权限检测
	 * 
	 * @param adminId
	 * @return
	 */
	boolean checkAdminPrivilege(String pkg, int adminId);
	
	/**
	 * 平台用户权限检测
	 * 
	 * @param appId
	 * @param uid
	 * @return
	 */
	boolean checkUserPrivilege(String pkg, int appId, int uid);
	
	/**
	 * 雇员权限检测
	 * 
	 * @param appId
	 * @param tid
	 * @param employeeId
	 * @return
	 */
	boolean checkEmployeePrivilege(String pkg, int appId, int tid, int employeeId);
}
