package org.btkj.master.service;

import javax.annotation.Resource;

import org.btkj.master.api.CloudService;
import org.btkj.master.pojo.entity.Admin;
import org.btkj.master.pojo.info.LoginInfo;
import org.btkj.master.redis.AdminMapper;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Service;

@Service("cloudService")
public class CloudServiceImpl implements CloudService {
	
	@Resource
	private AdminMapper adminMapper;

	@Override
	public Result<LoginInfo> login(int id, String pwd) {
		Admin administrator = adminMapper.getByKey(id);
		if (null == administrator)
			return Result.result(Code.USER_NOT_EXIST);
		if (!administrator.getPwd().equals(pwd))
			return Result.result(Code.PWD_ERROR);
		String token = adminMapper.tokenReplace(administrator);
		return Result.result(new LoginInfo(token, administrator.getId()));
	}
	
	@Override
	public void logout(String token) {
		adminMapper.tokenRemove(token);
	}
	
	@Override
	public Admin getAdministratorByToken(String token) {
		return adminMapper.getByToken(token);
	}
	
	@Override
	public Admin admin(int id) {
		return adminMapper.getByKey(id);
	}
}
