package org.btkj.community.mybatis;

import javax.annotation.Resource;

import org.btkj.community.mybatis.dao.QuizDao;
import org.btkj.community.BaseTest;
import org.btkj.pojo.submit.QuizSearcher;
import org.junit.Test;

public class QuizDaoTest extends BaseTest {
	
	@Resource
	private QuizDao quizDao;

	@Test
	public void testSearch() {
		QuizSearcher searcher = new QuizSearcher();
		quizDao.paging(searcher);
	}
}
