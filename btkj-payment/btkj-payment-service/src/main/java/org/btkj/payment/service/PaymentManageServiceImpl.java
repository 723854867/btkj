package org.btkj.payment.service;

import java.util.Map;

import javax.annotation.Resource;

import org.btkj.payment.api.PaymentManageService;
import org.btkj.payment.mybatis.Tx;
import org.btkj.payment.pojo.model.ScoreTips;
import org.btkj.pojo.enums.BizType;
import org.springframework.stereotype.Service;

@Service("paymentManageService")
public class PaymentManageServiceImpl implements PaymentManageService {
	
	@Resource
	private Tx tx;
	
	@Override
	public void gainScores(Map<Integer, Map<BizType, ScoreTips>> map) {
		tx.gainScores(map);
	}
	
	@Override
	public void gainScore(int employeeId, int score, BizType type) {
		tx.gainScore(employeeId, score, type);
	}
}
