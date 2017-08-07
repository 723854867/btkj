package org.btkj.master.action;

import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;

import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.user.api.UserManageService;
import org.btkj.user.pojo.param.AppEditParam;
import org.btkj.web.util.Params;
import org.rapid.util.common.enums.CRUD_TYPE;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.validator.ValidateGroups;
import org.rapid.util.validator.Validator;

public class APP_EDIT extends AdminAction<AppEditParam> {
	
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<?> execute(Administrator admin, AppEditParam param) {
		return userManageService.appEdit(param);
	}
	
	@Override
	protected Set<ConstraintViolation<AppEditParam>> validate(AppEditParam param) {
		CRUD_TYPE type = request().getParam(Params.CRUD_TYPE);
		param.setType(type);
		switch (type) {
		case CREATE:
			return Validator.JSR_VALIDATOR.validate(param, ValidateGroups.CREATE.class);
		case UPDATE:
			return Validator.JSR_VALIDATOR.validate(param, ValidateGroups.UPDATE.class);
		default:
			throw ConstConvertFailureException.errorConstException(Params.CRUD_TYPE);
		}
	}
}
