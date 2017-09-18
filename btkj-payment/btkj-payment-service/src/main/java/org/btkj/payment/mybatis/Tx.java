package org.btkj.payment.mybatis;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.btkj.payment.EntityGenerator;
import org.btkj.payment.mybatis.dao.AccountDao;
import org.btkj.pojo.entity.payment.Account;
import org.btkj.pojo.enums.ScoreType;
import org.btkj.pojo.model.ScoreTips;
import org.rapid.util.lang.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tx")
public class Tx {

	@Resource
	private AccountDao accountDao;
	
	@Transactional
	public void gainScores(Map<Integer, Map<ScoreType, ScoreTips>> map) {
		Map<Integer, Account> accounts = accountDao.getByKeysForUpdate(map.keySet());
		for (Entry<Integer, Map<ScoreType, ScoreTips>> entry : map.entrySet()) {
			Account account = accounts.get(entry.getKey());
			if (null == account) {
				account = EntityGenerator.newAccount(entry.getKey()); 
				accounts.put(entry.getKey(), account);
			}
			for (Entry<ScoreType, ScoreTips> temp : entry.getValue().entrySet()) {
				int score = temp.getValue().getScore();
				account.setScoreAvailable(account.getScoreAvailable() + score);
				account.setScorePersonal(account.getScorePersonal() + score);
				switch (temp.getKey()) {
				case VEHICLE_BOUNS_MANAGE:
					account.setScoreManage(account.getScoreManage() + score);
					break;
				case VEHICLE_BONUS_SCALE:
					account.setScoreScale(account.getScoreScale() + score);
					break;
				default:
					break;
				}
				account.setUpdated(DateUtil.currentTime());
			}
		}
		accountDao.replace(accounts);
	}
	
	@Transactional
	public void gainScore(int employeeId, int score, ScoreType type) {
		Account account = accountDao.getByKeyForUpdate(employeeId);
		if (null == account) 
			account = EntityGenerator.newAccount(employeeId); 
		account.setScoreAvailable(account.getScoreAvailable() + score);
		account.setScorePersonal(account.getScorePersonal() + score);
		switch (type) {
		case VEHICLE_BOUNS_MANAGE:
			account.setScoreManage(account.getScoreManage() + score);
			break;
		case VEHICLE_BONUS_SCALE:
			account.setScoreScale(account.getScoreScale() + score);
			break;
		default:
			break;
		}
	}
}
