package org.btkj.manager.pojo.param;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.param.Param;

public class ArticleAddParam extends Param {

	private static final long serialVersionUID = 593539536614465200L;

	@NotNull
	@Size(min = BtkjConsts.LIMITS.ARTICLE_TITLE_MIN, max = BtkjConsts.LIMITS.ARTICLE_TITLE_MAX)
	private String title;						// 咨询标题
	@NotNull
	@Size(min = BtkjConsts.LIMITS.URL_MIN, max = BtkjConsts.LIMITS.URL_MAX)					
	private String icon;						// 图标地址
	@NotNull
	@Size(min = BtkjConsts.LIMITS.URL_MIN, max = BtkjConsts.LIMITS.URL_MAX)					
	private String link;						// 图标地址

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
