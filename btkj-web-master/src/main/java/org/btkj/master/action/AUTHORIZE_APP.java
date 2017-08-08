package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.pojo.param.Param;
import org.rapid.util.common.message.Result;

/**
 * 给平台授权
 * 
 * @author ahab
 */
public class AUTHORIZE_APP extends AdminAction<Param> {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<?> execute(Administrator admin, Param param) {
		return null;
	}
}
