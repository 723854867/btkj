package org.btkj.user.redis;

import java.util.ArrayList;
import java.util.List;

import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.po.TenantPO;
import org.btkj.user.mybatis.dao.TenantDao;
import org.btkj.user.pojo.info.TenantPagingInfo;
import org.btkj.user.pojo.info.TenantPagingMasterInfo;
import org.btkj.user.pojo.submit.TenantSearcher;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class TenantMapper extends RedisDBAdapter<Integer, TenantPO, TenantDao> {
	
	public TenantMapper() {
		super(new ByteProtostuffSerializer<TenantPO>(), "hash:db:tenant");
	}
	
	public int countByAppId(int appId) {
		return dao.countByAppId(appId);
	}
	
	public Pager<TenantPagingInfo> paging(TenantSearcher searcher) {
		int total = dao.count(searcher);
		if (0 == total)
			return Pager.EMPLTY;
		searcher.calculate(total);
		List<TenantPO> tenants = dao.paging(searcher);
		List<TenantPagingInfo> list = new ArrayList<TenantPagingInfo>(); 
		for (TenantPO tenant : tenants) 
			list.add(searcher.getClient() == Client.TENANT_MANAGER ? new TenantPagingInfo(tenant) : new TenantPagingMasterInfo(tenant));
		return new Pager<TenantPagingInfo>(searcher.getTotal(), list);
	}
}
