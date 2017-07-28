package org.btkj.master.api;

import org.btkj.master.pojo.entity.Administrator;
import org.btkj.pojo.bo.Pager;
import org.rapid.util.common.message.Result;

public interface MasterService {

	Result<Pager<Administrator>> administrators(int page, int pageSize);
	
	int administraorAdd(String name, String pwd);
}
