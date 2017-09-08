package org.btkj.vehicle.api;

import java.util.Map;

import org.btkj.pojo.entity.vehicle.CoefficientRange;
import org.btkj.pojo.model.CoefficientDocument;
import org.btkj.pojo.model.NodeConfigModel;
import org.btkj.pojo.model.PoundageDocument;
import org.btkj.pojo.param.vehicle.CoefficientRangeEditParam;
import org.btkj.pojo.param.vehicle.PoundageConfigEditParam;
import org.rapid.util.common.message.Result;

/**
 * 佣金服务类
 * 
 * @author ahab
 */
public interface BonusService {
	
	/**
	 * 手续费配置节点结构
	 * 
	 * @return
	 */
	Map<Integer, PoundageDocument> poundageDocuments();
	
	/**
	 * 手续费配置节点关联系数
	 * 
	 * @param poundageNodeId
	 * @return
	 */
	Result<Map<Integer, CoefficientDocument>> coefficientDocuments(int poundageNodeId);
	
	/**
	 * 获取指定系数的系数范围
	 * 
	 * @param coefficientId
	 * @return
	 */
	Map<Integer, CoefficientRange> coefficientRanges(int tid, int coefficientId);
	
	/**
	 * 编辑指定系数类型的系数范围
	 * 
	 * @param param
	 * @return
	 */
	Result<Integer> coefficientRangeEdit(CoefficientRangeEditParam param); 
	
	/**
	 * 获取指定险企指定节点的手续费配置
	 * 
	 * @param tid
	 * @param insurerId
	 * @param nodeId
	 * @return
	 */
	NodeConfigModel poundageConfig(int tid, int insurerId, int nodeId, int coefficientId);
	
	/**
	 * 手续费配置编辑
	 * 
	 * @param param
	 * @return
	 */
	Result<Void> poundageConfigEdit(PoundageConfigEditParam param);
}
