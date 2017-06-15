package org.btkj.community.redis;

import java.util.List;

import org.btkj.community.mybatis.dao.QuizDao;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.Quiz;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.QuizSearcher;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class QuizMapper extends RedisDBAdapter<Integer, Quiz, QuizDao> {

	public QuizMapper() {
		super(new ByteProtostuffSerializer<Quiz>(), "hash:db:quiz");
	}
	
	public Result<Pager<Quiz>> quizs(QuizSearcher searcher) {
		int total = dao.total(searcher);
		if (0 == total)
			return BtkjConsts.RESULT.EMPTY_PAGING;
		searcher.calculate(total);
		List<Quiz> list = dao.paging(searcher);
		return Result.result(new Pager<Quiz>(searcher.getTotal(), list));
	}
}
