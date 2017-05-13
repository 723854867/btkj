package org.btkj.user.redis;


import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.SpecialCommission;
import org.btkj.user.persistence.dao.SpecialCommissionDao;
import org.rapid.data.storage.mapper.RedisProtostuffDBMapper;


/**
 * 
 * 
 * @author sj
 */
public class SpecialCommissionMapper extends RedisProtostuffDBMapper<Integer, SpecialCommission, SpecialCommissionDao> {
	

	
	public SpecialCommissionMapper() {
		super(BtkjTables.SPECIAL_COMMISSION, "hash:db:special_commission");
	}

	public SpecialCommission getByeid(int eid) {
		return dao.selectByeid(eid);
	};
	
	public boolean isSpecialCommission(int eid) {
		return null == dao.selectByeid(eid);
	}
}
