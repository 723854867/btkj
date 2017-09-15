package org.btkj.common.pojo.param;

import javax.validation.constraints.NotNull;

import org.btkj.pojo.param.IdParam;

public class CommentParam extends IdParam {

	private static final long serialVersionUID = -6023462763157852354L;

	@NotNull
	private String content;

	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
