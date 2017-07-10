package org.btkj.community.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.community.mybatis.dao.CommentDao;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.Comment;
import org.btkj.pojo.model.Pager;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.data.storage.redis.RedisConsts;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtils;

public class CommentMapper extends RedisDBAdapter<Integer, Comment, CommentDao> {
	
	private String LOAD_LOCK					= "lock:comment:{0}";
	private String TIME_BASED_ZSET				= "zset:comment:{0}";			// 评论列表
	
	public CommentMapper() {
		super(new ByteProtostuffSerializer<Comment>(), "hash:db:comment");
	}
	
	public Result<Pager<Comment>> comments(int articleId, int page, int pageSize) {
		_checkLoad(articleId);
		List<byte[]> list = redis.hpaging(_zsetKey(articleId), redisKey, page, pageSize, RedisConsts.OPTION_ZREVRANGE);
		if (null == list)
			return BtkjConsts.RESULT.EMPTY_PAGING;
		int total = Integer.valueOf(new String(list.remove(0)));
		List<Comment> comments = new ArrayList<Comment>();
		for (byte[] data : list)
			comments.add(serializer.antiConvet(data));
		return Result.result(new Pager<Comment>(total, comments));
	}
	
	public void deleteByArticleId(int articleId) {
		redis.hmzdrop(redisKey, _zsetKey(articleId));
		dao.deleteByArticleId(articleId);
	}
	
	private void _checkLoad(int articleId) {
		if (!redis.hsetnx(BtkjConsts.CACHE_CONTROLLER_KEY, _loadKey(articleId), _loadKey(articleId)))
			return;
		List<Comment> list = dao.getByArticleId(articleId);
		if (CollectionUtils.isEmpty(list))
			return;
		flush(list);
	}
	
	@Override
	public void flush(Comment entity) {
		redis.hmzset(redisKey, entity, _zsetKey(entity.getArticleId()), entity.getCreated(), serializer);
	}
	
	@Override
	public void remove(Comment model) {
		redis.hmzdel(redisKey, model.key(), _zsetKey(model.getArticleId()));
	}
	
	@Override
	public void flush(Collection<Comment> models) {
		Map<Integer, List<Comment>> map = new HashMap<Integer, List<Comment>>();
		for (Comment comment : models) {
			int articleId = comment.getArticleId();
			List<Comment> list = map.get(articleId);
			if (null == list) {
				list = new ArrayList<Comment>();
				map.put(articleId, list);
			}
			list.add(comment);
		}
		
		for (Entry<Integer, List<Comment>> entry : map.entrySet()){
			int article = entry.getKey();
			Comment[] comments = entry.getValue().toArray(new Comment[]{});
			Map<String, double[]> zsetParams = new HashMap<String, double[]>();
			
			//时间排序
			double[] scores = new double[comments.length];
			int idx = 0;
			for (int i = 0, len = comments.length; i < len; i++)
				scores[idx++] = comments[i].getCreated();
			zsetParams.put(_zsetKey(article), scores);
			redis.hmzset(redisKey, comments, zsetParams, serializer);
		}
	}
	
	private String _zsetKey(int articleId) {
		return MessageFormat.format(TIME_BASED_ZSET, String.valueOf(articleId));
	}
	
	private String _loadKey(int articleId) {
		return MessageFormat.format(LOAD_LOCK, String.valueOf(articleId));
	}
}
