package org.btkj.common.action.user;

import java.util.LinkedList;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.entity.config.Region;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.param.user.CustomerEditParam;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.message.Result;

public class CUSTOMER_EDIT extends UserAction<CustomerEditParam> {
	
	@Resource
	private ConfigService configService;
	
	public CUSTOMER_EDIT() {
		super(CrudType.CREATE, CrudType.UPDATE, CrudType.DELETE);
	}

	@Override
	protected Result<Void> execute(App app, User user, CustomerEditParam param) {
		if (null != param.getRegion()) {
			LinkedList<Region> regions = configService.regionRoute(param.getRegion());
			if (null != regions && regions.size() < 4)
				return Consts.RESULT.FORBID;
			param.setRegions(regions);
		}
		param.setUid(user.getUid());
		return userService.customerEdit(param);
	}
}
