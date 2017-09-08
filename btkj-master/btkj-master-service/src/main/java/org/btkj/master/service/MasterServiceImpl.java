package org.btkj.master.service;

import javax.annotation.Resource;

import org.btkj.master.EntityGenerator;
import org.btkj.master.api.MasterService;
import org.btkj.master.redis.AdminMapper;
import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.Param;
import org.btkj.pojo.param.master.AdminEditParam;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Service;

@Service("masterService")
public class MasterServiceImpl implements MasterService {
	
	@Resource
	private AdminMapper adminMapper;
	
	@Override
	public Result<Pager<Admin>> admins(Param param) {
		return adminMapper.admins(param);
	}
	
	@Override
	public Result<?> adminEdit(AdminEditParam param) {
		switch (param.getCrudType()) {
		case CREATE:
			Admin administrator = EntityGenerator.newAdministrator(param.getName(), param.getPwd());
			adminMapper.insert(administrator);
			return Result.result(Code.OK, administrator.getId());
		default:
			return Consts.RESULT.FORBID;
		}
	}
}
