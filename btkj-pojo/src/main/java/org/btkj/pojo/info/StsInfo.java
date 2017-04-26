package org.btkj.pojo.info;

import org.rapid.util.common.model.UniqueModel;

public class StsInfo implements UniqueModel<String> {

	private static final long serialVersionUID = -7073264768932854538L;

	private String key;
	private long expire;
	
	private String bucket;
	private String endpoint;
	private String expiration;
	private String accessKeyId;
	private String securityToken;
	private String accessKeySecret;
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public long getExpire() {
		return expire;
	}
	
	public void setExpire(long expire) {
		this.expire = expire;
	}
	
	public String getBucket() {
		return bucket;
	}
	
	public void setBucket(String bucket) {
		this.bucket = bucket;
	}
	
	public String getEndpoint() {
		return endpoint;
	}
	
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	
	public String getExpiration() {
		return expiration;
	}
	
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	
	public String getAccessKeyId() {
		return accessKeyId;
	}
	
	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}
	
	public String getSecurityToken() {
		return securityToken;
	}
	
	public void setSecurityToken(String securityToken) {
		this.securityToken = securityToken;
	}
	
	public String getAccessKeySecret() {
		return accessKeySecret;
	}
	
	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	@Override
	public String key() {
		return this.key;
	}
}
