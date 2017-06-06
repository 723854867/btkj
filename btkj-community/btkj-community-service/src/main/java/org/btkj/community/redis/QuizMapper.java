package org.btkj.community.redis;

import java.util.List;

import org.btkj.community.mybatis.dao.QuizDao;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Quiz;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.QuizSearcher;
import org.rapid.data.storage.mapper.RedisProtostuffDBMapper;
import org.rapid.util.common.message.Result;

public class QuizMapper extends RedisProtostuffDBMapper<Integer, Quiz, QuizDao> {

	public QuizMapper() {
		super(BtkjTables.QUIZ, "hash:db:quiz");
	}
	
	public Result<Pager<Quiz>> quizs(QuizSearcher searcher) {
		int total = dao.count(searcher);
		if (0 == total)
			return BtkjConsts.RESULT.EMPTY_PAGING;
		searcher.calculate(total);
		List<Quiz> list = dao.search(searcher);
		return Result.result(new Pager<Quiz>(total, list));
	}
}
