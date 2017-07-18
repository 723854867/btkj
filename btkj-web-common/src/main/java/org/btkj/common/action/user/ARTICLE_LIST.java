package org.btkj.common.action.user;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityService;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.pojo.po.Article;
import org.btkj.pojo.vo.ArticleSearcher;
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
	protected Result<Pager<Article>> execute(Request request, User user) {
		ArticleSearcher searcher = request.getParam(Params.ARTICLE_SEARCHER);
		if (null == searcher.getSortCol())
			throw ConstConvertFailureException.errorConstException(Params.ARTICLE_SEARCHER);
		searcher.setAppId(user.getAppId());
		return communityService.articles(searcher);
	}
	
	@Override
	protected boolean userIntegrityVerify(User user) {
		return false;
	}
}
