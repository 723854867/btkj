package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.pojo.param.Param;
import org.btkj.user.api.UserManageService;
import org.rapid.util.common.message.Result;

/**
 * 给平台授权
 * 
 * @author ahab
 *
 */
public class APP_AUTHORIZE extends AdminAction<Param> {
	
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<Void> execute(Administrator admin, Param param) {
		
		return null;
	}
}
