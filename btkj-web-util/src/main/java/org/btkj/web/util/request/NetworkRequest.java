package org.btkj.web.util.request;

/**
 * 表示网络请求
 * 
 * @author ahab
 */
public interface NetworkRequest {

	/**
	 * 返回结果
	 * 
	 * @param reply
	 */
	void write(String reply);

	/**
	 * 返回结果
	 * 
	 * @param reply
	 */
	void write(byte[] reply);
}
