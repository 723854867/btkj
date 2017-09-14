package org.btkj.resources.pojo.param;

import java.io.InputStream;

import javax.validation.constraints.NotNull;

import org.btkj.pojo.param.Param;
import org.rapid.util.validator.custom.Stream;

public class UploadParam extends Param {

	private static final long serialVersionUID = 764866531248178241L;

	@NotNull
	@Stream(maxmium = 1024*1024)
	private InputStream file;
	
	public InputStream getFile() {
		return file;
	}
	
	public void setFile(InputStream file) {
		this.file = file;
	}
}
