package org.btkj.pojo.info;

import org.rapid.data.storage.redis.RedisConsts;
import org.rapid.util.common.enums.SORT_TYPE;

public class QuizSearcher extends Page {

	private static final long serialVersionUID = -4663590321300215965L;

	private Integer appId;
	private SortCol sortCol;
	private SORT_TYPE sortType;
	
	public Integer getAppId() {
		return appId;
	}
	
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	
	public SortCol getSortCol() {
		return sortCol;
	}
	
	public void setSortCol(SortCol sortCol) {
		this.sortCol = sortCol;
	}
	
	public SORT_TYPE getSortType() {
		return sortType;
	}
	
	public void setSortType(SORT_TYPE sortType) {
		this.sortType = sortType;
	}
	
	public String redisZSortType() {
		return null == sortType ? RedisConsts.OPTION_ZREVRANGE : sortType == SORT_TYPE.ASC ? RedisConsts.OPTION_ZRANGE : RedisConsts.OPTION_ZREVRANGE;
	}
	
	public enum SortCol {
		TIME,
		REPLY_NUM,
		BROWSE_NUM;
	}
}
