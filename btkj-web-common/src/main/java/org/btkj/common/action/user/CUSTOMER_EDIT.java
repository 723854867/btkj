package org.btkj.common.action.user;

import java.util.LinkedList;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.pojo.po.Region;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.OldUserAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;

public class CUSTOMER_EDIT extends OldUserAction {
	
	@Resource
	private UserService userService;
	@Resource
	private ConfigService configService;

	@Override
	protected Result<Void> execute(Request request, User user) {
		CrudType crudType = request.getParam(Params.CRUD_TYPE);
		switch (crudType) {
		case CREATE:
			String name = request.getParam(Params.NAME);
			String identity = request.getParam(Params.IDENTITY);
			String mobile = request.getParam(Params.MOBILE);
			String license = request.getParam(Params.LICENSE);
			int region = request.getParam(Params.REGION);
			String address = request.getParam(Params.ADDRESS);
			String memo = request.getParam(Params.CONTENT);
			LinkedList<Region> regions = 0 == region ? null : configService.regionRoute(region);
			if (null == regions || regions.size() < 4)
				throw ConstConvertFailureException.errorConstException(Params.REGION);
				return userService.customerAdd(user.getUid(), name, identity, mobile, license, regions, address, memo);
		case UPDATE:
			name = request.getOptionalParam(Params.NAME);
			identity = request.getOptionalParam(Params.IDENTITY);
			mobile = request.getOptionalParam(Params.MOBILE);
			license = request.getOptionalParam(Params.LICENSE);
			region = request.getOptionalParam(Params.REGION);
			address = request.getOptionalParam(Params.ADDRESS);
			memo = request.getOptionalParam(Params.CONTENT);
			regions = 0 == region ? null : configService.regionRoute(region);
			if (null != regions && regions.size() < 4)
				throw ConstConvertFailureException.errorConstException(Params.REGION);
			return userService.customerUpdate(request.getParam(Params.ID), user.getUid(), name, identity, mobile, license, regions, address, memo);
		case DELETE:
			return userService.customerDelete(request.getParam(Params.ID), user.getUid());
		default:
			return Consts.RESULT.FORBID;
		}
	}
	
	@Override
	protected boolean userIntegrityVerify(User user) {
		return true;
	}
}
