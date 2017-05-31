package org.btkj.user.redis;

import java.util.List;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.info.TenantListPc;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.TenantSearcher;
import org.btkj.user.persistence.dao.TenantDao;
import org.rapid.data.storage.mapper.RedisProtostuffDBMapper;
import org.rapid.util.common.message.Result;

public class TenantMapper extends RedisProtostuffDBMapper<Integer, Tenant, TenantDao> {
	
	protected TenantMapper() {
		super(BtkjTables.TENANT, "hash:db:tenant");
	}
	/**
	 * 分页获取平台Tenant用户
	 * 
	 * @param pager
	 * @param tid
	 */
	@SuppressWarnings("unchecked")
	public Result<Pager<TenantListPc>> tenantList(TenantSearcher searcher) {
		int total = dao.searchCount(searcher);
		if (0 == total)
			return Result.result(Pager.EMPLTY);
		searcher.calculate(total);
		List<TenantListPc> tenants = dao.search(searcher);
		Pager<TenantListPc> pager = new Pager<TenantListPc>(searcher.getTotal(), tenants);
		return Result.result(pager);
	}
	
	public int countByAppId(int appId) {
		return dao.countByAppId(appId);
	}
}
