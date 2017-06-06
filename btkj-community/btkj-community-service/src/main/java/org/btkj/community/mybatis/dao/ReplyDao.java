package org.btkj.community.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.community.mybatis.provider.ReplySQLProvider;
import org.btkj.pojo.entity.Reply;
import org.rapid.data.storage.db.Dao;

public interface ReplyDao extends Dao<Integer, Reply> {
	
	@Override
	@InsertProvider(type = ReplySQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(Reply entity);

	/**
	 * 总记录条数
	 * 
	 * @param quizId
	 * @return
	 */
	@SelectProvider(type = ReplySQLProvider.class, method = "total")
	int total(int quizId);
	
	/**
	 * 分页列表
	 * 
	 * @param quizId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = ReplySQLProvider.class, method = "paging")
	List<Reply> paging(@Param("quizId") int quizId, @Param("start") int start, @Param("pageSize") int pageSize);
}
