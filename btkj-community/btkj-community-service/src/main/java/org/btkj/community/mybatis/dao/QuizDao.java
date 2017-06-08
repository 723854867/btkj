package org.btkj.community.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.community.mybatis.provider.QuizSQLProvider;
import org.btkj.pojo.entity.Quiz;
import org.btkj.pojo.submit.QuizSearcher;
import org.rapid.data.storage.db.Dao;

public interface QuizDao extends Dao<Integer, Quiz> {
	
	@Override
	@InsertProvider(type = QuizSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(Quiz entity);
	
	@Override
	@SelectProvider(type = QuizSQLProvider.class, method = "selectByKey")
	Quiz selectByKey(Integer key);
	
	@Override
	@SelectProvider(type = QuizSQLProvider.class, method = "update")
	void update(Quiz entity);

	/**
	 * 获取符合条件的记录条数
	 * 
	 * @param searcher
	 * @return
	 */
	@SelectProvider(type = QuizSQLProvider.class, method = "total")
	int total(QuizSearcher searcher);
	
	/**
	 * 获取符合条件的记录列表
	 * 
	 * @param searcher
	 * @return
	 */
	@SelectProvider(type = QuizSQLProvider.class, method = "paging")
	List<Quiz> paging(QuizSearcher searcher);
}
