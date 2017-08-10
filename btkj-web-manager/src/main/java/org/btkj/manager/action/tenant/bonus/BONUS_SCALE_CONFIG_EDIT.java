package org.btkj.manager.action.tenant.bonus;

import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.param.BonusScaleConfigEditParam;
import org.btkj.web.util.Params;
import org.rapid.util.common.enums.CRUD_TYPE;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.validator.ValidateGroups;
import org.rapid.util.validator.Validator;

public class BONUS_SCALE_CONFIG_EDIT extends EmployeeAction<BonusScaleConfigEditParam> {
	
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<?> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, BonusScaleConfigEditParam param) {
		return vehicleManageService.bonusScaleConfigEdit(tenant.getTid(), param);
	}
	
	@Override
	protected Set<ConstraintViolation<BonusScaleConfigEditParam>> validate(BonusScaleConfigEditParam param) {
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
