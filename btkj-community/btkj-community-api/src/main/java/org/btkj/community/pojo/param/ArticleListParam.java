package org.btkj.community.pojo.param;

import javax.validation.constraints.NotNull;

import org.btkj.pojo.param.Param;
import org.rapid.util.common.Consts;

public class ArticleListParam extends Param {

	private static final long serialVersionUID = -5059825156861391370L;

	private int appId;
	private boolean asc;
	@NotNull
	private SortCol sortCol;
	
	public int getAppId() {
		return appId;
	}
	
	public void setAppId(int appId) {
		this.appId = appId;
	}
	
	public boolean isAsc() {
		return asc;
	}
	
	public void setAsc(boolean asc) {
		this.asc = asc;
	}
	
	public SortCol getSortCol() {
		return sortCol;
	}
	
	public void setSortCol(SortCol sortCol) {
		this.sortCol = sortCol;
	}
	
	public String redisZSortType() {
		return asc ? Consts.REDIS.OPTION_ZRANGE : Consts.REDIS.OPTION_ZREVRANGE;
	}
	
	public enum SortCol {
		TIME,
		BROWSE_NUM,
		COMMENT_NUM;
	}
}
