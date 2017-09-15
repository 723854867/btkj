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
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.IdParam;
import org.btkj.user.api.UserService;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;

/**
 * 评论列表
 * 
 * @author ahab
 */
public class COMMENT_LIST extends UserAction<IdParam> {
	
	@Resource
	private UserService userService;
	@Resource
	private CommunityService communityService;

	@Override
	protected Result<?> execute(App app, User user, IdParam param) {
		Result<Pager<Comment>> result = communityService.comments(user.getAppId(), param.getId(), param.getPage(), param.getPageSize());
		if (!result.isSuccess())
			return result;
		List<Comment> list = result.attach().getList();
		if (CollectionUtil.isEmpty(list))
			return BtkjConsts.RESULT.EMPTY_PAGING;
		Set<Integer> ids = new HashSet<Integer>();
		for (Comment comment : list)
			ids.add(comment.getUid());
		
		Map<Integer, User> users = userService.users(new ArrayList<Integer>(ids));
		List<CommentInfo> l = new ArrayList<CommentInfo>(list.size());
		for (Comment comment : list) {
			User up =  users.get(comment.getUid());
			l.add(new CommentInfo(app, up, comment));
		}
		return Result.result(new Pager<CommentInfo>(result.attach().getTotal(), l));
	}
}
