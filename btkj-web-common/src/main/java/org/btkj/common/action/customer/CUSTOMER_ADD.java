package org.btkj.common.action.customer;

import java.util.LinkedList;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.pojo.po.Region;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;

public class CUSTOMER_ADD extends UserAction {
	
	@Resource
	private UserService userService;
	@Resource
	private ConfigService configService;

	@Override
	protected Result<?> execute(Request request, User user) {
		String name = request.getParam(Params.NAME);
		String identity = request.getParam(Params.IDENTITY);
		String mobile = request.getParam(Params.MOBILE);
		String license = request.getParam(Params.LICENSE);
		int region = request.getParam(Params.REGION);
		String address = request.getParam(Params.ADDRESS);
		String memo = request.getParam(Params.CONTENT);
		LinkedList<Region> regions = 0 == region ? null : configService.regionRoute(region);
		if (null == regions || regions.size() < 3)
			throw ConstConvertFailureException.errorConstException(Params.REGION);
		return userService.customerAdd(user.getUid(), name, identity, mobile, license, regions, address, memo);
	}
}
