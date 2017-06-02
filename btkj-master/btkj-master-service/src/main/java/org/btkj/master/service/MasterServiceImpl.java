package org.btkj.master.service;

import org.btkj.master.api.MasterService;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.entity.User;
import org.springframework.stereotype.Service;

@Service("masterService")
public class MasterServiceImpl implements MasterService {

	@Override
	public void tenantAdd(App app, Region region, String tname, User user) {
		
	}
}
