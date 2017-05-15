package org.btkj.pojo.submit;

import java.util.Map;

public class NonAutoProductSearcher extends Page {

	private static final long serialVersionUID = -5942932537451808183L;

	private String sort;							// 排序
	private int sortType;							// 排序类型
	private Map<String, String> filters;			// 筛选
	
	public String getSort() {
		return sort;
	}
	
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public int getSortType() {
		return sortType;
	}
	
	public void setSortType(int sortType) {
		this.sortType = sortType;
	}
	
	public Map<String, String> getFilters() {
		return filters;
	}
	
	public void setFilters(Map<String, String> filters) {
		this.filters = filters;
	}
}
