package org.btkj.user.pojo.param;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.btkj.pojo.param.Param;
import org.rapid.util.validator.ValidateGroups;

public class BannerEditParam extends Param {

	private static final long serialVersionUID = -2288754924621132852L;

	@Min(value = 1, groups = { ValidateGroups.UPDATE.class, ValidateGroups.DELETE.class })
	private int id;
	private int appId;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	@Min(value = 1, groups = { ValidateGroups.CRUD.class })
	private Integer tid;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	@Min(value = 1, groups = { ValidateGroups.CRUD.class })
	@Max(value = 3, groups = { ValidateGroups.CRUD.class })
	private Integer idx;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	private String icon;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	private String link;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
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
