package org.btkj.resources.action;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.AliyunResourceUtil;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.BtkjUtil;
import org.btkj.pojo.entity.config.Insurer;
import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.enums.Client;
import org.btkj.resources.AliyunUploader;
import org.btkj.resources.pojo.param.UploadInsurerIconParam;
import org.btkj.web.util.action.AdminAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.lang.StringUtil;

public class UPLOAD_INSURER_ICON extends AdminAction<UploadInsurerIconParam> {
	
	@Resource
	private ConfigService configService;
	@Resource
	private AliyunUploader aliyunUploader;

	@Override
	protected Result<String> execute(Admin admin, UploadInsurerIconParam param) {
		if (BtkjUtil.isSeal(admin))
			return BtkjConsts.RESULT.ADMIN_SEALED;
		Insurer insurer = configService.insurer(param.getId());
		if (null == insurer)
			return BtkjConsts.RESULT.INSURER_NOT_EXIST;
		String suffix = AliyunResourceUtil.pngResource();
		String icon = AliyunResourceUtil.btResourceKey(suffix);
		if (!aliyunUploader.upload(icon, param.getIcon()))
			return Consts.RESULT.FAILURE;
		if (StringUtil.hasText(insurer.getIcon()))
			aliyunUploader.delete(AliyunResourceUtil.btResourceKey(insurer.getIcon()));
		insurer.setIcon(suffix);
		insurer.setUpdated(DateUtil.currentTime());
		configService.updateInsurer(insurer);
		return Result.result(AliyunResourceUtil.btResource(suffix));
	}
	
	@Override
	public Client client() {
		return Client.BAO_TU_MANAGER;
	}
}
