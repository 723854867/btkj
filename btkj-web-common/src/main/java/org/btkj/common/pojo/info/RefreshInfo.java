package org.btkj.common.pojo.info;

import java.io.Serializable;

import org.btkj.common.Config;
import org.btkj.pojo.config.GlobalConfigContainer;
import org.rapid.util.common.enums.Protocol;

public class RefreshInfo implements Serializable {

	private static final long serialVersionUID = -5632827376726076204L;

	private int commonPort;
	private String shareUrl;
	private String commonHost;
	private Protocol protocol;
	
	public RefreshInfo() {
		this.commonPort = Config.getPort();
		this.commonHost = Config.getHost();
		this.protocol = Config.getProtocol();
		this.shareUrl = GlobalConfigContainer.getGlobalConfig().getEmployeeInviteUrl();
	}
	
	public int getCommonPort() {
		return commonPort;
	}
	
	public void setCommonPort(int commonPort) {
		this.commonPort = commonPort;
	}
	
	public String getShareUrl() {
		return shareUrl;
	}
	
	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}
	
	public String getCommonHost() {
		return commonHost;
	}
	
	public void setCommonHost(String commonHost) {
		this.commonHost = commonHost;
	}
	
	public Protocol getProtocol() {
		return protocol;
	}
	
	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}
}
