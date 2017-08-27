package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Admin;
import org.btkj.master.pojo.param.AuthorizeParam;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

/**
 * 给超级管理员授权
 * 
 * @author ahab
 *
 */
public class AUTHORIZE_ADMIN extends AdminAction<AuthorizeParam> {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<Void> execute(Admin admin, AuthorizeParam param) {
		Admin target = cloudService.admin(param.getTarId());
		if (null == target)
			return Consts.RESULT.USER_NOT_EXIST;
		configManageService.authorizeAdmin(param.getTarId(), param.getModulars());
		return Consts.RESULT.OK;
	}
}
