package org.btkj.community.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.community.mybatis.dao.CommentDao;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.po.Comment;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.data.storage.redis.RedisConsts;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtil;

public class CommentMapper extends RedisDBAdapter<Integer, Comment, CommentDao> {
	
	private String TIME_ZSET					= "zset:comment:time:{0}";		// 时间排序评论列表
	private String ARTICLE_CONTROLLER			= "controller:comment:{0}";		// 基于咨询的缓存列表

	public CommentMapper() {
		super(new ByteProtostuffSerializer<Comment>(), "hash:db:comment");
	}
	
	public Result<Pager<Comment>> comments(int articleId, int page, int pageSize) {
		_checkLoad(articleId);
		List<byte[]> list = redis.hpaging(_timeZsetKey(articleId), redisKey, page, pageSize, RedisConsts.OPTION_ZREVRANGE);
		if (null == list)
			return BtkjConsts.RESULT.EMPTY_PAGING;
		int total = Integer.valueOf(new String(list.remove(0)));
		List<Comment> comments = new ArrayList<Comment>();
		for (byte[] data : list)
			comments.add(serializer.antiConvet(data));
		return Result.result(new Pager<Comment>(total, comments));
	}
	
	public void deleteByArticleId(int articleId) {
		redis.hmzdrop(redisKey, _timeZsetKey(articleId));
		dao.deleteByArticleId(articleId);
	}
	
	private void _checkLoad(int articleId) {
		if (!checkLoad(_articleControllerField(articleId)))
			return;
		Map<Integer, Comment> map = dao.getByArticleId(articleId);
		if (CollectionUtil.isEmpty(map))
			return;
		flush(map);
	}
	
	@Override
	public void flush(Comment entity) {
		redis.hmzset(redisKey, entity, _timeZsetKey(entity.getArticleId()), entity.getCreated(), serializer);
	}
	
	@Override
	public void remove(Comment entity) {
		redis.hmzdel(redisKey, entity.key(), _timeZsetKey(entity.getArticleId()));
	}
	
	@Override
	public void flush(Map<Integer, Comment> models) {
		Map<Integer, List<Comment>> map = new HashMap<Integer, List<Comment>>();
		for (Comment comment : models.values()) {
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
			double[] scores = new double[comments.length];
			int idx = 0;
			for (int i = 0, len = comments.length; i < len; i++)
				scores[idx++] = comments[i].getCreated();
			zsetParams.put(_timeZsetKey(article), scores);
			redis.hmzset(redisKey, comments, zsetParams, serializer);
		}
	}
	
	private String _timeZsetKey(int articleId) {
		return MessageFormat.format(TIME_ZSET, String.valueOf(articleId));
	}
	
	private String _articleControllerField(int articleId) {
		return MessageFormat.format(ARTICLE_CONTROLLER, String.valueOf(articleId));
	}
}
