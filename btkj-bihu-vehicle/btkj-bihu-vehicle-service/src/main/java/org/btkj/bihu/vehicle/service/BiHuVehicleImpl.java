package org.btkj.bihu.vehicle.service;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.btkj.bihu.vehicle.api.BiHuVehicle;
import org.btkj.bihu.vehicle.domain.BiHuParams;
import org.btkj.bihu.vehicle.domain.BiHuVehicleInfo;
import org.btkj.bihu.vehicle.domain.InsureResult;
import org.btkj.bihu.vehicle.domain.QuoteResp;
import org.btkj.bihu.vehicle.domain.QuoteResult;
import org.btkj.bihu.vehicle.domain.RenewInfo;
import org.btkj.bihu.vehicle.domain.RequestType;
import org.btkj.bihu.vehicle.exception.RequestFrequently;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.Renewal;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.enums.CommercialInsuranceType;
import org.btkj.pojo.enums.IDType;
import org.btkj.pojo.enums.LicenseType;
import org.btkj.pojo.exception.BusinessException;
import org.btkj.pojo.info.VehicleInfo;
import org.btkj.pojo.model.PolicySchema;
import org.btkj.pojo.model.VehicleAuditModel;
import org.btkj.pojo.param.VehicleOrderParam;
import org.btkj.pojo.param.VehicleOrderParam.InsuranceItem;
import org.btkj.pojo.param.VehicleOrderParam.Unit;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.lang.StringUtil;
import org.rapid.util.net.http.HttpProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("biHuVehicle")
public class BiHuVehicleImpl implements BiHuVehicle {
	
	private static final Logger logger = LoggerFactory.getLogger(BiHuVehicleImpl.class);
	
	private static final String SEC_CODE					= "SecCode";
	private static final String TWO							= "2";
	private static final String ONE							= "1";
	private static final String ZERO						= "0";
	private static final String ONE_NEG						= "-1";
	
	@Resource
	private HttpProxy httpProxy;
	
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
	@Value("${bihu.vehicleInfo.path}")
	private String vehicleInfoPath;
	@Value("${bihu.vehicleInfo.timeout}")
	private int vehicleInfoTimeout;
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
	public Result<Renewal> renewal(TenantPO tenant, int uid, String license, int cityCode) {
		BiHuParams params = new BiHuParams(RequestType.RENEWL);
		params.setLicenseNo(license);
		return _doRenewal(tenant, uid, params, cityCode, LicenseType.RENEWAL_CAR);
	}
	
	@Override
	public Result<Renewal> renewal(TenantPO tenant, int uid, String vin, String engine, int cityCode) {
		BiHuParams params = new BiHuParams(RequestType.RENEWL);
		params.setCarVin(vin);
		params.setEngineNo(engine);
		return _doRenewal(tenant, uid, params, cityCode, LicenseType.SECONDARY_CAR);
	}
	
	private Result<Renewal> _doRenewal(TenantPO tenant, int uid, BiHuParams params, int cityCode, LicenseType licenseType) {
		params.setCustKey(DigestUtils.md5Hex(String.valueOf(uid)))	// 用 uid 作为 custkey
				.setCityCode(cityCode);
		if (StringUtil.hasText(tenant.getBiHuAgent(), tenant.getBiHuKey()))
			params.setAgent(tenant.getBiHuAgent()).setKey(tenant.getBiHuKey());
		else
			params.setAgent(agent).setKey(key);
		HttpUriRequest request = _requestUri(renewInfoPath, params, renewInfoTimeout);
		try {
			RenewInfo info = httpProxy.syncRequest(request, RenewInfo.JSON_HANDLER);
			int status = info.getBusinessStatus();
			if (status == 2 || status == -10003 || status == -10002 || status == -10001 || status == -1000)
				return Result.result(BtkjCode.RENEW_INFO_GET_FAILURE);
			
			Renewal renewal = info.toRenewal();
			if (null != renewal.getTips() && StringUtil.hasText(renewal.getTips().getName())) {
				BiHuVehicleInfo vehicleInfo = _vehicleInfo(params, renewal.getTips().getName(), licenseType);
				if (null != vehicleInfo)
					renewal.getTips().setTransmissionName(vehicleInfo.searchAlia(renewal.getTips().getBiHuJYId()));
			}
			return status == 3 ? Result.result(BtkjCode.RENEW_INFO_VEHICLE_ONLY, renewal) : Result.result(renewal);
		} catch (IOException e) {
			logger.warn("bihu renew request failure!", e);
			return Result.result(BtkjCode.RENEW_INFO_GET_TIMEOUT);
		} catch (RequestFrequently e) {
			return Result.result(BtkjCode.BIHU_REQUEST_FREQUENTLY);
		}
	}
	
