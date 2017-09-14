package org.btkj.common.action.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.common.pojo.info.QuizInfo;
import org.btkj.community.api.CommunityService;
import org.btkj.pojo.entity.community.Quiz;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.community.QuizListParam;
import org.btkj.user.api.UserService;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;

/**
 * 问答分页
 * 
 * @author ahab
 */
public class QUIZ_LIST extends UserAction<QuizListParam> {
	
	@Resource
	private UserService userService;
	@Resource
	private CommunityService communityService;

	@Override
	protected Result<?> execute(AppPO app, UserPO user, QuizListParam param) {
		param.setAppId(app.getId());
		switch (client()) {
		case TENANT_MANAGER:
			Result<Pager<Quiz>> result = communityService.quizs(param);
			Set<Integer> set = new HashSet<Integer>();
			for (Quiz quiz : result.attach().getList())
				set.add(quiz.getUid());
			Map<Integer, UserPO> users = userService.users(new ArrayList<Integer>(set));
			List<QuizInfo> list = new ArrayList<QuizInfo>(result.attach().getList().size());
			for (Quiz quiz : result.attach().getList()) {
				UserPO up = users.get(quiz.getUid());
				list.add(new QuizInfo(app, up, quiz));
			}
			return Result.result(new Pager<QuizInfo>(result.attach().getTotal(), list));
		default:
			return communityService.quizs(param);
		}
	}
}
