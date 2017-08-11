package org.btkj.master.action;

import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;

import org.btkj.bihu.vehicle.api.BiHuManageService;
import org.btkj.bihu.vehicle.pojo.param.TenantConfigEditParam;
import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.po.TenantPO;
import org.btkj.user.api.TenantService;
import org.btkj.web.util.Params;
import org.rapid.util.common.enums.CRUD_TYPE;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.validator.ValidateGroups;
import org.rapid.util.validator.Validator;

public class BI_HU_TENANT_CONFIG_EDIT extends AdminAction<TenantConfigEditParam> {
	
	@Resource
	private TenantService tenantService;
	@Resource
	private BiHuManageService biHuManageService;

	@Override
	protected Result<?> execute(Administrator admin, TenantConfigEditParam param) {
		if (param.getType() == CRUD_TYPE.CREATE) {
			TenantPO tenant = tenantService.tenant(param.getTid());
			if (null == tenant)
				return BtkjConsts.RESULT.TENANT_NOT_EXIST;
		}
		return biHuManageService.tenantConfigEdit(param);
	}
	
	@Override
	protected Set<ConstraintViolation<TenantConfigEditParam>> validate(TenantConfigEditParam param) {
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
