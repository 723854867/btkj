package org.btkj.pojo.info;

import java.io.Serializable;

/**
 * 分页提交参数
 * 
 * @author ahab
 */
public class Page implements Serializable {

	private static final long serialVersionUID = 8821021500698981890L;
	
	private int page;
	private int pageSize;
	private int start;
	private int total;
	
	public Page() {}
	
	public Page(int page, int pageSize) {
		this.page = page;
		this.pageSize = pageSize;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getStart() {
		return start;
	}
	
	public int getTotal() {
		return total;
	}
	
	public void calculate(int total) {
		this.pageSize = Math.max(1, this.pageSize);    // 至少显示一条数据
		int mod = total % pageSize;
		this.total = 0 == mod ? total / pageSize : (total / pageSize) + 1;
		this.page = Math.max(1, this.page);
		this.page = Math.min(this.total, this.page);
		this.start = (this.page - 1) * pageSize;
	}
}
