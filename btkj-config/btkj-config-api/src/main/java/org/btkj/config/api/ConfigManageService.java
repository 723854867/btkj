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
import org.btkj.config.pojo.param.ModularEditParam;
import org.btkj.pojo.po.Insurer;
import org.rapid.util.common.message.Result;

public interface ConfigManageService {

	/**
	 * 获取所有的险企
	 * 
	 * @return
	 */
	List<Insurer> insurers();
	
	/**
	 * 新增险企
	 * 
	 * @param name
	 * @param icon
	 * @param biHuId
	 * @param leBaoBaId
	 * @return
	 */
	Result<Void> insurerAdd(int id, String name, String icon, boolean bindBiHu, String leBaoBaId);
	
	/**
	 * 修改险企
	 * 
	 * @param name
	 * @param icon
	 * @param biHuId
	 * @param leBaoBaId
	 * @return
	 */
	Result<Void> insurerUpdate(int id, String name, String icon, boolean bindBiHu, String leBaoBaId);
	
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
