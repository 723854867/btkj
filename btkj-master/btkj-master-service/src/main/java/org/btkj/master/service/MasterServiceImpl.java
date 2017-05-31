package org.btkj.master.service;

import org.btkj.master.api.MasterService;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Region;
import org.springframework.stereotype.Service;

@Service("masterService")
public class MasterServiceImpl implements MasterService {

	@Override
	public void tenantAdd(App app, Region region, String tname, String name, String mobile, String identity, String identityFace, String identityBack) {
		
	}
}
