package org.btkj.user.redis;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.App;
import org.btkj.user.persistence.dao.AppDao;
import org.rapid.data.storage.mapper.ProtostuffDBMapper;

public class AppMapper extends ProtostuffDBMapper<Integer, App, AppDao> {
	
	private static final String APP_DATA				= "hash:db:app";					

	public AppMapper() {
		super(BtkjTables.APP, APP_DATA);
	}
}