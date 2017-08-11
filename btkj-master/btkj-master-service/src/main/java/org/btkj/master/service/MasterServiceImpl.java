package org.btkj.master.service;

import javax.annotation.Resource;

import org.btkj.master.EntityGenerator;
import org.btkj.master.api.MasterService;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.master.pojo.param.AdminEditParam;
import org.btkj.master.redis.AdministratorMapper;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.param.Param;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Service;

@Service("masterService")
public class MasterServiceImpl implements MasterService {
	
	@Resource
	private AdministratorMapper administratorMapper;
	
	@Override
	public Result<Pager<Administrator>> admins(Param param) {
		return administratorMapper.admins(param);
	}
	
	@Override
	public Result<?> adminEdit(AdminEditParam param) {
		switch (param.getType()) {
		case CREATE:
			Administrator administrator = EntityGenerator.newAdministrator(param.getName(), param.getPwd());
			administratorMapper.insert(administrator);
			return Result.result(Code.OK, administrator.getId());
		default:
			return Consts.RESULT.FORBID;
		}
	}
}