	private BiHuVehicleInfo _vehicleInfo(BiHuParams renewParam , String moldName, LicenseType licenseType) {
		BiHuParams params = new BiHuParams(RequestType.VEHICLE_INFO);
		params.setCustKey(renewParam.getCustKey()).setAgent(renewParam.getAgent()).setKey(renewParam.getKey())
				.setCityCode(renewParam.getCityCode()).setMoldName(moldName);
		switch (licenseType) {
		case RENEWAL_CAR:
			params.setLicenseNo(renewParam.getLicenseNo());
			break;
		case SECONDARY_CAR:
			params.setCarVin(renewParam.getCarVin()).setEngineNo(renewParam.getKey());
			break;
		default:
			break;
		}
		HttpUriRequest request = _requestUri(vehicleInfoPath, params, vehicleInfoTimeout);
		try {
			BiHuVehicleInfo info = httpProxy.syncRequest(request, BiHuVehicleInfo.JSON_HANDLER);
			return info.getBusinessStatus() == 1 ? info : null;
		} catch (IOException e) {
			logger.warn("bihu vehicle info request failure!", e);
			return null;
		}
	}
	
	@Override
	public Result<List<VehicleInfo>> vehicleInfos(int uid, TenantPO tenant, String license, String moldName, int cityCode) {
		BiHuParams params = new BiHuParams(RequestType.VEHICLE_INFO);
		params.setCustKey(DigestUtils.md5Hex(String.valueOf(uid))).setCityCode(cityCode).setLicenseNo(license).setMoldName(moldName);
		if (StringUtil.hasText(tenant.getBiHuAgent(), tenant.getBiHuKey()))
			params.setAgent(tenant.getBiHuAgent()).setKey(tenant.getBiHuKey());
		else
			params.setAgent(agent).setKey(key);
		HttpUriRequest request = _requestUri(vehicleInfoPath, params, vehicleInfoTimeout);
		try {
			BiHuVehicleInfo info = httpProxy.syncRequest(request, BiHuVehicleInfo.JSON_HANDLER);
			int status = info.getBusinessStatus();
			if (status == -1000 || status == -10001 || status == -10003)
				return Result.result(BtkjCode.VEHICLE_INFO_REQUEST_FAILURE, info.getStatusMessage());
			return null;
		} catch (IOException e) {
			logger.warn("bihu vehicle info request failure!", e);
			return Result.result(BtkjCode.RENEW_INFO_GET_TIMEOUT);
		}
	}
	
	@Override
	public Result<Void> order(String agent, String key, int uid, int quoteMod, int insureMod, int cityCode, VehicleOrderParam param) {
		BiHuParams params = new BiHuParams(RequestType.QUOTE);
		params.setAgent(agent).setKey(key).setQuoteGroup(quoteMod).setSubmitGroup(insureMod)
			.setCityCode(cityCode).setCustKey(DigestUtils.md5Hex(String.valueOf(uid)));
		
		_buildOwner(params, param.getOwner());
		_buildInsured(params, param.getInsured());
		_buildInsurer(params, param.getInsurer());
		_buildVehicle(params, param);
		_buildSchema(params, param);
		HttpUriRequest request = _requestUri(biHuQuotePath, params, quoteTimeout);
		try {
			QuoteResp resp = httpProxy.syncRequest(request, QuoteResp.JSON_HANDLER);
			if (resp.getBusinessStatus() != 1)
				return Result.result(BtkjCode.QUOTE_FAILURE, resp.getStatusMessage());
			return Result.success();
		} catch (IOException e) {
			logger.warn("bihu quote request failure!", e);
			return Result.result(BtkjCode.QUOTE_FAILURE, "系统错误");
		}
	}
	
