package org.btkj.master.service;

import javax.annotation.Resource;

import org.btkj.master.api.MasterService;
import org.btkj.master.redis.AdministratorMapper;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.po.Administrator;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Service;

@Service("masterService")
public class MasterServiceImpl implements MasterService {
	
	@Resource
	private AdministratorMapper administratorMapper;
	
	@Override
	public Result<Pager<Administrator>> administrators(int page, int pageSize) {
		return administratorMapper.paging(page, pageSize);
	}
}
