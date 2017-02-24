package org.btkj.config.pojo.entity;

import org.rapid.util.common.db.Entity;

public class RegionDistrict implements Entity<Integer> {

	private static final long serialVersionUID = -7020485128921252826L;
	
	private int code;

	@Override
	public Integer key() {
		return code;
	}
}
