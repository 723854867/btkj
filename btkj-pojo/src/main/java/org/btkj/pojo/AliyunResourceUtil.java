package org.btkj.pojo;

import java.text.MessageFormat;

import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.enums.FileType;
import org.rapid.util.common.uuid.AlternativeJdkIdGenerator;

public class AliyunResourceUtil {
	
	private static String ossPrefix = "http://btkj-test.oss-cn-hangzhou.aliyuncs.com/";
	
	private static String btPrefix = "bt/";
	private static String userPrefix = "app/{0}/user/{1}/";
	private static String tenantPrefix = "app/{0}/tenant/{1}/";
	
	public static String btResource(String resource) {
		return ossPrefix + btPrefix + resource;
	}
	
	public static String btResourceKey(String suffix) {
		return btPrefix + suffix;
	}
	
	public static String userResource(User user, String resource) {
		return null == resource ? null : ossPrefix + MessageFormat.format(userPrefix, String.valueOf(user.getAppId()), String.valueOf(user.getUid())) + resource;
	}
	
	public static String userResourceKey(User user, String suffix) {
		return MessageFormat.format(userPrefix, String.valueOf(user.getAppId()), String.valueOf(user.getUid())) + suffix;
	}
	
	public static String tenantResource(Tenant tenant, String resource) {
		return null == resource ? null : ossPrefix + MessageFormat.format(tenantPrefix, String.valueOf(tenant.getAppId()), String.valueOf(tenant.getTid())) + resource;
	}
	
	public static String tenantResourceKey(Tenant tenant, String suffix) {
		return MessageFormat.format(tenantPrefix, String.valueOf(tenant.getAppId()), String.valueOf(tenant.getTid())) + suffix;
	}
	
	public static String pngResource() {
		return AlternativeJdkIdGenerator.INSTANCE.generateId().toString() + FileType.PNG.suffix();
	}
	
	public static void setOssPrefix(String ossPrefix) {
		AliyunResourceUtil.ossPrefix = ossPrefix;
	}
}
