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
	
	Map<Integer, ModularDocument> modulars(Integer tarId, ModularType type);
	
	/**
	 * 获取指定类型的模块
	 * 
	 * @param type
	 * @return
	 */
	Map<Integer, ModularDocument> modulars(ModularType type);
	
	/**
	 * 获取权限模块
	 * 
	 * @param tarId: 目标ID
	 * @param tarType：目标类型
	 * @param modularType：模块类型
	 * @return
	 */
	Map<Integer, ModularDocument> modulars(int tarId, TarType tarType, ModularType modularType);
	
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
	Map<Integer, Api> apis(int modularId);
	
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
	void authorizeApp(int appId, Set<Integer> modulars);
	
	/**
	 * 给保途管理员授权
	 * 
	 * @param adminId
	 * @param modulars
	 * @return
	 */
	void authorizeAdmin(int adminId, Set<Integer> modulars);
	
	/**
	 * 授权
	 * 
	 * @param srcId
	 * @param srcType
	 * @param tarId
	 * @param tarType
	 * @param modulars
	 */
	void authorize(int srcId, TarType srcType, int tarId, TarType tarType, ModularType modularType, Set<Integer> modulars);
}
