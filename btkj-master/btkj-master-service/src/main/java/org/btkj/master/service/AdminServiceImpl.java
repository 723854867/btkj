package org.btkj.master.service;

import javax.annotation.Resource;

import org.btkj.master.api.AdminService;
import org.btkj.master.pojo.entity.Admin;
import org.btkj.master.pojo.entity.Admin.Mod;
import org.btkj.master.redis.AdminMapper;
import org.btkj.pojo.BtkjConsts;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtil;

public class AdminServiceImpl implements AdminService {
	
	@Resource
	private AdminMapper adminMapper;

	@Override
	public Result<Void> seal(int id) {
		Admin admin = adminMapper.getByKey(id);
		if (null == admin)
			return BtkjConsts.RESULT.ADMIN_NOT_EXIST;
		if (!Mod.SEAL.satisfy(admin.getMod())) {
			admin.setMod(admin.getMod() | Mod.SEAL.mark());
			admin.setUpdated(DateUtil.currentTime());
			adminMapper.update(admin);
		}
		return Consts.RESULT.OK;
	}

	@Override
	public Result<Void> unseal(int id) {
		Admin admin = adminMapper.getByKey(id);
		if (null == admin)
			return BtkjConsts.RESULT.ADMIN_NOT_EXIST;
		if (Mod.SEAL.satisfy(admin.getMod())) {
			admin.setMod(admin.getMod() & (~Mod.SEAL.mark()));
			admin.setUpdated(DateUtil.currentTime());
			adminMapper.update(admin);
		}
		return Consts.RESULT.OK;
	}
}
