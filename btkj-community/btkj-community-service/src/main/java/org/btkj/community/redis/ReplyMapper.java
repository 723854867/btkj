package org.btkj.community.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.community.mybatis.dao.ReplyDao;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.po.Reply;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.data.storage.redis.RedisConsts;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtil;

public class ReplyMapper extends RedisDBAdapter<Integer, Reply, ReplyDao> {
	
	private String TIME_ZSET					= "zset:reply:time:{0}";						// 基于时间的排序列表
	private String QUIZ_CONTROLLER				= "controller:reply:{0}";						// 基于提问的缓存控制键

	public ReplyMapper() {
		super(new ByteProtostuffSerializer<Reply>(), "hash:db:reply");
	}
	
	public Result<Pager<Reply>> paging(int quizId, int page, int pageSize) {
		_checkLoad(quizId);
		List<byte[]> list = redis.hpaging(_timeZsetKey(quizId), redisKey, page, pageSize, RedisConsts.OPTION_ZREVRANGE);
		if (null == list)
			return BtkjConsts.RESULT.EMPTY_PAGING;
		int total = Integer.valueOf(new String(list.remove(0)));
		List<Reply> replies = new ArrayList<Reply>();
		for (byte[] data : list)
			replies.add(serializer.antiConvet(data));
		return Result.result(new Pager<Reply>(total, replies));
	}
	
	public void deleteByQuizId(int quizId) {
		redis.hmzdrop(redisKey, _timeZsetKey(quizId));
		dao.deleteByQuizId(quizId);
	}
	
	private void _checkLoad(int quizId) {
		if (!checkLoad(_quizControllerField(quizId)))
			return;
		Map<Integer, Reply> map = dao.getByQuizId(quizId);
		if (CollectionUtil.isEmpty(map))
			return;
		flush(map);
	}
	
	@Override
	public void flush(Reply entity) {
		redis.hmzset(redisKey, entity, _timeZsetKey(entity.getQuizId()), entity.getCreated(), serializer);
	}
	
	@Override
	public void remove(Reply entity) {
		redis.hmzdel(redisKey, entity.key(), _timeZsetKey(entity.getQuizId()));
	}
	
	@Override
	public void flush(Map<Integer, Reply> entities) {
		Map<Integer, List<Reply>> map = new HashMap<Integer, List<Reply>>();
		for (Reply reply : entities.values()) {
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
			double[] scores = new double[replies.length];
			int idx = 0;
			for (int i = 0, len = replies.length; i < len; i++)
				scores[idx++] = replies[i].getCreated();
			zsetParams.put(_timeZsetKey(quizId), scores);
			redis.hmzset(redisKey, replies, zsetParams, serializer);
		}
	}
	
	private String _timeZsetKey(int quizId) {
		return MessageFormat.format(TIME_ZSET, String.valueOf(quizId));
	}
	
	private String _quizControllerField(int quizId) {
		return MessageFormat.format(QUIZ_CONTROLLER, String.valueOf(quizId));
	}
}
