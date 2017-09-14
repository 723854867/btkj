package org.btkj.resources.pojo.param;

import java.io.InputStream;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.btkj.pojo.param.Param;

public class UploadInsurerIconParam extends Param {

	private static final long serialVersionUID = 2923518841905226921L;

	@Min(1)
	private int id;
	@NotNull
	private InputStream icon;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public InputStream getIcon() {
		return icon;
	}
	
	public void setIcon(InputStream icon) {
		this.icon = icon;
	}
}
