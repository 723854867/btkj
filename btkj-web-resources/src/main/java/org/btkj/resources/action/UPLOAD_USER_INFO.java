package org.btkj.resources.action;

import java.io.InputStream;

import javax.annotation.Resource;

import org.btkj.pojo.AliyunResourceUtil;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.User;
import org.btkj.resources.AliyunUploader;
import org.btkj.resources.pojo.info.UploadUserInfo;
import org.btkj.resources.pojo.param.UploadUserInfoParam;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.lang.StringUtil;

/**
 * 用户基本信息资源修改：头像、身份证正反面
 * 
 * @author ahab
 */
public class UPLOAD_USER_INFO extends UserAction<UploadUserInfoParam> {
	
	@Resource
	private AliyunUploader aliyunUploader;

	@Override
	protected Result<UploadUserInfo> execute(App app, User user, UploadUserInfoParam param) {
		UploadUserInfo info = new UploadUserInfo();
		if (null != param.getAvatar())
			info.setAvatar(_uploadAvatar(app, user, param.getAvatar()));
		if (null != param.getIdentityFace())
			info.setIdentityFace(_uploadIdentityFace(app, user, param.getIdentityFace()));
		if (null != param.getIdentityBack())
			info.setIdentityBack(_uploadIdentityBack(app, user, param.getIdentityBack()));
		return Result.result(info);
	}
	
	private String _uploadAvatar(App app, User user, InputStream avatarFile) { 
		String avatar = AliyunResourceUtil.pngResource();
		String key = AliyunResourceUtil.userResourceKey(user, avatar);
		if (!aliyunUploader.upload(key, avatarFile))
			return null;
		if (StringUtil.hasText(user.getAvatar()))
			aliyunUploader.delete(AliyunResourceUtil.userResourceKey(user, user.getAvatar()));
		user.setAvatar(avatar);
		user.setUpdated(DateUtil.currentTime());
		userService.update(user);
		return AliyunResourceUtil.userResource(user, avatar);
	}
	
	private String _uploadIdentityFace(App app, User user, InputStream identityFaceFile) {
		String identityFace = AliyunResourceUtil.pngResource();
		String key = AliyunResourceUtil.userResourceKey(user, identityFace);
		if (!aliyunUploader.upload(key, identityFaceFile))
			return null;
		if (StringUtil.hasText(user.getIdentityFace()))
			aliyunUploader.delete(AliyunResourceUtil.userResourceKey(user, user.getIdentityFace()));
		user.setIdentityFace(identityFace);
		user.setUpdated(DateUtil.currentTime());
		userService.update(user);
		return AliyunResourceUtil.userResource(user, identityFace);
	}
	
	private String _uploadIdentityBack(App app, User user, InputStream identityBackFile) {
		String identityBack = AliyunResourceUtil.pngResource();
		String key = AliyunResourceUtil.userResourceKey(user, identityBack);
		if (!aliyunUploader.upload(key, identityBackFile))
			return null;
		if (StringUtil.hasText(user.getIdentityBack()))
			aliyunUploader.delete(AliyunResourceUtil.userResourceKey(user, user.getIdentityBack()));
		user.setIdentityBack(identityBack);
		user.setUpdated(DateUtil.currentTime());
		userService.update(user);
		return AliyunResourceUtil.userResource(user, identityBack);
	}
}
