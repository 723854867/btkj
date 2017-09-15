package org.btkj.common.pojo.param;

import javax.validation.constraints.NotNull;

import org.btkj.pojo.param.Param;

public class QuizParam extends Param {

	private static final long serialVersionUID = -1372468493055183544L;

	@NotNull
	private String content;
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
