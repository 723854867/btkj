package org.btkj.community.service;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityManageService;
import org.btkj.community.redis.QuizMapper;
import org.btkj.community.redis.ReplyMapper;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.Quiz;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Service;

@Service("communityManageService")
public class CommunityManageServiceImpl implements CommunityManageService {
	
	@Resource
	private QuizMapper quizMapper;
	@Resource
	private ReplyMapper replyMapper;

	@Override
	public Result<Void> quizDelete(int quizId, int appId) {
		Quiz quiz = quizMapper.getByKey(quizId);
		if (null == quiz)
			return BtkjConsts.RESULT.QUIZ_NOT_EXIST;
		if (quiz.getAppId() != appId)
			return Consts.RESULT.FORBID;
		quizMapper.delete(quiz);
		replyMapper.deleteByQuizId(quizId);
		return Consts.RESULT.OK;
	}
}
