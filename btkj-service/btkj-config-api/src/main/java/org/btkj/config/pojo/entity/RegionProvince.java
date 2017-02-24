package org.btkj.config.pojo.entity;

import org.rapid.util.common.db.Entity;

public class RegionProvince implements Entity<Integer> {

	private static final long serialVersionUID = 1412847904283796446L;
	
	private int code;

	@Override
	public Integer key() {
		return code;
	}
}
