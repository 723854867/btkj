package org.btkj.pojo;

import java.text.MessageFormat;

import org.btkj.pojo.entity.UserPO;

public class AliyunUtil {

	private static String ossPrefix = "http://btkj-test.oss-cn-hangzhou.aliyuncs.com/";
	
	private static String userPrefix = "user/{0}/";
	
	public static String userResource(UserPO user, String resource) {
		return ossPrefix + MessageFormat.format(userPrefix, String.valueOf(user.getUid())) + resource;
	}
	
	public static void setOssPrefix(String ossPrefix) {
		AliyunUtil.ossPrefix = ossPrefix;
	}
}
