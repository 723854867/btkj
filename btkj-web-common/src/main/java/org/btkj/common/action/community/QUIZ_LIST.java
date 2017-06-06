package org.btkj.common.action.community;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityService;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Quiz;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.QuizSearcher;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;

/**
 * 问答分页
 * 
 * @author ahab
 */
public class QUIZ_LIST extends UserAction {
	
	@Resource
	private CommunityService communityService;

	@Override
	protected Result<Pager<Quiz>> execute(Request request, App app, Client client, User user) {
		QuizSearcher searcher = request.getParam(Params.QUIZ_SEARCHER);
		searcher.setAppId(app.getId());
		return communityService.quizs(searcher);
	}
}
