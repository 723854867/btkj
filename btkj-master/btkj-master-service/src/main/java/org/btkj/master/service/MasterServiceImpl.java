package org.btkj.master.service;

import javax.annotation.Resource;

import org.btkj.master.EntityGenerator;
import org.btkj.master.api.MasterService;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.master.redis.AdministratorMapper;
import org.btkj.pojo.bo.Pager;
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
	
	@Override
	public int administraorAdd(String name, String pwd) {
		Administrator administrator = EntityGenerator.newAdministrator(name, pwd);
		administratorMapper.insert(administrator);
		return administrator.getId();
	}
}
