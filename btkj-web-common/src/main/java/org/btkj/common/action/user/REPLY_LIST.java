package org.btkj.common.action.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.common.pojo.info.ReplyInfo;
import org.btkj.community.api.CommunityService;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.community.Reply;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.IdParam;
import org.btkj.user.api.UserService;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;

/**
 * 提问回复分页
 * 
 * @author ahab
 */
public class REPLY_LIST extends UserAction<IdParam> {
	
	@Resource
	private UserService userService;
	@Resource
	private CommunityService communityService;
	
	@Override
	protected Result<?> execute(AppPO app, UserPO user, IdParam param) {
		Result<Pager<Reply>> result = communityService.replies(user.getAppId(), param.getId(), param.getPage(), param.getPageSize());
		if (!result.isSuccess())
			return result;
		
		List<Reply> list = result.attach().getList();
		if (CollectionUtil.isEmpty(list))
			return BtkjConsts.RESULT.EMPTY_PAGING;
		Set<Integer> ids = new HashSet<Integer>();
		for (Reply reply : list)
			ids.add(reply.getUid());
		Map<Integer, UserPO> users = userService.users(new ArrayList<Integer>(ids));
		List<ReplyInfo> l = new ArrayList<ReplyInfo>(list.size());
		for (Reply reply : list) {
			UserPO up = users.get(reply.getUid());
			l.add(new ReplyInfo(app, up, reply));
		}
		return Result.result(new Pager<ReplyInfo>(result.attach().getTotal(), l));
	}
}
