package org.btkj.pojo.info;

import java.io.Serializable;

/**
 * 非车险首页列表信息
 * 
 * @author ahab
 *
 */
public class InsuranceNonAutoTipsInfo implements Serializable {

	private static final long serialVersionUID = 1732635911364746139L;

	private String name; 				// 险种名
	private String icon; 				// 险种图标地址

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
