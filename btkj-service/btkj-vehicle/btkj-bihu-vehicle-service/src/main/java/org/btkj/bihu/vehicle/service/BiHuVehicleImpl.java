package org.btkj.bihu.vehicle.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.btkj.bihu.vehicle.api.BiHuVehicle;
import org.btkj.bihu.vehicle.domain.BiHuParams;
import org.btkj.bihu.vehicle.domain.RenewInfo;
import org.btkj.bihu.vehicle.persistence.entity.CityCode;
import org.btkj.bihu.vehicle.persistence.entity.TenantConfig;
import org.btkj.bihu.vehicle.redis.CityCodeMapper;
import org.btkj.bihu.vehicle.redis.TenantConfigMapper;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.exception.BusinessException;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.Renew;
import org.rapid.util.common.Constants;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.net.http.HttpProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("biHuVehicle")
public class BiHuVehicleImpl implements BiHuVehicle {
	
	private static final Logger logger = LoggerFactory.getLogger(BiHuVehicleImpl.class);
	
	private static final String SEC_CODE					= "SecCode";
	
	@Resource
	private HttpProxy httpProxy;
	@Resource
	private CityCodeMapper cityCodeMapper;
	@Resource
	private TenantConfigMapper tenantConfigMapper;
	
	@Value("${bihu.key}")
	private String key;
	@Value("${bihu.agent}")
	private String agent;
	@Value("${bihu.schema}")
	private String biHuSchema;
	@Value("${bihu.host}")
	private String biHuHost;
	@Value("${bihu.renewInfo.Path}")
	private String biHuRenewInfoPath;

	@Override
	public Result<Renew> renewlInfo(EmployeeForm employeeForm, String license) {
		BiHuParams params = new BiHuParams();
		params.setLicenseNo(license);
		params.setCustKey(DigestUtils.md5Hex(String.valueOf(employeeForm.getUser().getUid())));
		TenantConfig tc = tenantConfigMapper.getByKey(employeeForm.getTenant().getTid());
		if (null != tc) 
			params.setAgent(tc.getAgent()).setKey(tc.getKey());
		else
			params.setAgent(agent).setKey(key);
		CityCode cityCode = cityCodeMapper.getByKey(employeeForm.getTenant().getRegion());
		if (null == cityCode)
			return Result.result(BtkjCode.BIHU_CITY_UNSUPPORT);
		params.setCityCode(cityCode.getCid());
		HttpUriRequest request = _requestUri(biHuRenewInfoPath, params);
		try {
			RenewInfo info = httpProxy.syncRequest(request, RenewInfo.JSON_HANDLER);
			return Result.result(info.toRenew());
		} catch (IOException e) {
			logger.warn("bihu renew request failure!", e);
			return Result.result(BtkjCode.RENEW_INFO_GET_TIMEOUT);
		}
	}
	
	@Override
	public void quote(EmployeeForm employeeForm, String license) {
		
	}
	
	private HttpUriRequest _requestUri(String path, BiHuParams params) {
		URIBuilder builder = new URIBuilder();
		builder.setScheme(biHuSchema);
		builder.setHost(biHuHost);
		builder.setPath(path);
		
		StringBuilder str2encode = new StringBuilder();
		for (Entry<String, String> entry : params.entrySet()) {
			builder.addParameter(entry.getKey(), entry.getValue());
			str2encode.append(entry.getKey()).append(Constants.SYMBOL_EQUAL).append(entry.getValue()).append(Constants.SYMBOL_AND);
		}
		str2encode.deleteCharAt(str2encode.length() - 1);
		str2encode.append(params.getKey());
		builder.addParameter(SEC_CODE, DigestUtils.md5Hex(str2encode.toString()));
		try {
			return new HttpGet(builder.build());
		} catch (URISyntaxException e) {
			throw new BusinessException(Code.SYSTEM_ERROR, "request uri build failure", e);
		}
	}
}
