package org.btkj.payment.api;

import java.util.Map;

import org.btkj.pojo.enums.BizType;
import org.btkj.pojo.model.ScoreTips;

public interface PaymentManageService {

	/**
	 * 获取积分
	 * 
	 * @param map
	 */
	void gainScores(Map<Integer, Map<BizType, ScoreTips>> map);
	
	/**
	 * 获取积分
	 * 
	 * @param employeeId
	 * @param score
	 */
	void gainScore(int employeeId, int score, BizType type);
}
