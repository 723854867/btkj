package org.btkj.manager.action.user;

import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;

import org.btkj.manager.action.UserAction;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.user.api.UserManageService;
import org.btkj.user.pojo.param.BannerEditParam;
import org.btkj.web.util.Params;
import org.rapid.util.common.enums.CRUD_TYPE;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.validator.ValidateGroups;
import org.rapid.util.validator.Validator;

/**
 * 轮播图编辑
 * 
 * @author ahab
 */
public class BANNER_EDIT extends UserAction<BannerEditParam> {
	
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<?> execute(AppPO app, UserPO user, BannerEditParam param) {
		param.setAppId(app.getId());
		return userManageService.bannerEdit(param);
	}
	
	@Override
	protected Set<ConstraintViolation<BannerEditParam>> validate(BannerEditParam param) {
		CRUD_TYPE type = request().getParam(Params.CRUD_TYPE);
		param.setType(type);
		switch (type) {
		case CREATE:
			return Validator.JSR_VALIDATOR.validate(param, ValidateGroups.CREATE.class);
		case UPDATE:
			return Validator.JSR_VALIDATOR.validate(param, ValidateGroups.UPDATE.class);
		case DELETE:
			return Validator.JSR_VALIDATOR.validate(param, ValidateGroups.DELETE.class);
		default:
			throw ConstConvertFailureException.errorConstException(Params.CRUD_TYPE);
		}
	}
}
