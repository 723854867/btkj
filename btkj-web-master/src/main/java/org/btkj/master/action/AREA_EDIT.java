package org.btkj.master.action;

import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;

import org.btkj.config.api.ConfigManageService;
import org.btkj.config.pojo.param.AreaEditParam;
import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.web.util.Params;
import org.rapid.util.common.enums.CRUD_TYPE;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.validator.ValidateGroups;
import org.rapid.util.validator.Validator;

public class AREA_EDIT extends AdminAction<AreaEditParam> {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<Void> execute(Administrator admin, AreaEditParam param) {
		return configManageService.areaEdit(param);
	}
	
	@Override
	protected Set<ConstraintViolation<AreaEditParam>> validate(AreaEditParam param) {
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
