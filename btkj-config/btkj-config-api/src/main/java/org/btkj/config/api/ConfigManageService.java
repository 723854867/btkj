package org.btkj.config.api;

import java.util.Map;
import java.util.Set;

import org.btkj.pojo.entity.config.Api;
import org.btkj.pojo.entity.config.Insurer;
import org.btkj.pojo.enums.ModularType;
import org.btkj.pojo.info.config.AreaInfo;
import org.btkj.pojo.info.config.ModularDocument;
import org.btkj.pojo.param.config.ApiEditParam;
import org.btkj.pojo.param.config.AreaEditParam;
import org.btkj.pojo.param.config.InsurerEditParam;
import org.btkj.pojo.param.config.ModularEditParam;
import org.rapid.util.common.message.Result;

public interface ConfigManageService {

	/**
	 * 险企列表
	 * 
	 * @return
	 */
	Map<Integer, Insurer> insurers();
	
	/**
	 * 险企编辑
	 * 
	 * @param param
	 * @return
	 */
	Result<?> insurerEdit(InsurerEditParam param);
	
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
	
	Set<String> modularsPossessed(Integer tarId, ModularType type);
	
	Map<Integer, Set<String>> modularsPossessed(Set<Integer> employees);
	
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
	 * 授权
	 * 
	 * @param tarId
	 * @param modulars
	 * @param type
	 */
	void authorize(int tarId, Set<Integer> modulars, ModularType type);
}
