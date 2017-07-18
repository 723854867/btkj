package org.btkj.master.api;

import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.po.Administrator;
import org.rapid.util.common.message.Result;

public interface MasterService {

	Result<Pager<Administrator>> administrators(int page, int pageSize);
}
