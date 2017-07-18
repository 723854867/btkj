package org.btkj.common.action.community;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.common.pojo.info.ReplyInfo;
import org.btkj.community.api.CommunityService;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.Reply;
import org.btkj.pojo.po.UserPO;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;

/**
 * 提问回复分页
 * 
 * @author ahab
 */
public class REPLY_LIST extends UserAction {
	
	@Resource
	private UserService userService;
	@Resource
	private CommunityService communityService;
	
	@Override
	protected Result<?> execute(Request request, AppPO app, Client client, UserPO operator) {
		Result<Pager<Reply>> result = communityService.replies(
				app.getId(), 
				request.getParam(Params.ID), 
				request.getParam(Params.PAGE), 
				request.getParam(Params.PAGE_SIZE));
		if (!result.isSuccess())
			return result;
		
		List<Reply> list = result.attach().getList();
		if (CollectionUtil.isEmpty(list))
			return BtkjConsts.RESULT.EMPTY_PAGING;
		Set<Integer> ids = new HashSet<Integer>();
		for (Reply reply : list)
			ids.add(reply.getUid());
		List<UserPO> users = userService.users(new ArrayList<Integer>(ids));
		List<ReplyInfo> l = new ArrayList<ReplyInfo>(list.size());
		for (Reply reply : list) {
			for (UserPO user : users) {
				if (user.getUid() != reply.getUid())
					continue;
				l.add(new ReplyInfo(user, reply));
				break;
			}
		}
		return Result.result(new Pager<ReplyInfo>(result.attach().getTotal(), l));
	}
}
