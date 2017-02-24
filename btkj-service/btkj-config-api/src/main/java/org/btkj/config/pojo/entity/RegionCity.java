package org.btkj.config.pojo.entity;

import org.rapid.util.common.db.Entity;

public class RegionCity implements Entity<Integer> {

	private static final long serialVersionUID = 7606322969749601739L;

	private int code;

	@Override
	public Integer key() {
		return code;
	}
}
