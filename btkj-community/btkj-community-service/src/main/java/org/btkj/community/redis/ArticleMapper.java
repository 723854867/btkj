package org.btkj.community.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.community.LuaCmd;
import org.btkj.community.mybatis.dao.ArticleDao;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.Article;
import org.btkj.pojo.enums.SortType;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.ArticleSearcher;
import org.btkj.pojo.submit.ArticleSearcher.SortCol;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.data.storage.redis.RedisConsts;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.serializer.SerializeUtil;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class ArticleMapper extends RedisDBAdapter<Integer, Article, ArticleDao> {
	
	private String LOAD_LOCK						= "lock:article";							// 咨询加载控制键
	private String TIME_BASED_SET					= "zset:article:time:{0}";					// 基于时间的排序列表
	private String BROWSE_NUM_BASED_SET				= "zset:article:browse:num:{0}";			// 基于浏览数的排序列表
	private String COMMENT_NUM_BASED_SET			= "zset:article:comment:num:{0}";			// 基于评论数的排序列表
	
	public ArticleMapper() {
		super(new ByteProtostuffSerializer<Article>(), "hash:db:article");
	}
	
	public void init() {
		if (!redis.hsetnx(BtkjConsts.CACHE_CONTROLLER_KEY, LOAD_LOCK, LOAD_LOCK))
			return;
		List<Article> list = dao.getAll();
		if (list.isEmpty())
			return;
		flush(list);
		Map<Integer, List<Article>> map = new HashMap<Integer, List<Article>>();
		for (Article article : list) {
			list = map.get(article.getAppId());
			if (null == list) {
				list = new ArrayList<Article>();
				map.put(article.getAppId(), list);
			}
			list.add(article);
		}
		for (Entry<Integer, List<Article>> entry : map.entrySet()) {
			int appId = entry.getKey();
			List<Article> articles = entry.getValue();
			Map<String, Double> timeMap = new HashMap<String, Double>();
			Map<String, Double> browseMap = new HashMap<String, Double>();
			Map<String, Double> commentMap = new HashMap<String, Double>();
			for (Article article : articles) {
				String id = String.valueOf(article.getId());
				timeMap.put(id, Double.valueOf(article.getCreated()));
				browseMap.put(id, Double.valueOf(article.getBrowseNum()));
				commentMap.put(id, Double.valueOf(article.getCommentNum()));
			}
			redis.zadd(_setKey(appId, SortCol.TIME), timeMap);
			redis.zadd(_setKey(appId, SortCol.BROWSE_NUM), browseMap);
			redis.zadd(_setKey(appId, SortCol.COMMENT_NUM), commentMap);
		}
	}
	
	public Result<Pager<Article>> articles(int appId, ArticleSearcher searcher) {
		if (null == searcher.getSortCol())
			return BtkjConsts.RESULT.EMPTY_PAGING;
		List<byte[]> list = redis.hpaging(
				SerializeUtil.RedisUtil.encode(_setKey(appId, searcher.getSortCol())), 
				SerializeUtil.RedisUtil.encode(redisKey), 
				SerializeUtil.RedisUtil.encode(searcher.getPage()), 
				SerializeUtil.RedisUtil.encode(searcher.getPageSize()),
				SerializeUtil.RedisUtil.encode(null == searcher.getSortType() ? RedisConsts.OPTION_ZREVRANGE 
						: searcher.getSortType() == SortType.ASC ? RedisConsts.OPTION_ZRANGE : RedisConsts.OPTION_ZREVRANGE));
		if (null == list)
			return BtkjConsts.RESULT.EMPTY_PAGING;
		int total = Integer.valueOf(new String(list.remove(0)));
		List<Article> articles = new ArrayList<Article>();
		for (byte[] data : list)
			articles.add(serializer.antiConvet(data));
		return Result.result(new Pager<Article>(total, articles));
	}
	
	@Override
	public void flush(Article entity) {
		redis.invokeLua(LuaCmd.FLUSH_ARTICLE, redisKey, _setKey(entity.getAppId(), SortCol.BROWSE_NUM),
				_setKey(entity.getAppId(), SortCol.COMMENT_NUM), _setKey(entity.getAppId(), SortCol.TIME), entity.getId(),
				serializer.convert(entity), entity.getCreated());
	}
	
	private String _setKey(int appId, SortCol col) {
		String setKey = null;
		switch (col) {
		case BROWSE_NUM:
			setKey = MessageFormat.format(BROWSE_NUM_BASED_SET, String.valueOf(appId));
			break;
		case COMMENT_NUM:
			setKey = MessageFormat.format(COMMENT_NUM_BASED_SET, String.valueOf(appId));
			break;
		default:
			setKey = MessageFormat.format(TIME_BASED_SET, String.valueOf(appId));
			break;
		}
		return setKey;
	}
}
