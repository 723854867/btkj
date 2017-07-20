package org.btkj.config.api;

import java.util.List;

import org.btkj.config.pojo.info.AreaInfo;
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
	Result<Void> insurerAdd(int id, String name, String icon, boolean bindBiHu, String leBaoBaId, int jianJieId);
	
	/**
	 * 修改险企
	 * 
	 * @param name
	 * @param icon
	 * @param biHuId
	 * @param leBaoBaId
	 * @return
	 */
	Result<Void> insurerUpdate(int id, String name, String icon, boolean bindBiHu, String leBaoBaId, int jianJieId);
	
	/**
	 * 获取所有地区配置信息
	 * 
	 * @return
	 */
	List<AreaInfo> areas();
	
	/**
	 * 新增地区
	 * 
	 * @param code
	 * @param renewalPeriod
	 * @param biHuId
	 * @return
	 */
	Result<Void> areaAdd(int code, int renewalPeriod, int biHuId);
	
	/**
	 * 修改地区
	 * 
	 * @param code
	 * @param renewalPeriod
	 * @param biHuId
	 * @return
	 */
	Result<Void> areaUpdate(int code, int renewalPeriod, int biHuId);
}
