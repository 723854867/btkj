package org.btkj.resources.action;

import java.io.InputStream;

import javax.annotation.Resource;

import org.btkj.pojo.AliyunResourceUtil;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.enums.FileType;
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
	protected Result<UploadUserInfo> execute(AppPO app, UserPO user, UploadUserInfoParam param) {
		UploadUserInfo info = new UploadUserInfo();
		if (null != param.getAvatar())
			info.setAvatar(_uploadAvatar(app, user, param.getAvatar()));
		if (null != param.getIdentityFace())
			info.setIdentityFace(_uploadIdentityFace(app, user, param.getIdentityFace()));
		if (null != info.getIdentityBack())
			info.setIdentityBack(_uploadIdentityBack(app, user, param.getIdentityBack()));
		return Result.result(info);
	}
	
	private String _uploadAvatar(AppPO app, UserPO user, InputStream avatar) { 
		String resource = AliyunResourceUtil.userResourceSuffix(app, user, FileType.PNG);
		if (!aliyunUploader.upload(resource, avatar))
			return null;
		if (StringUtil.hasText(user.getAvatar()))
			aliyunUploader.delete(AliyunResourceUtil.userResourceSuffix(app, user, user.getAvatar()));
		user.setAvatar(resource);
		user.setUpdated(DateUtil.currentTime());
		userService.update(user);
		return AliyunResourceUtil.userResource(app, user, resource);
	}
	
	private String _uploadIdentityFace(AppPO app, UserPO user, InputStream identityFace) {
		String resource = AliyunResourceUtil.userResourceSuffix(app, user, FileType.PNG);
		if (!aliyunUploader.upload(resource, identityFace))
			return null;
		if (StringUtil.hasText(user.getIdentityFace()))
			aliyunUploader.delete(AliyunResourceUtil.userResourceSuffix(app, user, user.getIdentityFace()));
		user.setIdentityFace(resource);
		user.setUpdated(DateUtil.currentTime());
		userService.update(user);
		return AliyunResourceUtil.userResource(app, user, resource);
	}
	
	private String _uploadIdentityBack(AppPO app, UserPO user, InputStream identityBack) {
		String resource = AliyunResourceUtil.userResourceSuffix(app, user, FileType.PNG);
		if (!aliyunUploader.upload(resource, identityBack))
			return null;
		if (StringUtil.hasText(user.getIdentityBack()))
			aliyunUploader.delete(AliyunResourceUtil.userResourceSuffix(app, user, user.getIdentityBack()));
		user.setIdentityBack(resource);
		user.setUpdated(DateUtil.currentTime());
		userService.update(user);
		return AliyunResourceUtil.userResource(app, user, resource);
	}
}
