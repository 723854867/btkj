package org.btkj.pojo.entity;

import java.io.Serializable;
import java.util.List;

import org.rapid.util.common.model.UniqueModel;

/**
 * 非车险类型
 * 
 * @author ahab
 */
@SuppressWarnings("unused")
public class NonAutoCategory implements UniqueModel<Integer> {

	private static final long serialVersionUID = -6213076468122206339L;
	
	private int id;
	private String name;
	private List<Filter> filters;
	private List<String> sorts;				// 排序字段
	private int created;
	private int updated;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
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
	public Integer key() {
		return this.id;
	}
	
	/**
	 * 筛选条件
	 * 
	 * @author ahab
	 */
	private class Filter implements Serializable {
		private static final long serialVersionUID = -2834589584602207601L;
		private String name;					// 筛选条件的名字
		private List<String> conditions;		// 删选条件的值
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public List<String> getConditions() {
			return conditions;
		}
		public void setConditions(List<String> conditions) {
			this.conditions = conditions;
		}
	}
}
