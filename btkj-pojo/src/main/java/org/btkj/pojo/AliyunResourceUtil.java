package org.btkj.pojo;

import java.text.MessageFormat;

import org.btkj.pojo.entity.user.App;
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
	
	public static String btResourceSuffix(String resource) {
		return btPrefix + resource;
	}
	
	public static String btResourceSuffix(FileType fileType) {
		return btPrefix + AlternativeJdkIdGenerator.INSTANCE.generateId().toString() + fileType.suffix();
	}
	
	public static String userResource(App app, User user, String resource) {
		return ossPrefix + MessageFormat.format(userPrefix, String.valueOf(app.getId()), String.valueOf(user.getUid())) + resource;
	}
	
	public static String userResourceSuffix(App app, User user, String resource) {
		return MessageFormat.format(userPrefix, String.valueOf(app.getId()), String.valueOf(user.getUid())) + resource;
	}
	
	public static String userResourceSuffix(App app, User user, FileType fileType) {
		return MessageFormat.format(userPrefix, String.valueOf(app.getId()), String.valueOf(user.getUid())) + AlternativeJdkIdGenerator.INSTANCE.generateId().toString() + fileType.suffix();
	}
	
	public static String tenantResource(App app, Tenant tenant, String resource) {
		return ossPrefix + MessageFormat.format(tenantPrefix, String.valueOf(app.getId()), String.valueOf(tenant.getTid())) + resource;
	}
	
	public static String tenantResourceSuffix(App app, Tenant tenant, String resource) {
		return MessageFormat.format(tenantPrefix, String.valueOf(app.getId()), String.valueOf(tenant.getTid())) + resource;
	}
	
	public static String tenantResourceSuffix(App app, Tenant tenant, FileType fileType) {
		return MessageFormat.format(tenantPrefix, String.valueOf(app.getId()), String.valueOf(tenant.getTid())) + AlternativeJdkIdGenerator.INSTANCE.generateId().toString() + fileType.suffix();
	}
	
	public static void setOssPrefix(String ossPrefix) {
		AliyunResourceUtil.ossPrefix = ossPrefix;
	}
}
