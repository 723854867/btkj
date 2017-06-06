package org.btkj.community.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.btkj.community.mybatis.Tx;
import org.btkj.community.mybatis.dao.CommentDao;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.Comment;
import org.btkj.pojo.model.Pager;
import org.rapid.data.storage.mapper.RedisProtostuffDBMapper;
import org.rapid.data.storage.redis.RedisConsts;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.serializer.SerializeUtil;
import org.rapid.util.lang.DateUtils;

public class CommentMapper extends RedisProtostuffDBMapper<Integer, Comment, CommentDao> {
	
	private String LOAD_LOCK					= "lock:comment:{0}";
	private String LIST							= "zset:comment:{0}";			// 评论列表
	
	private Tx tx;

	public CommentMapper() {
		super(BtkjTables.COMMENT, "hash:db:comment");
	}
	
	public Result<Pager<Comment>> comments(int articleId, int page, int pageSize) {
		if (redis.hsetnx(BtkjConsts.HASH_CACHE_CONTROLLER, _loadLockKey(articleId), String.valueOf(DateUtils.currentTime()))) 	// 首次加载
			tx.storeComments(articleId);
		List<byte[]> list = redis.hpaging(
				SerializeUtil.RedisUtil.encode(_listKey(articleId)), 
				SerializeUtil.RedisUtil.encode(redisKey), 
				SerializeUtil.RedisUtil.encode(page), 
				SerializeUtil.RedisUtil.encode(pageSize),
				SerializeUtil.RedisUtil.encode(RedisConsts.OPTION_ZREVRANGE));
		if (null == list)
			return BtkjConsts.RESULT.EMPTY_PAGING;
		int total = Integer.valueOf(new String(list.remove(0)));
		List<Comment> comments = new ArrayList<Comment>();
		for (byte[] data : list)
			comments.add(deserial(data));
		return Result.result(new Pager<Comment>(total, comments));
	}
	
	public void storeComments(int articleId) {
		List<Comment> articles = dao.selectByArticleIdForUpdate(articleId);
		flush(articles);
	}
	
	@Override
	public void flush(List<Comment> entities) {
		redis.hmsetProtostuff(redisKey, entities);
		Map<String, Double> map = new HashMap<String, Double>();
		for (Comment comment : entities)
			map.put(String.valueOf(comment.getId()), Double.valueOf(comment.getCreated()));
		redis.zadd(_listKey(entities.get(0).getArticleId()), map);
	}
	
	@Override
	public void flush(Comment entity) {
		super.flush(entity);
		redis.zadd(_listKey(entity.getArticleId()), entity.getCreated(), String.valueOf(entity.getId()));
	}
	
	public void setTx(Tx tx) {
		this.tx = tx;
	}
	
	private String _listKey(int articleId) {
		return MessageFormat.format(LIST, String.valueOf(articleId));
	}
	
	private String _loadLockKey(int articleId) {
		return MessageFormat.format(LOAD_LOCK, String.valueOf(articleId));
	}
}
