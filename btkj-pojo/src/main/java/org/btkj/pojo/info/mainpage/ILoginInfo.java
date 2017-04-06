package org.btkj.pojo.info.mainpage;

/**
 * 登录信息：除了包含首页信息还包含一些额外的信息，比如登录 token
 * 
 * @author ahab
 */
public interface ILoginInfo extends IMainPageInfo {

	/**
	 * 获取登录 token
	 * 
	 * @return
	 */
	String getToken();
	
	/**
	 * 设置登录 token
	 * 
	 * @param token
	 */
	void setToken(String token);
}
