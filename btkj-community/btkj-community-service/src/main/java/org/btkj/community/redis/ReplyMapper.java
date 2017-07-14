package org.btkj.community.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.community.mybatis.dao.ReplyDao;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.Reply;
import org.btkj.pojo.model.Pager;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.data.storage.redis.RedisConsts;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtil;

public class ReplyMapper extends RedisDBAdapter<Integer, Reply, ReplyDao> {
	
	private String LOAD_LOCK						= "lock:reply:{0}";							
	private String TIME_BASED_SET					= "zset:reply:time:{0}";						// 基于时间的排序列表

	public ReplyMapper() {
		super(new ByteProtostuffSerializer<Reply>(), "hash:db:reply");
	}
	
	public Result<Pager<Reply>> paging(int quizId, int page, int pageSize) {
		_checkLoad(quizId);
		List<byte[]> list = redis.hpaging(_setKey(quizId), redisKey, page, pageSize, RedisConsts.OPTION_ZREVRANGE);
		if (null == list)
			return BtkjConsts.RESULT.EMPTY_PAGING;
		int total = Integer.valueOf(new String(list.remove(0)));
		List<Reply> replies = new ArrayList<Reply>();
		for (byte[] data : list)
			replies.add(serializer.antiConvet(data));
		return Result.result(new Pager<Reply>(total, replies));
	}
	
	public void deleteByQuizId(int quizId) {
		redis.hmzdrop(redisKey, _setKey(quizId));
		dao.deleteByQuizId(quizId);
	}
	
	private void _checkLoad(int quizId) {
		if (!redis.hsetnx(BtkjConsts.CACHE_CONTROLLER_KEY, _loadKey(quizId), _loadKey(quizId)))
			return;
		List<Reply> quizs = dao.getByQuizId(quizId);
		if (CollectionUtil.isEmpty(quizs))
			return;
		flush(quizs);
	}
	
	@Override
	public void flush(Reply model) {
		redis.hmzset(redisKey, model, _setKey(model.getQuizId()), model.getCreated(), serializer);
	}
	
	@Override
	public void remove(Reply model) {
		redis.hmzdel(redisKey, model.key(), _setKey(model.getQuizId()));
	}
	
	@Override
	public void flush(Collection<Reply> models) {
		Map<Integer, List<Reply>> map = new HashMap<Integer, List<Reply>>();
		for (Reply reply : models) {
			int quizId = reply.getQuizId();
			List<Reply> list = map.get(quizId);
			if (null == list) {
				list = new ArrayList<Reply>();
				map.put(quizId, list);
			}
			list.add(reply);
		}
		
		for (Entry<Integer, List<Reply>> entry : map.entrySet()){
			int quizId = entry.getKey();
			Reply[] replies = entry.getValue().toArray(new Reply[]{});
			Map<String, double[]> zsetParams = new HashMap<String, double[]>();
			
			//时间排序
			double[] scores = new double[replies.length];
			int idx = 0;
			for (int i = 0, len = replies.length; i < len; i++)
				scores[idx++] = replies[i].getCreated();
			zsetParams.put(_setKey(quizId), scores);
			redis.hmzset(redisKey, replies, zsetParams, serializer);
		}
	}
	
	private String _setKey(int quizId) {
		return MessageFormat.format(TIME_BASED_SET, String.valueOf(quizId));
	}
	
	private String _loadKey(int quizId) {
		return MessageFormat.format(LOAD_LOCK, String.valueOf(quizId));
	}
}
