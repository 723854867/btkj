package org.btkj.master.action;

import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;

import org.btkj.master.AdminAction;
import org.btkj.master.api.MasterService;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.master.pojo.param.AdminEditParam;
import org.btkj.web.util.Params;
import org.rapid.util.common.enums.CRUD_TYPE;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.validator.ValidateGroups;
import org.rapid.util.validator.Validator;

public class ADMIN_EDIT extends AdminAction<AdminEditParam> {
	
	@Resource
	private MasterService masterService;
	
	@Override
	protected Result<?> execute(Administrator admin, AdminEditParam param) {
		return masterService.adminEdit(param);
	}
	
	@Override
	protected Set<ConstraintViolation<AdminEditParam>> validate(AdminEditParam param) {
		CRUD_TYPE type = request().getParam(Params.CRUD_TYPE);
		param.setType(type);
		switch (type) {
		case CREATE:
			return Validator.JSR_VALIDATOR.validate(param, ValidateGroups.CREATE.class);
		default:
			throw ConstConvertFailureException.errorConstException(Params.CRUD_TYPE);
		}
	}
}
