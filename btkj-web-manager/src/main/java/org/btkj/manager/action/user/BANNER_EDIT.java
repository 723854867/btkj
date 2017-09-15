package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.manager.action.UserAction;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.param.user.BannerEditParam;
import org.btkj.user.api.UserManageService;
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
	protected Result<?> execute(App app, User user, BannerEditParam param) {
		param.setAppId(app.getId());
		return userManageService.bannerEdit(param);
	}
}
