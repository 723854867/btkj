package org.btkj.resources.pojo.param;

import java.io.IOException;
import java.io.InputStream;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.btkj.pojo.param.Param;

public class UploadTenantLicenseParam extends Param {

	private static final long serialVersionUID = -4685017733726214359L;

	@Min(1)
	private int tid;
	@NotNull
	private InputStream license;
	
	public int getTid() {
		return tid;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
	}
	
	public InputStream getLicense() {
		return license;
	}
	
	public void setLicense(InputStream license) {
		this.license = license;
	}
	
	@Override
	public void dispose() throws IOException {
		if (null != this.license) 
			this.license.close();
	}
}
