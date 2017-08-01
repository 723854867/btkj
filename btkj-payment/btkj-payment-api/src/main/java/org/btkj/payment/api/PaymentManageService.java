package org.btkj.payment.api;

import java.util.Map;

import org.btkj.payment.pojo.model.ScoreTips;
import org.btkj.pojo.enums.BizType;

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
