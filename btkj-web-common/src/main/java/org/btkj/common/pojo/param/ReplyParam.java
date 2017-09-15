package org.btkj.common.pojo.param;

import javax.validation.constraints.NotNull;

import org.btkj.pojo.param.IdParam;

public class ReplyParam extends IdParam {

	private static final long serialVersionUID = -2818779557816179138L;

	@NotNull
	private String content;
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
