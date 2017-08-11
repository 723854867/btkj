package org.btkj.master.action;

import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;

import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.pojo.BtkjConsts;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.param.PoundageCoefficientEditParam;
import org.btkj.web.util.Params;
import org.rapid.util.common.enums.CRUD_TYPE;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.validator.ValidateGroups;
import org.rapid.util.validator.Validator;

public class POUNDAGE_COEFFICIENT_EDIT extends AdminAction<PoundageCoefficientEditParam> {
	
	@Resource
	private VehicleManageService vehicleManageService;
	
	@Override
	protected Result<?> execute(Administrator admin, PoundageCoefficientEditParam param) {
		param.setTid(BtkjConsts.GLOBAL_TENANT_ID);
		return vehicleManageService.poundageCoefficientEdit(param);
	}
	
	@Override
	protected Set<ConstraintViolation<PoundageCoefficientEditParam>> validate(PoundageCoefficientEditParam param) {
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
