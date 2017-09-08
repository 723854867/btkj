package org.btkj.master.api;

import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.Param;
import org.btkj.pojo.param.master.AdminEditParam;
import org.rapid.util.common.message.Result;

public interface MasterService {

	Result<Pager<Admin>> admins(Param param);
	
	Result<?> adminEdit(AdminEditParam param);
}
