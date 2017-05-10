package org.btkj.pojo.entity;

import java.io.Serializable;
import java.util.List;

import org.rapid.util.common.model.UniqueModel;

/**
 * 非车险类型
 * 
 * @author ahab
 */
public class NonAutoCategory implements UniqueModel<Long> {

	private static final long serialVersionUID = -6213076468122206339L;
	
	private long _id;
	private String name;
	private List<Filter> filters;
	private List<String> sorts;				// 排序字段
	private List<String> tages;				// 标签
	private int created;
	private int updated;
	
	public long get_id() {
		return _id;
	}
	
	public void set_id(long _id) {
		this._id = _id;
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
	
	public List<String> getTages() {
		return tages;
	}
	
	public void setTages(List<String> tages) {
		this.tages = tages;
	}
	
	public int getCreated() {
		return created;
	}
	
	public void setCreated(int created) {
		this.created = created;
	}
	
	public int getUpdated() {
		return updated;
	}
	
	public void setUpdated(int updated) {
		this.updated = updated;
	}

	@Override
	public Long key() {
		return this._id;
	}
	
	/**
	 * 筛选条件
	 * 
	 * @author ahab
	 */
	public static class Filter implements Serializable {
		private static final long serialVersionUID = -2834589584602207601L;
		private String name;					// 筛选条件的名字
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
