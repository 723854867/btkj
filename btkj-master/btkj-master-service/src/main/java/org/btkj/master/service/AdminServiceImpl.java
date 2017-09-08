package org.btkj.master.service;

import javax.annotation.Resource;

import org.btkj.master.api.AdminService;
import org.btkj.master.redis.AdminMapper;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.BtkjUtil;
import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.entity.master.Admin.Mod;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtil;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
	
	@Resource
	private AdminMapper adminMapper;

	@Override
	public Result<Void> seal(int id) {
		Admin admin = adminMapper.getByKey(id);
		if (null == admin)
			return BtkjConsts.RESULT.ADMIN_NOT_EXIST;
		if (BtkjUtil.isTopRole(admin))
			return Consts.RESULT.FORBID;
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
