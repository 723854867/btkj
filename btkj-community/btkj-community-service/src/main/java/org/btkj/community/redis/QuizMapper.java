package org.btkj.community.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.community.mybatis.dao.QuizDao;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.po.Quiz;
import org.btkj.pojo.vo.QuizSearcher;
import org.btkj.pojo.vo.QuizSearcher.SortCol;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;
import org.rapid.util.lang.CollectionUtil;

public class QuizMapper extends RedisDBAdapter<Integer, Quiz, QuizDao> {
	
	private String TIME_BASED_SET					= "zset:quiz:time:{0}";						// 时间的排序列表
	private String REPLY_BASED_SET					= "zset:quiz:reply:{0}";					// 回复数的排序列表
	private String BROWSE_BASED_SET					= "zset:quiz:browse:{0}";					// 浏览数的排序列表
	private String APP_CONTROLLER					= "controller:quiz:{0}";					// 基于平台的缓存控制键

	public QuizMapper() {
		super(new ByteProtostuffSerializer<Quiz>(), "hash:db:quiz");
	}
	
	public Result<Pager<Quiz>> paging(QuizSearcher searcher) {
		_checkLoad(searcher.getAppId());
		List<byte[]> list = redis.hpaging(_setKey(searcher.getAppId(), searcher.getSortCol()), redisKey, searcher.getPage(), searcher.getPageSize(), searcher.redisZSortType());
		if (null == list)
			return BtkjConsts.RESULT.EMPTY_PAGING;
		int total = Integer.valueOf(new String(list.remove(0)));
		List<Quiz> quizs = new ArrayList<Quiz>();
		for (byte[] data : list)
			quizs.add(serializer.antiConvet(data));
		return Result.result(new Pager<Quiz>(total, quizs));
	}
	
	private void _checkLoad(int appId) {
		if (!checkLoad(_appControllerField(appId)))
			return;
		Map<Integer, Quiz> map = dao.getByAppId(appId);
		if (CollectionUtil.isEmpty(map))
			return;
		flush(map);
	}
	
	@Override
	public void flush(Quiz entity) {
		Map<String, Double> scores = new HashMap<String, Double>();
		scores.put(_setKey(entity.getAppId(), SortCol.TIME), Double.valueOf(entity.getCreated()));
		scores.put(_setKey(entity.getAppId(), SortCol.REPLY_NUM), Double.valueOf(entity.getReplyNum()));
		scores.put(_setKey(entity.getAppId(), SortCol.BROWSE_NUM), Double.valueOf(entity.getBrowseNum()));
		redis.hmzset(redisKey, entity, scores, serializer);
	}
	
	@Override
	public void remove(Quiz entity) {
		int appId = entity.getAppId();
		redis.hmzdel(redisKey, entity.key(), _setKey(appId, SortCol.TIME), _setKey(appId, SortCol.REPLY_NUM), _setKey(appId, SortCol.BROWSE_NUM));
	}
	
	@Override
	public void flush(Map<Integer, Quiz> entities) {
		Map<Integer, List<Quiz>> map = new HashMap<Integer, List<Quiz>>();
		for (Quiz quiz : entities.values()) {
			int appId = quiz.getAppId();
			List<Quiz> list = map.get(appId);
			if (null == list) {
				list = new ArrayList<Quiz>();
				map.put(appId, list);
			}
			list.add(quiz);
		}
		
		for (Entry<Integer, List<Quiz>> entry : map.entrySet()){
			int appId = entry.getKey();
			Quiz[] quizs = entry.getValue().toArray(new Quiz[]{});
			Map<String, double[]> zsetParams = new HashMap<String, double[]>();
			
			//时间排序
			double[] scores = new double[quizs.length];
			int idx = 0;
			for (int i = 0, len = quizs.length; i < len; i++)
				scores[idx++] = quizs[i].getCreated();
			zsetParams.put(_setKey(appId, SortCol.TIME), scores);
			
			// 回复数排序
			scores = new double[quizs.length];
			idx = 0;
			for (int i = 0, len = quizs.length; i < len; i++)
				scores[idx++] = quizs[i].getReplyNum();
			zsetParams.put(_setKey(appId, SortCol.REPLY_NUM), scores);
			
			// 浏览数排序
			scores = new double[quizs.length];
			idx = 0;
			for (int i = 0, len = quizs.length; i < len; i++)
				scores[idx++] = quizs[i].getBrowseNum();
			zsetParams.put(_setKey(appId, SortCol.BROWSE_NUM), scores);
			redis.hmzset(redisKey, quizs, zsetParams, serializer);
		}
	}
	
	private String _appControllerField(int appId) {
		return MessageFormat.format(APP_CONTROLLER, String.valueOf(appId));
	}
	
	private String _setKey(int appId, SortCol col) {
		String setKey = null;
		switch (col) {
		case BROWSE_NUM:
			setKey = MessageFormat.format(BROWSE_BASED_SET, String.valueOf(appId));
			break;
		case REPLY_NUM:
			setKey = MessageFormat.format(REPLY_BASED_SET, String.valueOf(appId));
			break;
		default:
			setKey = MessageFormat.format(TIME_BASED_SET, String.valueOf(appId));
			break;
		}
		return setKey;
	}
}
