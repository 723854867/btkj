package org.btkj.community.mybatis.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.community.mybatis.provider.CommentSQLProvider;
import org.btkj.pojo.entity.Comment;
import org.rapid.data.storage.db.Dao;

public interface CommentDao extends Dao<Integer, Comment> {
	
	@Override
	@InsertProvider(type = CommentSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(Comment entity);

	@Resource
	@SelectProvider(type = CommentSQLProvider.class, method = "selectByArticleIdForUpdate")
	List<Comment> selectByArticleIdForUpdate(int articleId);
}
