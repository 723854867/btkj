package org.btkj.common.web;

public class Config {

	private static int userNameLen;
	
	public static int getUserNameLen() {
		return userNameLen;
	}
	
	public static void setUserNameLen(int userNameLen) {
		Config.userNameLen = userNameLen;
	}
}
