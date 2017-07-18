package org.btkj.master.service;

import javax.annotation.Resource;

import org.btkj.master.api.CloudService;
import org.btkj.master.pojo.info.LoginInfo;
import org.btkj.master.redis.AdministratorMapper;
import org.btkj.pojo.po.Administrator;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Service;

@Service("cloudService")
public class CloudServiceImpl implements CloudService {
	
	@Resource
	private AdministratorMapper administratorMapper;

	@Override
	public Result<LoginInfo> login(int id, String pwd) {
		Administrator administrator = administratorMapper.getByKey(id);
		if (null == administrator)
			return Result.result(Code.USER_NOT_EXIST);
		if (!administrator.getPwd().equals(pwd))
			return Result.result(Code.PWD_ERROR);
		String token = administratorMapper.tokenReplace(administrator);
		return Result.result(new LoginInfo(token, administrator.getId()));
	}
	
	@Override
	public void logout(String token) {
		administratorMapper.tokenRemove(token);
	}
	
	@Override
	public Administrator getAdministratorByToken(String token) {
		return administratorMapper.getByToken(token);
	}
}
