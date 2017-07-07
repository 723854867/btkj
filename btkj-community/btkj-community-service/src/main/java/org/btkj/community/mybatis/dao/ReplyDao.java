package org.btkj.community.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.community.mybatis.provider.ReplySQLProvider;
import org.btkj.pojo.entity.Reply;
import org.rapid.data.storage.mapper.DBMapper;

public interface ReplyDao extends DBMapper<Integer, Reply> {
	
	@Override
	@InsertProvider(type = ReplySQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(Reply entity);
	
	@SelectProvider(type = ReplySQLProvider.class, method = "getByQuizId")
	List<Reply> getByQuizId(int quizId);

	@DeleteProvider(type = ReplySQLProvider.class, method = "deleteByQuizId")
	void deleteByQuizId(int quizId);
}
