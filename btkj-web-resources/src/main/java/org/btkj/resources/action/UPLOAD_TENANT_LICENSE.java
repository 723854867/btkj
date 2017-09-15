package org.btkj.resources.action;

import javax.annotation.Resource;

import org.btkj.pojo.AliyunResourceUtil;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.enums.FileType;
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
		Tenant tenant = tenantService.tenant(param.getTid());
		if (null == tenant)
			return BtkjConsts.RESULT.TENANT_NOT_EXIST;
		if (tenant.getAppId() != app.getId())
			return Consts.RESULT.FORBID;
		String resource = AliyunResourceUtil.tenantResourceSuffix(app, tenant, FileType.PNG);
		if (!aliyunUploader.upload(resource, param.getLicense()))
			return Consts.RESULT.FAILURE;
		if (StringUtil.hasText(tenant.getLicenseImage()))
			aliyunUploader.delete(AliyunResourceUtil.tenantResourceSuffix(app, tenant, tenant.getLicenseImage()));
		tenant.setLicenseImage(resource);
		tenant.setUpdated(DateUtil.currentTime());
		tenantService.update(tenant);
		return Result.result(AliyunResourceUtil.tenantResource(app, tenant, resource));
	}
	
	@Override
	public Client client() {
		return Client.TENANT_MANAGER;
	}
}
