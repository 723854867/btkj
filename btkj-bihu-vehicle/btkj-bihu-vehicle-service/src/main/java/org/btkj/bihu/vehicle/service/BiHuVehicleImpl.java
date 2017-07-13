package org.btkj.bihu.vehicle.service;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.btkj.bihu.vehicle.api.BiHuVehicle;
import org.btkj.bihu.vehicle.domain.BiHuParams;
import org.btkj.bihu.vehicle.domain.InsureResult;
import org.btkj.bihu.vehicle.domain.QuoteResp;
import org.btkj.bihu.vehicle.domain.QuoteResult;
import org.btkj.bihu.vehicle.domain.RenewInfo;
import org.btkj.bihu.vehicle.domain.RequestType;
import org.btkj.bihu.vehicle.exception.RequestFrequently;
import org.btkj.bihu.vehicle.pojo.entity.BiHuArea;
import org.btkj.bihu.vehicle.pojo.entity.BiHuInsurer;
import org.btkj.bihu.vehicle.pojo.entity.TenantConfig;
import org.btkj.bihu.vehicle.redis.BiHuAreaMapper;
import org.btkj.bihu.vehicle.redis.BiHuInsurerMapper;
import org.btkj.bihu.vehicle.redis.TenantConfigMapper;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.Renewal;
import org.btkj.pojo.enums.IDType;
import org.btkj.pojo.enums.CommercialInsuranceType;
import org.btkj.pojo.exception.BusinessException;
import org.btkj.pojo.info.tips.VehiclePolicyTips;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.insur.vehicle.InsurUnit;
import org.btkj.pojo.model.insur.vehicle.Insurance;
import org.btkj.pojo.model.insur.vehicle.PolicyDetail;
import org.btkj.pojo.model.insur.vehicle.PolicySchema;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtils;
import org.rapid.util.lang.StringUtils;
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
	private BiHuAreaMapper biHuAreaMapper;
	@Resource
	private BiHuInsurerMapper biHuInsurerMapper;
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
	@Value("${bihu.renewInfo.path}")
	private String renewInfoPath;
	@Value("${bihu.renewInfo.timeout}")
	private int renewInfoTimeout;
	@Value("${bihu.quote.path}")
	private String biHuQuotePath;
	@Value("${bihu.quote.timeout}")
	private int quoteTimeout;
	@Value("${bihu.quote.result.path}")
	private String quoteResultPath;
	@Value("${bihu.quote.result.timeout}")
	private int quoteResultTimeout;
	@Value("${bihu.insure.result.path}")
	private String insureResultPath;
	@Value("${bihu.insure.result.timeout}")
	private int insureResultTimeout;

	@Override
	public Result<Renewal> renewal(EmployeeForm employeeForm, String license) {
		BiHuParams params = new BiHuParams(RequestType.RENEWL);
		params.setLicenseNo(license);
		return _doRenewal(employeeForm, params);
	}
	
	@Override
	public Result<Renewal> renewal(EmployeeForm employeeForm, String vin, String engine) {
		BiHuParams params = new BiHuParams(RequestType.RENEWL);
		params.setCarVin(vin);
		params.setEngineNo(engine);
		return _doRenewal(employeeForm, params);
	}
	
	private Result<Renewal> _doRenewal(EmployeeForm employeeForm, BiHuParams params) {
		params.setCustKey(DigestUtils.md5Hex(String.valueOf(employeeForm.getUser().getUid())));	// 用 uid 作为 custkey
		TenantConfig tc = tenantConfigMapper.getByKey(employeeForm.getTenant().getTid());
		if (null != tc) 
			params.setAgent(tc.getAgent()).setKey(tc.getKey());
		else
			params.setAgent(agent).setKey(key);
		BiHuArea cityCode = biHuAreaMapper.getByKey(employeeForm.getTenant().getRegion());
		if (null == cityCode)
			return Result.result(BtkjCode.CITY_UNSUPPORT);
		params.setCityCode(cityCode.getCid());
		HttpUriRequest request = _requestUri(renewInfoPath, params, renewInfoTimeout);
		try {
			RenewInfo info = httpProxy.syncRequest(request, RenewInfo.JSON_HANDLER);
			int status = info.getBusinessStatus();
			if (status == 2 || status == -10003 || status == -10002 || status == -10001 || status == -1000)
				return Result.result(BtkjCode.RENEW_INFO_GET_FAILURE);
			
			Renewal renewal = info.toRenewal();
			BiHuInsurer insurer = biHuInsurerMapper.getByCode(renewal.getInsurerId());
			renewal.setInsurerId(null != insurer ? insurer.getId() : 0);
			return status == 3 ? Result.result(BtkjCode.RENEW_INFO_VEHICLE_ONLY, renewal) : Result.result(renewal);
		} catch (IOException e) {
			logger.warn("bihu renew request failure!", e);
			return Result.result(BtkjCode.RENEW_INFO_GET_TIMEOUT);
		} catch (RequestFrequently e) {
			return Result.result(BtkjCode.BIHU_REQUEST_FREQUENTLY);
		}
	}
	
	@Override
	public Result<Void> order(EmployeeForm employeeForm, Set<Integer> quote, Set<Integer> insure, VehiclePolicyTips tips) {
		TenantConfig tc = tenantConfigMapper.getByKey(employeeForm.getTenant().getTid());
		if (null == tc)
			return Result.result(BtkjCode.LANE_BIHU_NOT_OPENED);
		BiHuArea cityCode = biHuAreaMapper.getByKey(employeeForm.getTenant().getRegion());
		if (null == cityCode)
			return Result.result(BtkjCode.CITY_UNSUPPORT);
		List<BiHuInsurer> insurers = biHuInsurerMapper.getWithinKey(new ArrayList<Integer>(quote));
		if (insurers.size() != quote.size())
			return Result.result(BtkjCode.INSURER_NOT_EXIST);
		int quoteMod = 0;
		int insureMod = 0;
		for (BiHuInsurer insurer : insurers) {
			quoteMod |= insurer.getCode();
			if (null != insure && insure.remove(insurer.getId())) 
				insureMod |= insurer.getCode();
		}
		BiHuParams params = new BiHuParams(RequestType.QUOTE);
		params.setAgent(tc.getAgent())
			.setKey(tc.getKey())
			.setQuoteGroup(quoteMod)
			.setSubmitGroup(insureMod)
			.setCityCode(cityCode.getCid())
			.setCustKey(DigestUtils.md5Hex(String.valueOf(employeeForm.getUser().getUid())));
		
		_buildOwner(params, tips.getOwner());
		_buildInsured(params, tips.getInsured());
		_buildInsurer(params, tips.getInsurer());
		_buildVehicle(params, tips);
		_buildSchema(params, tips.getSchema());
		HttpUriRequest request = _requestUri(biHuQuotePath, params, quoteTimeout);
		try {
			QuoteResp resp = httpProxy.syncRequest(request, QuoteResp.JSON_HANDLER);
			if (resp.getBusinessStatus() != 1)
				return Result.result(BtkjCode.QUOTE_FAILURE);
			return Result.success();
		} catch (IOException e) {
			logger.warn("bihu quote request failure!", e);
			return Result.result(BtkjCode.QUOTE_FAILURE);
		}
	}
	
	private void _buildOwner(BiHuParams params, InsurUnit owner) { 
		if (null == owner)
			return;
		params.setCarOwnerName(owner.getName());
		params.setIdCard(owner.getIdNo());
		params.setOwnerIdCardType(_biHuIdType(owner.getIdType()));
	}
	
	private void _buildInsured(BiHuParams params, InsurUnit insured) { 
		if (null == insured)
			return;
		params.setInsuredName(insured.getName());
		params.setInsuredIdType(_biHuIdType(insured.getIdType()));
		params.setInsuredIdCard(insured.getIdNo());
		if (StringUtils.hasText(insured.getMobile()))
			params.setInsuredMobile(insured.getMobile());
	}
	
	private void _buildInsurer(BiHuParams params, InsurUnit insurer) { 
		if (null == insurer)
			return;
		params.setHolderName(insurer.getName());
		params.setHolderIdType(_biHuIdType(insurer.getIdType()));
		params.setHolderIdCard(insurer.getIdNo());
		if (StringUtils.hasText(insurer.getMobile()))
			params.setHolderMobile(insurer.getMobile());
	}
	
	private String _biHuIdType(IDType idType) { 
		switch (idType) {
		case IDENTITY:
			return String.valueOf(1);
		case ORGANIZATION_LICENSE:
			return String.valueOf(2);
		case BUSINESS_LICENSE:
			return String.valueOf(9);
		default:
			throw new IllegalArgumentException("Unsupported id type " + idType);
		}
	}
	
	private void _buildVehicle(BiHuParams params, VehiclePolicyTips tips) {
		params.setLicenseNo(tips.getLicense());
		params.setEngineNo(tips.getEngine());
		params.setCarVin(tips.getVin());
		params.setRegisterDate(tips.getEnrollDate());
		params.setMoldName(tips.getName());
		if (null != tips.getIssueDate())
			params.setTransferDate(tips.getIssueDate());
		params.setSeatCount(String.valueOf(tips.getSeat()));
	}
	
	private void _buildSchema(BiHuParams params, PolicySchema schema) {
		if (null != schema.getCommercialStart()) {
			if (null != schema.getCompulsiveStart()) {
				params.setForceTax(String.valueOf(1));
				params.setForceTimeStamp(String.valueOf((int) (DateUtils.getTime(schema.getCompulsiveStart(), DateUtils.YYYY_MM_DD_HH_MM_SS) / 1000)));
			} else
				params.setForceTax(String.valueOf(0));
			params.setBizTimeStamp(String.valueOf((int) (DateUtils.getTime(schema.getCommercialStart(), DateUtils.YYYY_MM_DD_HH_MM_SS) / 1000)));
		} else {
			if (null == schema.getCompulsiveStart())
				throw new IllegalArgumentException("compulsory and commercial insur must choose one!");
			else {
				params.setForceTax(String.valueOf(2));
				params.setForceTimeStamp(String.valueOf((int) (DateUtils.getTime(schema.getCompulsiveStart(), DateUtils.YYYY_MM_DD_HH_MM_SS) / 1000)));
			}
		}
		
		Map<CommercialInsuranceType, Insurance> commercial = schema.getInsurances();
		for (CommercialInsuranceType type : CommercialInsuranceType.values()) {
			Insurance insurance = null == commercial ? null : commercial.get(type);
			switch (type) {
			case DAMAGE:
				params.setCheSun(null != insurance ? 1 : 0);
				break;
			case DAMAGE_DEDUCTIBLE:
				params.setBuJiMianCheSun(null != insurance ? 1 : 0);
				break;
			case THIRD:
				params.setSanZhe(null != insurance ? insurance.getQuota() : 0);
				break;
			case THIRD_DEDUCTIBLE:
				params.setBuJiMianSanZhe(null != insurance ? 1 : 0);
				break;
			case DRIVER:
				params.setSiJi(null != insurance ? insurance.getQuota() : 0);
				break;
			case DRIVER_DEDUCTIBLE:
				params.setBuJiMianSiJi(null != insurance ? 1 : 0);
				break;
			case PASSENGER:
				params.setChengKe(null != insurance ? insurance.getQuota() : 0);
				break;
			case PASSENGER_DEDUCTIBLE:
				params.setBuJiMianChengKe(null != insurance ? 1 : 0);
				break;
			case ROBBERY:
				params.setDaoQiang(null != insurance ? 1 : 0);
				break;
			case ROBBERY_DEDUCTIBLE:
				params.setBuJiMianDaoQiang(null != insurance ? 1 : 0);
				break;
			case GLASS:
				if (null != insurance) {
					int value = (int) insurance.getQuota();
					if (value != 1 && value != 2)
						value = 0;
					params.setBoLi(value);
				} else
					params.setBoLi(0);
				break;
			case AUTO_FIRE:
				params.setZiRan(null != insurance ? 1 : 0);
				break;
			case AUTO_FIRE_DEDUCTIBLE:
				params.setBuJiMianZiRan(null != insurance ? 1 : 0);
				break;
			case SCRATCH:
				params.setHuaHen(null != insurance ? insurance.getQuota() : 0);
				break;
			case SCRATCH_DEDUCTIBLE:
				params.setBuJiMianHuaHen(null != insurance ? 1 : 0);
				break;
			case WADDING:
				params.setSheShui(null != insurance ? 1 : 0);
				break;
			case WADDING_DEDUCTIBLE:
				params.setBuJiMianSheShui(null != insurance ? 1 : 0);
				break;
			case GARAGE_DESIGNATED:
				if (null != insurance) {
					int value = (int) insurance.getQuota();
					if (value != -1 && value != 0 && value != 1)
						value = -1;
					params.setHcXiuLiChangType(value);
					params.setHcXiuLiChang(insurance.getPrice());
				}
				break;
			case UNKNOWN_THIRD:
				params.setHcSanFangTeYue(null != insurance ? 1 : 0);
				break;
			default:
				break;
			}
		}
		params.setBuJiMianJingShenSunShi(0); // 不计免精神损失不做该项投保
	}
	
	@Override
	public Result<PolicySchema> quoteResult(EmployeeForm employeeForm, String license, int insurId) {
		BiHuInsurer insurer = biHuInsurerMapper.getByKey(insurId);
		BiHuParams params = new BiHuParams(RequestType.QUOTE_RESULT);
		params.setLicenseNo(license);
		params.setCustKey(DigestUtils.md5Hex(String.valueOf(employeeForm.getUser().getUid())));
		TenantConfig tc = tenantConfigMapper.getByKey(employeeForm.getTenant().getTid());
		if (null != tc) 
			params.setAgent(tc.getAgent()).setKey(tc.getKey());
		else
			params.setAgent(agent).setKey(key);
		params.setQuoteGroup(insurer.getCode());
		HttpUriRequest request = _requestUri(quoteResultPath, params, quoteResultTimeout);
		try {
			QuoteResult result = httpProxy.syncRequest(request, QuoteResult.JSON_HANDLER);
			if (result.getBusinessStatus() != 1)
				return Result.result(Code.REQUEST_FAILURE);
			return result.schema();
		} catch (IOException e) {
			logger.warn("bihu renew request failure!", e);
			if (e instanceof SocketTimeoutException)
				return Result.result(BtkjCode.RENEW_INFO_GET_TIMEOUT);
			return Result.result(Code.SYSTEM_ERROR);
		} catch (RequestFrequently e) {
			return Result.result(BtkjCode.BIHU_REQUEST_FREQUENTLY);
		}
	}
	
	@Override
	public Result<PolicyDetail> insureResult(EmployeeForm employeeForm, String license, int insurId) {
		BiHuInsurer insurer = biHuInsurerMapper.getByKey(insurId);
		if (null == insurer)
			return Result.result(BtkjCode.INSURER_NOT_EXIST);
		
		BiHuParams params = new BiHuParams(RequestType.INSURE_RESULT);
		params.setLicenseNo(license);
		params.setCustKey(DigestUtils.md5Hex(String.valueOf(employeeForm.getUser().getUid())));
		TenantConfig tc = tenantConfigMapper.getByKey(employeeForm.getTenant().getTid());
		if (null != tc) 
			params.setAgent(tc.getAgent()).setKey(tc.getKey());
		else
			params.setAgent(agent).setKey(key);
		params.setSubmitGroup(insurer.getCode());
		HttpUriRequest request = _requestUri(insureResultPath, params, insureResultTimeout);
		try {
			InsureResult result = httpProxy.syncRequest(request, InsureResult.JSON_HANDLER);
			if (result.getBusinessStatus() != 1)
				return Result.result(Code.REQUEST_FAILURE);
			if (result.getItem().getSubmitStatus() == 1)
				return Result.result(result.detail());
			if (result.getItem().getSubmitStatus() == 2)
				return Result.result(BtkjCode.INSURE_REPEAT, result.getItem().getSubmitResult());
			if (result.getItem().getSubmitStatus() == 3)
				return Result.result(BtkjCode.RENEW_INFO_GET_TIMEOUT);
			return Result.result(BtkjCode.INSURE_FAILURE, result.getItem().getSubmitResult());
		} catch (IOException e) {
			logger.warn("bihu renew request failure!", e);
			if (e instanceof SocketTimeoutException)
				return Result.result(BtkjCode.RENEW_INFO_GET_TIMEOUT);
			return Result.result(Code.SYSTEM_ERROR);
		} catch (RequestFrequently e) {
			return Result.result(BtkjCode.BIHU_REQUEST_FREQUENTLY);
		}
	}
	
	/**
	 * 请求超时时间
	 * 
	 * @param path
	 * @param params
	 * @param timeout
	 * @return
	 */
	private HttpUriRequest _requestUri(String path, BiHuParams params, int timeout) {
		URIBuilder builder = new URIBuilder();
		builder.setScheme(biHuSchema);
		builder.setHost(biHuHost);
		builder.setPath(path);
		
		StringBuilder str2encode = new StringBuilder();
		for (Entry<String, String> entry : params.entrySet()) {
			builder.addParameter(entry.getKey(), entry.getValue());
			str2encode.append(entry.getKey()).append(Consts.SYMBOL_EQUAL).append(entry.getValue()).append(Consts.SYMBOL_AND);
		}
		str2encode.deleteCharAt(str2encode.length() - 1);
		str2encode.append(params.getKey());
		builder.addParameter(SEC_CODE, DigestUtils.md5Hex(str2encode.toString()));
		try {
			HttpGet request = new HttpGet(builder.build());
			request.setConfig(RequestConfig.custom().setSocketTimeout(timeout).build());
			return request;
		} catch (URISyntaxException e) {
			throw new BusinessException(Code.SYSTEM_ERROR, "request uri build failure", e);
		}
	}
}
