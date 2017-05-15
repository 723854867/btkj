package org.btkj.pojo.submit;

import java.util.List;
import java.util.Map;

import org.btkj.pojo.enums.SortField;

public class NonAutoProductSearcher extends Page {

	private static final long serialVersionUID = -5942932537451808183L;

	private Integer cid;							// 险种类型
	private SortField sort;							// 排序
	private boolean desc;							// 是否是将序，默认是false
	private List<String> tags;						//　标签
	private Map<String, String> filters;			// 筛选
	
	public Integer getCid() {
		return cid;
	}
	
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	public SortField getSort() {
		return sort;
	}
	
	public void setSort(SortField sort) {
		this.sort = sort;
	}
	
	public boolean isDesc() {
		return desc;
	}
	
	public void setDesc(boolean desc) {
		this.desc = desc;
	}
	
	public List<String> getTags() {
		return tags;
	}
	
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	public Map<String, String> getFilters() {
		return filters;
	}
	
	public void setFilters(Map<String, String> filters) {
		this.filters = filters;
	}
}
