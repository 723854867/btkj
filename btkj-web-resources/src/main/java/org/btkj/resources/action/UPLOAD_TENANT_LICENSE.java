package org.btkj.resources.action;

import javax.annotation.Resource;

import org.btkj.pojo.AliyunResourceUtil;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.BtkjUtil;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.enums.Client;
import org.btkj.resources.AliyunUploader;
import org.btkj.resources.pojo.param.UploadTenantLicenseParam;
import org.btkj.user.api.TenantService;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.lang.StringUtil;

/**
 * 商户营业执照上传
 * 
 * @author ahab
 */
public class UPLOAD_TENANT_LICENSE extends UserAction<UploadTenantLicenseParam> {
	
	@Resource
	private TenantService tenantService;
	@Resource
	private AliyunUploader aliyunUploader;
	
	@Override
	protected Result<String> execute(App app, User user, UploadTenantLicenseParam param) {
		if (BtkjUtil.isSeal(app))
			return BtkjConsts.RESULT.APP_SEALED;
		if (BtkjUtil.isSeal(user))
			return BtkjConsts.RESULT.USER_SEALED;
		Tenant tenant = tenantService.tenant(param.getTid());
		if (null == tenant)
			return BtkjConsts.RESULT.TENANT_NOT_EXIST;
		if (tenant.getAppId() != app.getId())
			return Consts.RESULT.FORBID;
		String license = AliyunResourceUtil.pngResource();
		String key = AliyunResourceUtil.tenantResourceKey(tenant, license);
		if (!aliyunUploader.upload(key, param.getLicense()))
			return Consts.RESULT.FAILURE;
		if (StringUtil.hasText(tenant.getLicenseImage()))
			aliyunUploader.delete(AliyunResourceUtil.tenantResourceKey(tenant, tenant.getLicenseImage()));
		tenant.setLicenseImage(license);
		tenant.setUpdated(DateUtil.currentTime());
		tenantService.update(tenant);
		return Result.result(AliyunResourceUtil.tenantResource(tenant, license));
	}
	
	@Override
	public Client client() {
		return Client.TENANT_MANAGER;
	}
}
