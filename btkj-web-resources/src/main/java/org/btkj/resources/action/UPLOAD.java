package org.btkj.resources.action;

import java.io.InputStream;

import javax.annotation.Resource;

import org.btkj.resources.AliyunUploader;
import org.btkj.resources.pojo.param.UploadParam;
import org.btkj.web.util.action.UploadAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

public class UPLOAD extends UploadAction<UploadParam> {
	
	@Resource
	private AliyunUploader aliyunUploader;
	
	@Override
	protected Result<?> execute(UploadParam param) {
		InputStream input = param.getFile();
		aliyunUploader.upload("common/user/test/test.png", input);
		return Consts.RESULT.OK;
	}
}
