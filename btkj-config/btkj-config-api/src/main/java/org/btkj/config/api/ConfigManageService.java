package org.btkj.config.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.btkj.config.pojo.TarType;
import org.btkj.config.pojo.entity.Api;
import org.btkj.config.pojo.info.AreaInfo;
import org.btkj.config.pojo.info.ModularDocument;
import org.btkj.config.pojo.param.ApiEditParam;
import org.btkj.config.pojo.param.AreaEditParam;
import org.btkj.config.pojo.param.InsurerEditParam;
import org.btkj.config.pojo.param.ModularEditParam;
import org.btkj.pojo.entity.Insurer;
import org.btkj.pojo.enums.ModularType;
import org.rapid.util.common.message.Result;

public interface ConfigManageService {

	/**
	 * 险企列表
	 * 
	 * @return
	 */
	List<Insurer> insurers();
	
	/**
	 * 险企编辑
	 * 
	 * @param param
	 * @return
	 */
	Result<Void> insurerEdit(InsurerEditParam param);
	
	/**
	 * 获取所有地区配置信息
	 * 
	 * @return
	 */
	Map<Integer, AreaInfo> areas();
	
	/**
	 * 地区编辑
	 * 
	 * @param param
	 * @return
	 */
	Result<Void> areaEdit(AreaEditParam param);
	
	/**
	 * api 列表
	 * 
	 * @param param
	 * @return
	 */
	Map<Integer, ModularDocument> modulars(TarType type, int tarId);
	
	/**
	 * 权限模块结构
	 * 
	 * @param type
	 * @return
	 */
	Map<Integer, ModularDocument> modulars(ModularType type);
	
	/**
	 * api 编辑
	 * 
	 * @param param
	 * @return
	 */
	Result<?> modularEdit(ModularEditParam param);
	
	/**
	 * 接口列表
	 * 
	 * @param modularId
	 * @return
	 */
	Map<String, Api> apis(int modularId);
	
	/**
	 * 接口编辑
	 * 
	 * @param param
	 * @return
	 */
	Result<Void> apiEdit(ApiEditParam param);
	
	/**
	 * 给平台授权
	 * 
	 * @param appId
	 * @param modulars
	 * @return
	 */
	Result<Void> authorizeApp(int appId, Set<Integer> modulars);
	
	/**
	 * 给保途管理员授权
	 * 
	 * @param adminId
	 * @param modulars
	 * @return
	 */
	Result<Void> authorizeAdmin(int adminId, Set<Integer> modulars);
	
	/**
	 * 给平台用户授权
	 * 
	 * @param uid
	 * @param modularse
	 * @return
	 */
	Result<Void> authorizeUser(int appId, int uid, Set<Integer> modulars);
	
	/**
	 * 给商户授权
	 * 
	 * @param tid
	 * @param modulars
	 * @return
	 */
	Result<Void> authorizeTenant(int appId, int tid, Set<Integer> modulars);
	
	/**
	 * 给雇员授权
	 * 
	 * @param uid
	 * @param modulars
	 * @return
	 */
	Result<Void> authorizeEmployee(int tid, int employeeId, Set<Integer> modulars);
}
