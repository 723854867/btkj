package org.btkj.resources.pojo.param;

import java.io.IOException;
import java.io.InputStream;

import org.btkj.pojo.param.Param;

public class UploadUserInfoParam extends Param {

	private static final long serialVersionUID = -8951918819631576392L;
	
	private InputStream avatar;
	private InputStream identityFace;
	private InputStream identityBack;
	
	public InputStream getAvatar() {
		return avatar;
	}
	
	public void setAvatar(InputStream avatar) {
		this.avatar = avatar;
	}
	
	public InputStream getIdentityFace() {
		return identityFace;
	}
	
	public void setIdentityFace(InputStream identityFace) {
		this.identityFace = identityFace;
	}
	
	public InputStream getIdentityBack() {
		return identityBack;
	}
	
	public void setIdentityBack(InputStream identityBack) {
		this.identityBack = identityBack;
	}
	
	@Override
	public void dispose() throws IOException {
		if (null != this.avatar)
			this.avatar.close();
		if (null != this.identityFace)
			this.identityFace.close();
		if (null != this.identityBack)
			this.identityBack.close();
	}
}
