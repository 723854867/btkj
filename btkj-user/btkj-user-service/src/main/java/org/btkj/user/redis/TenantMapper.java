package org.btkj.user.redis;

import java.util.ArrayList;
import java.util.List;

import org.btkj.pojo.entity.user.TenantPO;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.user.TenantPagingInfo;
import org.btkj.pojo.info.user.TenantPagingMasterInfo;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.user.TenantsParam;
import org.btkj.user.mybatis.dao.TenantDao;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class TenantMapper extends RedisDBAdapter<Integer, TenantPO, TenantDao> {
	
	public TenantMapper() {
		super(new ByteProtostuffSerializer<TenantPO>(), "hash:db:tenant");
	}
	
	public int countByAppId(int appId) {
		return dao.countByAppId(appId);
	}
	
	public Pager<TenantPagingInfo> tenants(TenantsParam param) {
		int total = dao.count(param);
		if (0 == total)
			return Pager.EMPLTY;
		param.calculate(total);
		List<TenantPO> tenants = dao.tenants(param);
		List<TenantPagingInfo> list = new ArrayList<TenantPagingInfo>(); 
		for (TenantPO tenant : tenants) 
			list.add(param.getClient() == Client.TENANT_MANAGER ? new TenantPagingInfo(tenant) : new TenantPagingMasterInfo(tenant));
		return new Pager<TenantPagingInfo>(total, list);
	}
}
