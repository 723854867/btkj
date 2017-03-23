package org.btkj.pojo.entity;

import org.rapid.data.storage.db.Entity;

public class Insurer implements Entity<Integer> {

	private static final long serialVersionUID = -4411281170930448555L;
	
	private int id;

	@Override
	public Integer key() {
		return id;
	}
}
