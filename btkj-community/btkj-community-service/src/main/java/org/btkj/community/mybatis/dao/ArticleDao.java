package org.btkj.community.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.community.mybatis.provider.ArticleSQLProvider;
import org.btkj.pojo.entity.Article;
import org.rapid.data.storage.db.Dao;

public interface ArticleDao extends Dao<Integer, Article> {
	
	@Override
	@SelectProvider(type = ArticleSQLProvider.class, method = "selectByKey")
	Article selectByKey(Integer key);
	
	@SelectProvider(type = ArticleSQLProvider.class, method = "selectByAppIdForUpdate")
	List<Article> selectByAppIdForUpdate(int appId);
	
	@Override
	@SelectProvider(type = ArticleSQLProvider.class, method = "selectAll")
	List<Article> selectAll();
	
	@Override
	@SelectProvider(type = ArticleSQLProvider.class, method = "update")
	void update(Article entity);
}
