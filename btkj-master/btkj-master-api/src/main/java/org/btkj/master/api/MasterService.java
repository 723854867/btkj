package org.btkj.master.api;

import org.btkj.master.pojo.entity.Administrator;
import org.btkj.master.pojo.param.AdminEditParam;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.param.Param;
import org.rapid.util.common.message.Result;

public interface MasterService {

	Result<Pager<Administrator>> admins(Param param);
	
	Result<?> adminEdit(AdminEditParam param);
}
