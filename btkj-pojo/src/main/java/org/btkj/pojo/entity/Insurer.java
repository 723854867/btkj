package org.btkj.pojo.entity;

import org.rapid.util.common.model.UniqueModel;

public class Insurer implements UniqueModel<Integer> {

	private static final long serialVersionUID = -4411281170930448555L;
	
	private int id;

	@Override
	public Integer key() {
		return id;
	}
}
