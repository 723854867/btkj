package org.btkj.master.api;

import org.btkj.pojo.entity.Administrator;
import org.btkj.pojo.model.Pager;
import org.rapid.util.common.message.Result;

public interface MasterService {

	Result<Pager<Administrator>> administrators(int page, int pageSize);
}
