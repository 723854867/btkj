package org.btkj.common.action.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.common.pojo.info.ReplyInfo;
import org.btkj.community.api.CommunityService;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.bo.indentity.User;
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
	protected Result<?> execute(Request request, User user) {
		Result<Pager<Reply>> result = communityService.replies(user.getAppId(), request.getParam(Params.ID), 
				request.getParam(Params.PAGE), request.getParam(Params.PAGE_SIZE));
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
			for (UserPO temp : users) {
				if (temp.getUid() != reply.getUid())
					continue;
				l.add(new ReplyInfo(temp, reply));
				break;
			}
		}
		return Result.result(new Pager<ReplyInfo>(result.attach().getTotal(), l));
	}
	
	@Override
	protected boolean userIntegrityVerify(User user) {
		return true;
	}
}
