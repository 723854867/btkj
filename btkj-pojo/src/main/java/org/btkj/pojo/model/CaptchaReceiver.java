package org.btkj.pojo.model;

import java.io.Serializable;

/**
 * 验证码接收设备的信息
 * 
 * @author ahab
 */
public class CaptchaReceiver implements Serializable {

	private static final long serialVersionUID = 3924529610822330871L;

	private int appId;					// 所属 app id
	private Type type;					// 接收设备的类型
	private String identity;			// 接收设备的识别号：手机则是手机号，邮箱就是邮箱账号
	
	public enum Type {
		MOBILE(0),
		EMAIL(1);
		private int mark;
		private Type(int mark) {
			this.mark = mark;
		}
		public int mark() {
			return mark;
		}
		public static final Type match(int type) { 
			for (Type temp : Type.values()) {
				if (temp.mark == type)
					return temp;
			}
			return null;
		}
	}
	
	public int getAppId() {
		return appId;
	}
	
	public void setAppId(int appId) {
		this.appId = appId;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public String getIdentity() {
		return identity;
	}
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}
}