	private void _buildOwner(BiHuParams params, Unit owner) { 
		if (null == owner)
			return;
		params.setCarOwnerName(owner.getName());
		params.setIdCard(owner.getIdNo());
		params.setOwnerIdCardType(_biHuIdType(owner.getIdType()));
	}
	
	private void _buildInsured(BiHuParams params, Unit insured) { 
		if (null == insured)
			return;
		params.setInsuredName(insured.getName());
		params.setInsuredIdType(_biHuIdType(insured.getIdType()));
		params.setInsuredIdCard(insured.getIdNo());
		if (StringUtil.hasText(insured.getMobile()))
			params.setInsuredMobile(insured.getMobile());
	}
	
	private void _buildInsurer(BiHuParams params, Unit insurer) { 
		if (null == insurer)
			return;
		params.setHolderName(insurer.getName());
		params.setHolderIdType(_biHuIdType(insurer.getIdType()));
		params.setHolderIdCard(insurer.getIdNo());
		if (StringUtil.hasText(insurer.getMobile()))
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
	
	private void _buildVehicle(BiHuParams params, VehicleOrderParam param) {
		params.setLicenseNo(param.getLicense());
		params.setEngineNo(param.getEngine());
		params.setCarVin(param.getVin());
		params.setRegisterDate(DateUtil.convert(param.getEnrollDate(), DateUtil.YYYY_MM_DD_HH_MM_SS, DateUtil.YYYY_MM_DD, DateUtil.TIMEZONE_GMT_8));
		params.setMoldName(param.getName());
		if (null != param.getIssueDate())
			params.setTransferDate(param.getIssueDate());
		params.setSeatCount(String.valueOf(param.getSeat()));
	}
	
	private void _buildSchema(BiHuParams params, VehicleOrderParam param) {
		if (null != param.getCommercialStart()) {
			if (null != param.getCompulsoryStart()) {
				params.setForceTax(String.valueOf(1));
				params.setForceTimeStamp(String.valueOf((int) (DateUtil.getTime(param.getCompulsoryStart(), DateUtil.YYYY_MM_DD_HH_MM_SS) / 1000)));
			} else
				params.setForceTax(String.valueOf(0));
			params.setBizTimeStamp(String.valueOf((int) (DateUtil.getTime(param.getCommercialStart(), DateUtil.YYYY_MM_DD_HH_MM_SS) / 1000)));
		} else {
			if (null == param.getCompulsoryStart())
				throw new IllegalArgumentException("compulsory and commercial insur must choose one!");
			else {
				params.setForceTax(String.valueOf(2));
				params.setForceTimeStamp(String.valueOf((int) (DateUtil.getTime(param.getCompulsoryStart(), DateUtil.YYYY_MM_DD_HH_MM_SS) / 1000)));
			}
		}
		
		Map<CommercialInsuranceType, InsuranceItem> commercial = param.getInsurances();
		for (CommercialInsuranceType type : CommercialInsuranceType.values()) {
			InsuranceItem insurance = null == commercial ? null : commercial.get(type);
			switch (type) {
			case DAMAGE:
				params.setCheSun(null != insurance ? ONE : ZERO);
				break;
			case DAMAGE_DEDUCTIBLE:
				params.setBuJiMianCheSun(null != insurance ? ONE : ZERO);
				break;
			case THIRD:
				params.setSanZhe(null != insurance ? insurance.getQuota() : ZERO);
				break;
			case THIRD_DEDUCTIBLE:
				params.setBuJiMianSanZhe(null != insurance ? ONE : ZERO);
				break;
			case DRIVER:
				params.setSiJi(null != insurance ? insurance.getQuota() : ZERO);
				break;
			case DRIVER_DEDUCTIBLE:
				params.setBuJiMianSiJi(null != insurance ? ONE : ZERO);
				break;
			case PASSENGER:
				params.setChengKe(null != insurance ? insurance.getQuota() : ZERO);
				break;
			case PASSENGER_DEDUCTIBLE:
				params.setBuJiMianChengKe(null != insurance ? ONE : ZERO);
				break;
			case ROBBERY:
				params.setDaoQiang(null != insurance ? ONE : ZERO);
				break;
			case ROBBERY_DEDUCTIBLE:
				params.setBuJiMianDaoQiang(null != insurance ? ONE : ZERO);
				break;
			case GLASS:
				if (null != insurance) {
					if (!insurance.getQuota().equals(ONE) && !insurance.getQuota().equals(TWO))
						params.setBoLi(ZERO);
					else
						params.setBoLi(insurance.getQuota());
				} else
					params.setBoLi(ZERO);
				break;
			case AUTO_FIRE:
				params.setZiRan(null != insurance ? ONE : ZERO);
				break;
			case AUTO_FIRE_DEDUCTIBLE:
				params.setBuJiMianZiRan(null != insurance ? ONE : ZERO);
				break;
			case SCRATCH:
				params.setHuaHen(null != insurance ? insurance.getQuota() : ZERO);
				break;
			case SCRATCH_DEDUCTIBLE:
				params.setBuJiMianHuaHen(null != insurance ? ONE : ZERO);
				break;
			case WADDING:
				params.setSheShui(null != insurance ? ONE : ZERO);
				break;
			case WADDING_DEDUCTIBLE:
				params.setBuJiMianSheShui(null != insurance ? ONE : ZERO);
				break;
			case GARAGE_DESIGNATED:
				if (null != insurance) {
					if (!insurance.getQuota().equals(ONE_NEG) && !insurance.getQuota().equals(ZERO) && !insurance.getQuota().equals(ONE))
						params.setHcXiuLiChangType(ONE_NEG);
					else
						params.setHcXiuLiChangType(insurance.getQuota());
					params.setHcXiuLiChang(insurance.getPrice());
				}
				break;
			case UNKNOWN_THIRD:
				params.setHcSanFangTeYue(null != insurance ? ONE : ZERO);
				break;
			default:
				break;
			}
		}
		params.setBuJiMianJingShenSunShi(ZERO); // 不计免精神损失不做该项投保
	}
	
	@Override
	public Result<PolicySchema> quoteResult(TenantPO tenant, int uid, String license, int insurer) {
		BiHuParams params = new BiHuParams(RequestType.QUOTE_RESULT);
		params.setLicenseNo(license).setCustKey(DigestUtils.md5Hex(String.valueOf(uid))).setAgent(tenant.getBiHuAgent())
				.setKey(tenant.getBiHuKey()).setQuoteGroup(insurer);
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
	public Result<VehicleAuditModel> insureResult(TenantPO tenant, int uid, String license, int insurer) {
		BiHuParams params = new BiHuParams(RequestType.INSURE_RESULT);
		params.setLicenseNo(license).setCustKey(DigestUtils.md5Hex(String.valueOf(uid)))
			.setAgent(tenant.getBiHuAgent()).setKey(tenant.getBiHuKey()).setSubmitGroup(insurer);
		HttpUriRequest request = _requestUri(insureResultPath, params, insureResultTimeout);
		try {
			InsureResult result = httpProxy.syncRequest(request, InsureResult.JSON_HANDLER);
			if (result.getBusinessStatus() != 1)
				return Result.result(Code.REQUEST_FAILURE);
			if (result.getItem().getSubmitStatus() == 1)
				return Result.result(result.auditModel());
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
