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
}
