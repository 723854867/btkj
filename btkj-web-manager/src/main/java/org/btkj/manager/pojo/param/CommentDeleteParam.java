package org.btkj.manager.pojo.param;

import javax.validation.constraints.Min;

import org.btkj.pojo.param.Param;

public class CommentDeleteParam extends Param {

	private static final long serialVersionUID = -3480699354380178429L;

	@Min(1)
	private int id;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
