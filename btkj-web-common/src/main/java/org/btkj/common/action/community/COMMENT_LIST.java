package org.btkj.common.action.community;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.common.pojo.info.CommentInfo;
import org.btkj.community.api.CommunityService;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.pojo.po.Comment;
import org.btkj.pojo.po.UserPO;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;

/**
 * 评论列表
 * 
 * @author ahab
 */
public class COMMENT_LIST extends UserAction {
	
	@Resource
	private UserService userService;
	@Resource
	private CommunityService communityService;

	@Override
	protected Result<?> execute(Request request, User user) {
		int page = request.getParam(Params.PAGE);
		int pageSize = request.getParam(Params.PAGE_SIZE);
		Result<Pager<Comment>> result = communityService.comments(user.getAppId(), request.getParam(Params.ID), page, pageSize);
		if (!result.isSuccess())
			return result;
		List<Comment> list = result.attach().getList();
		if (CollectionUtil.isEmpty(list))
			return BtkjConsts.RESULT.EMPTY_PAGING;
		Set<Integer> ids = new HashSet<Integer>();
		for (Comment comment : list)
			ids.add(comment.getUid());
		
		List<UserPO> users = userService.users(new ArrayList<Integer>(ids));
		List<CommentInfo> l = new ArrayList<CommentInfo>(list.size());
		for (Comment comment : list) {
			for (UserPO temp : users) {
				if (temp.getUid() != comment.getUid())
					continue;
				l.add(new CommentInfo(temp, comment));
				break;
			}
		}
		return Result.result(new Pager<CommentInfo>(result.attach().getTotal(), l));
	}
}
