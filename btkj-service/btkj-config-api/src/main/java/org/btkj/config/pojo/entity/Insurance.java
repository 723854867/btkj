package org.btkj.config.pojo.entity;

import org.rapid.util.common.db.Entity;

public class Insurance implements Entity<Integer> {

	private static final long serialVersionUID = -4411281170930448555L;
	
	private int id;

	@Override
	public Integer key() {
		return id;
	}
}
