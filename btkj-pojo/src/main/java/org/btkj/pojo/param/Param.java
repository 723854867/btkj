package org.btkj.pojo.param;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Request message 请求参数
 * 
 * @author ahab
 */
public class Param implements Serializable {

	private static final long serialVersionUID = -5703472892280596023L;
	
	@Min(1)
	private int page = 1;
	@Min(1)
	@Max(50)
	private int pageSize = 10;
	private int start;
	private int total;
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	public int getStart() {
		return start;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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
