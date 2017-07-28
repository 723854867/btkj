package org.btkj.nonauto.pojo.param;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.param.Param;
import org.btkj.pojo.po.NonAutoCategory;
import org.rapid.util.lang.DateUtil;

public class NonAutoEditParam extends Param {

	private static final long serialVersionUID = 5797837742620704707L;

	@Min(value = 0)
	private long id;
	@NotNull
	@Size(min = BtkjConsts.LIMITS.NAME_MIN, max = BtkjConsts.LIMITS.NAME_MAX)
	private String name;				// 图标地址
	@NotNull
	@Size(min = BtkjConsts.LIMITS.URL_MIN, max = BtkjConsts.LIMITS.URL_MAX)	
	private String icon;
	@Valid
	@Size(max = BtkjConsts.LIMITS.NONAUTO_FILTER_MAX)
	private List<Filter> filters;
	@Size(max = BtkjConsts.LIMITS.NONAUTO_SORT_MAX)
	private List<String> sorts;				// 排序字段
	@Size(max = BtkjConsts.LIMITS.NONAUTO_TAG_MAX)
	private List<String> tags;				// 标签
						
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Filter> getFilters() {
		return filters;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

	public List<String> getSorts() {
		return sorts;
	}

	public void setSorts(List<String> sorts) {
		this.sorts = sorts;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public NonAutoCategory entity() {
		NonAutoCategory category = new NonAutoCategory();
		category.set_id(id);
		category.setName(name);
		category.setIcon(icon);
		category.setTags(tags);
		category.setSorts(sorts);
		if (null != this.filters) {
			List<org.btkj.pojo.po.NonAutoCategory.Filter> list = new ArrayList<org.btkj.pojo.po.NonAutoCategory.Filter>();
			for (Filter filter : this.filters) 
				list.add(new org.btkj.pojo.po.NonAutoCategory.Filter(filter.name, filter.options));
			category.setFilters(list);
		}
		int time = DateUtil.currentTime();
		if (0 == this.id)
			category.setCreated(time);
		category.setUpdated(time);
		return category;
	}

	private class Filter implements Serializable {
		private static final long serialVersionUID = -7458367072405373134L;
		@NotNull
		@Size(min = BtkjConsts.LIMITS.NAME_MIN, max = BtkjConsts.LIMITS.NAME_MAX)
		private String name;					// 筛选条件的名字
		@NotNull
		@Size(min = BtkjConsts.LIMITS.NONAUTO_FILTER_OPTION_MIN, max = BtkjConsts.LIMITS.NONAUTO_FILTER_OPTION_MAX)
		private List<String> options;			// 删选条件的值
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public List<String> getOptions() {
			return options;
		}
		public void setOptions(List<String> options) {
			this.options = options;
		}
	}
}
