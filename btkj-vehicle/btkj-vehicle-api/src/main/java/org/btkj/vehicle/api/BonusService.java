package org.btkj.vehicle.api;

import java.util.List;
import java.util.Map;

import org.btkj.vehicle.pojo.model.CoefficientRange;
import org.btkj.vehicle.pojo.model.PoundageStructure;
import org.btkj.vehicle.pojo.param.PoundageCoefficientRangeEditParam;
import org.btkj.vehicle.pojo.param.PoundageErogidicParam;
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
	Map<Integer, PoundageStructure> poundageNodeStructure();
	
	/**
	 * 获取指定系数类型的系数范围
	 * 
	 * @param cfgCoefficientId
	 * @return
	 */
	Result<List<CoefficientRange>> poundageCoefficientRanges(int tid, int cfgCoefficientId);
	
	/**
	 * 编辑指定系数类型的系数范围
	 * 
	 * @param param
	 * @return
	 */
	Result<Integer> poundageCoefficientRangeEdit(PoundageCoefficientRangeEditParam param); 
	
	/**
	 * 手续费递归操作
	 * 
	 * @param param
	 * @return
	 */
	Result<?> poundageErgodic(PoundageErogidicParam param); 
}
