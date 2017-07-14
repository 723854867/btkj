package org.btkj.config.api;

import java.util.List;

import org.btkj.pojo.entity.Insurer;
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
}
