package org.btkj.community.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.community.mybatis.dao.ArticleDao;
import org.btkj.community.pojo.param.ArticleListParam;
import org.btkj.community.pojo.param.ArticleListParam.SortCol;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.po.Article;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtil;

public class ArticleMapper extends RedisDBAdapter<Integer, Article, ArticleDao> {
	
	private String TIME_BASED_SET					= "zset:article:time:{0}";					// 基于时间的排序列表
	private String BROWSE_NUM_BASED_SET				= "zset:article:browse:num:{0}";			// 基于浏览数的排序列表
	private String COMMENT_NUM_BASED_SET			= "zset:article:comment:num:{0}";			// 基于评论数的排序列表
	private String APP_CONTROLLER					= "controller:article:{0}";					// 基于 appId 的缓存列表
	
	public ArticleMapper() {
		super(new ByteProtostuffSerializer<Article>(), "hash:db:article");
	}
	
	public Result<Pager<Article>> paging(ArticleListParam param) {
		_checkLoad(param.getAppId());
		List<byte[]> list = redis.hpaging(_zsetKey(param.getAppId(), param.getSortCol()), redisKey, param.getPage(), param.getPageSize(), param.redisZSortType());
		if (null == list)
			return BtkjConsts.RESULT.EMPTY_PAGING;
		int total = Integer.valueOf(new String(list.remove(0)));
		List<Article> articles = new ArrayList<Article>();
		for (byte[] data : list)
			articles.add(serializer.antiConvet(data));
		return Result.result(new Pager<Article>(total, articles));
	}
	
	private void _checkLoad(int appId) {
		if (!checkLoad(_appControllerField(appId)))
			return;
		Map<Integer, Article> map = dao.getByAppId(appId);
		if (CollectionUtil.isEmpty(map))
			return;
		flush(map);
	} 
	
	@Override
	public void flush(Article entity) {
		Map<String, Double> scores = new HashMap<String, Double>();
		scores.put(_zsetKey(entity.getAppId(), SortCol.TIME), Double.valueOf(entity.getCreated()));
		scores.put(_zsetKey(entity.getAppId(), SortCol.BROWSE_NUM), Double.valueOf(entity.getBrowseNum()));
		scores.put(_zsetKey(entity.getAppId(), SortCol.COMMENT_NUM), Double.valueOf(entity.getCommentNum()));
		redis.hmzset(redisKey, entity, scores, serializer);
	}
	
	@Override
	public void remove(Article entity) {
		int appId = entity.getAppId();
		redis.hmzdel(redisKey, entity.key(), _zsetKey(appId, SortCol.TIME), _zsetKey(appId, SortCol.BROWSE_NUM), _zsetKey(appId, SortCol.COMMENT_NUM));
	}
	
	@Override
	public void flush(Map<Integer, Article> entities) {
		Map<Integer, List<Article>> map = new HashMap<Integer, List<Article>>();
		for (Article article : entities.values()) {
			List<Article> list = map.get(article.getAppId());
			if (null == list) {
				list = new ArrayList<Article>();
				map.put(article.getAppId(), list);
			}
			list.add(article);
		}
		
		for (Entry<Integer, List<Article>> entry : map.entrySet()){
			int appId = entry.getKey();
			Article[] articles = entry.getValue().toArray(new Article[]{});
			Map<String, double[]> zsetParams = new HashMap<String, double[]>();
			
			//时间排序
			double[] scores = new double[articles.length];
			int idx = 0;
			for (int i = 0, len = articles.length; i < len; i++)
				scores[idx++] = articles[i].getCreated();
			zsetParams.put(_zsetKey(appId, SortCol.TIME), scores);
			
			// 回复数排序
			scores = new double[articles.length];
			idx = 0;
			for (int i = 0, len = articles.length; i < len; i++)
				scores[idx++] = articles[i].getBrowseNum();
			zsetParams.put(_zsetKey(appId, SortCol.BROWSE_NUM), scores);
			
			// 浏览数排序
			scores = new double[articles.length];
			idx = 0;
			for (int i = 0, len = articles.length; i < len; i++)
				scores[idx++] = articles[i].getCommentNum();
			zsetParams.put(_zsetKey(appId, SortCol.COMMENT_NUM), scores);
			redis.hmzset(redisKey, articles, zsetParams, serializer);
		}
	}
	
	private String _appControllerField(int appId) {
		return MessageFormat.format(APP_CONTROLLER, String.valueOf(appId));
	}
	
	private String _zsetKey(int appId, SortCol col) {
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
