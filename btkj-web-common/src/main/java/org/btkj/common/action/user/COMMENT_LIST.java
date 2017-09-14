package org.btkj.common.action.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.common.pojo.info.CommentInfo;
import org.btkj.community.api.CommunityService;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.community.Comment;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.model.identity.User;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.btkj.web.util.action.OldUserAction;
import org.btkj.web.util.action.Request;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;

/**
 * 评论列表
 * 
 * @author ahab
 */
public class COMMENT_LIST extends OldUserAction {
	
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
		
		Map<Integer, UserPO> users = userService.users(new ArrayList<Integer>(ids));
		List<CommentInfo> l = new ArrayList<CommentInfo>(list.size());
		for (Comment comment : list) {
			UserPO up =  users.get(comment.getUid());
			l.add(new CommentInfo(up, comment));
		}
		return Result.result(new Pager<CommentInfo>(result.attach().getTotal(), l));
	}
	
	@Override
	protected boolean userIntegrityVerify(User user) {
		return true;
	}
}
