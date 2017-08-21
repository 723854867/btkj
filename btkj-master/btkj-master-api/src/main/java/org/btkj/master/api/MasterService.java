package org.btkj.master.api;

import org.btkj.master.pojo.entity.Admin;
import org.btkj.master.pojo.param.AdminEditParam;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.Param;
import org.rapid.util.common.message.Result;

public interface MasterService {

	Result<Pager<Admin>> admins(Param param);
	
	Result<?> adminEdit(AdminEditParam param);
}
