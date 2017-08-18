package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.manager.action.UserAction;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.user.api.UserManageService;
import org.btkj.user.pojo.param.BannerEditParam;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.message.Result;

/**
 * 轮播图编辑
 * 
 * @author ahab
 */
public class BANNER_EDIT extends UserAction<BannerEditParam> {
	
	@Resource
	private UserManageService userManageService;
	
	public BANNER_EDIT() {
		super(CrudType.CREATE, CrudType.UPDATE, CrudType.DELETE);
	}

	@Override
	protected Result<?> execute(AppPO app, UserPO user, BannerEditParam param) {
		param.setAppId(app.getId());
		return userManageService.bannerEdit(param);
	}
}
