package org.btkj.master.api;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.entity.User;

public interface MasterService {

	void tenantAdd(App app, Region region, String tname, User user);
}
