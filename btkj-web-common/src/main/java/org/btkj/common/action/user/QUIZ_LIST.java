package org.btkj.common.action.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.common.pojo.info.QuizInfo;
import org.btkj.community.api.CommunityService;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.pojo.po.Quiz;
import org.btkj.pojo.po.UserPO;
import org.btkj.pojo.vo.QuizSearcher;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;

/**
 * 问答分页
 * 
 * @author ahab
 */
public class QUIZ_LIST extends UserAction {
	
	@Resource
	private UserService userService;
	@Resource
	private CommunityService communityService;

	@Override
	protected Result<?> execute(Request request, User user) {
		QuizSearcher searcher = request.getParam(Params.QUIZ_SEARCHER);
		if (null == searcher.getSortCol())
			throw ConstConvertFailureException.errorConstException(Params.QUIZ_SEARCHER);
		searcher.setAppId(user.getAppId());
		switch (user.getClient()) {
		case TENANT_MANAGER:
			Result<Pager<Quiz>> result = communityService.quizs(searcher);
			Set<Integer> set = new HashSet<Integer>();
			for (Quiz quiz : result.attach().getList())
				set.add(quiz.getUid());
			List<UserPO> users = userService.users(new ArrayList<Integer>(set));
			List<QuizInfo> list = new ArrayList<QuizInfo>(result.attach().getList().size());
			for (Quiz quiz : result.attach().getList()) {
				for (UserPO u : users) {
					if (u.getUid() != quiz.getUid())
						continue;
					list.add(new QuizInfo(u, quiz));
				}
			}
			return Result.result(new Pager<QuizInfo>(result.attach().getTotal(), list));
		default:
			return communityService.quizs(searcher);
		}
	}
	
	@Override
	protected boolean userIntegrityVerify(User user) {
		return false;
	}
}
