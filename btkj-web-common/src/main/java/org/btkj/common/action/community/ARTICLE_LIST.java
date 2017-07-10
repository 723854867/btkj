package org.btkj.common.action.community;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityService;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Article;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.ArticleSearcher;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;

/**
 * 咨询列表
 * 
 * @author ahab
 */
public class ARTICLE_LIST extends UserAction {
	
	@Resource
	private CommunityService communityService;

	@Override
	protected Result<Pager<Article>> execute(Request request, App app, Client client, User operator) {
		ArticleSearcher searcher = request.getParam(Params.ARTICLE_SEARCHER);
		if (null == searcher.getSortCol())
			throw ConstConvertFailureException.errorConstException(Params.ARTICLE_SEARCHER);
		searcher.setAppId(app.getId());
		return communityService.articles(searcher);
	}
}
