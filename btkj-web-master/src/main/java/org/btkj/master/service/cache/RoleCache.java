package org.btkj.master.service.cache;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.master.persistence.Tables;
import org.btkj.master.persistence.dao.AdministratorDao;
import org.btkj.master.persistence.domain.Administrator;
import org.btkj.master.service.Role;
import org.rapid.util.common.cache.Cache;
import org.springframework.stereotype.Service;

@Service("roleCache")
public class RoleCache extends Cache<Integer, Role> {
	
	@Resource
	private AdministratorDao administratorDao;

	protected RoleCache() {
		super(Tables.ADMINISTRATOR.name());
	}

	@Override
	public void load() throws Exception {
		List<Administrator> list = administratorDao.selectAll();
		if (null == list || list.isEmpty())
			return;
		this.cache.clear();
		for (Administrator administrator : list)
			cache.put(administrator.getId(), new Role(administrator));
	}
}
