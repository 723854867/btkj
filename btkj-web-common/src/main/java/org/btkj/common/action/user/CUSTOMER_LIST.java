package org.btkj.common.action.user;

import javax.annotation.Resource;

import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.pojo.po.Customer;
import org.btkj.user.api.UserService;
import org.btkj.user.pojo.submit.CustomerSearcher;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.enums.SORT_COL;
import org.rapid.util.common.message.Result;

/**
 * 客户列表
 * 
 * @author ahab
 */
public class CUSTOMER_LIST extends UserAction {
	
	@Resource
	private UserService userService;

	@Override
	protected Result<Pager<Customer>> execute(Request request, User user) {
		CustomerSearcher searcher = request.getParam(Params.CUSTOMER_SEARCHER);
		searcher.setUid(user.getUid());
		if (null != searcher.getSortCol()) {
			if (!searcher.getSortCol().equalsIgnoreCase(SORT_COL.CREATED.name()) && !searcher.getSortCol().equalsIgnoreCase(SORT_COL.UPDATED.name()))
				searcher.setSortCol(null);
			else
				searcher.setSortCol(searcher.getSortCol().toLowerCase());
		}
		return userService.customers(searcher);
	}
	
	@Override
	protected boolean userIntegrityVerify(User user) {
		return true;
	}
}