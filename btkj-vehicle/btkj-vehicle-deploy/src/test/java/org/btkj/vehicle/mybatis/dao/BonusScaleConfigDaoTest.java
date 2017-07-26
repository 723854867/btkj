package org.btkj.vehicle.mybatis.dao;

import javax.annotation.Resource;

import org.btkj.vehicle.BaseTest;
import org.btkj.vehicle.pojo.entity.BonusScaleConfig;
import org.junit.Test;
import org.rapid.util.lang.DateUtil;

public class BonusScaleConfigDaoTest extends BaseTest {

	@Resource
	private BonusScaleConfigDao bonusScaleConfigDao;
	
	@Test
	public void testInsert() {
		BonusScaleConfig config = new BonusScaleConfig();
		config.setComparableValue("1");
		config.setComparison(1);
		config.setCreated(DateUtil.currentTime());
		config.setUpdated(DateUtil.currentTime());
		config.setRate(2);
		config.setTid(1);
		bonusScaleConfigDao.insert(config);
		System.out.println(config.getId());
	}
}
