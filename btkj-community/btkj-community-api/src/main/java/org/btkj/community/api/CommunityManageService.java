package org.btkj.community.api;

import org.rapid.util.common.message.Result;

/**
 * 咨询模块后台服务类
 * 
 * @author ahab
 */
public interface CommunityManageService {

	/**
	 * 删除提问
	 * 
	 * @param quizId
	 * @param appId
	 * @return
	 */
	Result<Void> quizDelete(int quizId, int appId);
	
	/**
	 * 删除回复
	 * 
	 * @param replyId
	 * @param appId
	 * @return
	 */
	Result<Void> replyDelete(int replyId, int appId);
	
	/**
	 * 删除咨询
	 * 
	 * @param articleId
	 * @param appId
	 * @return
	 */
	Result<Void> articleDelete(int articleId, int appId);
	
	/**
	 * 删除评论
	 * 
	 * @param commentId
	 * @param appId
	 * @return
	 */
	Result<Void> commentDelete(int commentId, int appId);
}
