package org.btkj.common.action.user;

import javax.annotation.Resource;

import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Customer;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.user.CustomerListParam;
import org.btkj.user.api.UserService;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.enums.SORT_COL;
import org.rapid.util.common.message.Result;

/**
 * 客户列表
 * 
 * @author ahab
 */
public class CUSTOMER_LIST extends UserAction<CustomerListParam> {
	
	@Resource
	private UserService userService;

	@Override
	protected Result<Pager<Customer>> execute(App app, User user, CustomerListParam param) {
		param.setUid(user.getUid());
		if (null != param.getSortCol()) {
			if (!param.getSortCol().equalsIgnoreCase(SORT_COL.CREATED.name()) && !param.getSortCol().equalsIgnoreCase(SORT_COL.UPDATED.name()))
				param.setSortCol(null);
			else
				param.setSortCol(param.getSortCol().toLowerCase());
		}
		return userService.customers(param);
	}
}
