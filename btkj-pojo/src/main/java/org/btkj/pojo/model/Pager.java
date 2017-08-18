package org.btkj.pojo.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页信息
 * 
 * @author ahab
 */
public class Pager<T> implements Serializable {

	private static final long serialVersionUID = 1530641020396359356L;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Pager EMPLTY				= new Pager(0, Collections.EMPTY_LIST);
	
	private long total;
	private List<T> list;
	
	public Pager() {}
	
	public Pager(long total, List<T> list) {
		this.total = total;
		this.list = list;
	}
	
	public long getTotal() {
		return total;
	}
	
	public void setTotal(long total) {
		this.total = total;
	}
	
	public List<T> getList() {
		return list;
	}
	
	public void setList(List<T> list) {
		this.list = list;
	}
}
