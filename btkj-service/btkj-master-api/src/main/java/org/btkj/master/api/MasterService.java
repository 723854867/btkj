package org.btkj.master.api;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Region;

public interface MasterService {

	void tenantAdd(App app, Region region, String tname, String name, String mobile, String identity);
}
