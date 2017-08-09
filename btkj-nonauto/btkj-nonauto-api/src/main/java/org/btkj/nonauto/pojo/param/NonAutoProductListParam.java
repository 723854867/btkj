package org.btkj.nonauto.pojo.param;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.enums.SortField;
import org.btkj.pojo.param.Param;

public class NonAutoProductListParam extends Param {

	private static final long serialVersionUID = -505643458672915703L;

	@Min(0)
	private int cid;								// 险种类型
	@NotNull
	private SortField sort;							// 排序
	private boolean desc;							// 是否是将序，默认是false
	@Size(max = BtkjConsts.LIMITS.NONAUTO_TAG_MAX)
	private List<String> tags;						//　标签
	@Size(max = BtkjConsts.LIMITS.NONAUTO_FILTER_MAX)
	private Map<String, String> filters;			// 筛选
	
	public int getCid() {
		return cid;
	}
	
	public void setCid(int cid) {
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
