package org.btkj.community.mybatis.dao;

import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.community.mybatis.provider.QuizSQLProvider;
import org.btkj.pojo.entity.community.Quiz;
import org.rapid.data.storage.mapper.DBMapper;

public interface QuizDao extends DBMapper<Integer, Quiz> {
	
	@Override
	@InsertProvider(type = QuizSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(Quiz entity);
	
	@Override
	@SelectProvider(type = QuizSQLProvider.class, method = "getByKey")
	Quiz getByKey(Integer key);
	
	@Override
	@SelectProvider(type = QuizSQLProvider.class, method = "update")
	void update(Quiz entity);
	
	@MapKey("id")
	@SelectProvider(type = QuizSQLProvider.class, method = "getByAppId")
	Map<Integer, Quiz> getByAppId(int appId);

	@Override
	@DeleteProvider(type = QuizSQLProvider.class, method = "delete")
	void delete(Integer key);
}
