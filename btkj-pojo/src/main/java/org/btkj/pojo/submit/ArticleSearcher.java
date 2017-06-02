package org.btkj.pojo.submit;

import org.btkj.pojo.enums.SortType;

public class ArticleSearcher extends Page {

	private static final long serialVersionUID = 8449289745559916447L;
	
	private SortCol sortCol;
	private SortType sortType;
	
	public SortCol getSortCol() {
		return sortCol;
	}
	
	public void setSortCol(SortCol sortCol) {
		this.sortCol = sortCol;
	}
	
	public SortType getSortType() {
		return sortType;
	}
	
	public void setSortType(SortType sortType) {
		this.sortType = sortType;
	}

	public enum SortCol {
		TIME,
		BROWSE_NUM,
		COMMENT_NUM;
	}
}
