package org.btkj.payment.service;

import java.util.Map;

import javax.annotation.Resource;

import org.btkj.payment.api.PaymentManageService;
import org.btkj.payment.mybatis.Tx;
import org.btkj.pojo.enums.ScoreType;
import org.btkj.pojo.model.ScoreTips;
import org.springframework.stereotype.Service;

@Service("paymentManageService")
public class PaymentManageServiceImpl implements PaymentManageService {
	
	@Resource
	private Tx tx;
	
	@Override
	public void gainScores(Map<Integer, Map<ScoreType, ScoreTips>> map) {
		tx.gainScores(map);
	}
	
	@Override
	public void gainScore(int employeeId, int score, ScoreType type) {
		tx.gainScore(employeeId, score, type);
	}
}
