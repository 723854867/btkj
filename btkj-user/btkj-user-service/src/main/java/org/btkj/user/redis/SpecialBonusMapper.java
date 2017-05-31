package org.btkj.user.redis;


import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.SpecialBonus;
import org.btkj.user.persistence.dao.SpecialBonusDao;
import org.rapid.data.storage.mapper.RedisProtostuffDBMapper;


/**
 * 
 * 
 * @author sj
 */
public class SpecialBonusMapper extends RedisProtostuffDBMapper<Integer, SpecialBonus, SpecialBonusDao> {
	

	
	public SpecialBonusMapper() {
		super(BtkjTables.SPECIAL_BONUS, "hash:db:special_bonus");
	}

	public SpecialBonus getByeid(int eid) {
		return dao.selectByeid(eid);
	};
	
}
