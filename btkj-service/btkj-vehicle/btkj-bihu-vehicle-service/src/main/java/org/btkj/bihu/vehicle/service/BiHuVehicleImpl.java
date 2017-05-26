package org.btkj.bihu.vehicle.service;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.util.Map.Entry;

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
import org.btkj.bihu.vehicle.mybatis.entity.CityCode;
import org.btkj.bihu.vehicle.mybatis.entity.Insurer;
import org.btkj.bihu.vehicle.mybatis.entity.TenantConfig;
import org.btkj.bihu.vehicle.redis.CityCodeMapper;
import org.btkj.bihu.vehicle.redis.InsurerMapper;
import org.btkj.bihu.vehicle.redis.TenantConfigMapper;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.Renewal;
import org.btkj.pojo.enums.IDType;
import org.btkj.pojo.exception.BusinessException;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.insur.vehicle.CommercialInsurance;
import org.btkj.pojo.model.insur.vehicle.InsurUnit;
import org.btkj.pojo.model.insur.vehicle.Insurance;
import org.btkj.pojo.model.insur.vehicle.InsuranceSchema;
import org.btkj.pojo.model.insur.vehicle.Policy;
import org.btkj.pojo.model.insur.vehicle.Vehicle;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
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
	private InsurerMapper insurerMapper;
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
	public Result<Renewal> renewal(EmployeeForm employeeForm, String license, String name) {
		BiHuParams params = new BiHuParams(RequestType.RENEWL);
		params.setLicenseNo(license);
		return _doRenewal(employeeForm, params, name);
	}
	
	@Override
	public Result<Renewal> renewal(EmployeeForm employeeForm, String vin, String engine, String name) {
		BiHuParams params = new BiHuParams(RequestType.RENEWL);
		params.setCarVin(vin);
		params.setEngineNo(engine);
		return _doRenewal(employeeForm, params, name);
	}
	
	private Result<Renewal> _doRenewal(EmployeeForm employeeForm, BiHuParams params, String name) {
		params.setCustKey(DigestUtils.md5Hex(String.valueOf(employeeForm.getUser().getUid())));	// 用 uid 作为 custkey
		TenantConfig tc = tenantConfigMapper.getByKey(employeeForm.getTenant().getTid());
		if (null != tc) 
			params.setAgent(tc.getAgent()).setKey(tc.getKey());
		else
			params.setAgent(agent).setKey(key);
		CityCode cityCode = cityCodeMapper.getByKey(employeeForm.getTenant().getRegion());
		if (null == cityCode)
			return Result.result(BtkjCode.BIHU_CITY_UNSUPPORT);
		params.setCityCode(cityCode.getCid());
		HttpUriRequest request = _requestUri(renewInfoPath, params, renewInfoTimeout);
		try {
			RenewInfo info = httpProxy.syncRequest(request, RenewInfo.JSON_HANDLER);
			int status = info.getBusinessStatus();
			if (status == 2 || status == -10003 || status == -10002 || status == -10001 || status == -1000)
				return Result.result(BtkjCode.RENEW_INFO_GET_FAILURE);
			return Result.result(info.toRenewal());
		} catch (IOException e) {
			logger.warn("bihu renew request failure!", e);
			return Result.result(BtkjCode.RENEW_INFO_GET_TIMEOUT);
		} catch (RequestFrequently e) {
			return Result.result(BtkjCode.BIHU_REQUEST_FREQUENTLY);
		}
	}
	
	@Override
	public Result<Void> order(EmployeeForm employeeForm, int quoteMod, int insureMod, Renewal renewal) {
		TenantConfig tc = tenantConfigMapper.getByKey(employeeForm.getTenant().getTid());
		if (null == tc)
			return Result.result(BtkjCode.LANE_BIHU_NOT_OPENED);
		CityCode cityCode = cityCodeMapper.getByKey(employeeForm.getTenant().getRegion());
		if (null == cityCode)
			return Result.result(BtkjCode.BIHU_CITY_UNSUPPORT);
		BiHuParams params = new BiHuParams(RequestType.QUOTE);
		params.setAgent(tc.getAgent())
			.setKey(tc.getKey())
			.setQuoteGroup(quoteMod)
			.setSubmitGroup(insureMod)
			.setCityCode(cityCode.getCid())
			.setCustKey(DigestUtils.md5Hex(String.valueOf(employeeForm.getUser().getUid())));
		
		_buildOwner(params, renewal.getOwner());
		_buildInsured(params, renewal.getInsured());
		_buildInsurer(params, renewal.getInsurer());
		_buildVehicle(params, renewal.getVehicle());
		_buildSchema(params, renewal.getSchema());
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
		if (!StringUtils.hasText(insured.getMobile()))
			params.setInsuredMobile(insured.getMobile());
	}
	
	private void _buildInsurer(BiHuParams params, InsurUnit insurer) { 
		if (null == insurer)
			return;
		params.setHolderName(insurer.getName());
		params.setHolderIdType(_biHuIdType(insurer.getIdType()));
		params.setHolderIdCard(insurer.getIdNo());
		if (!StringUtils.hasText(insurer.getMobile()))
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
	
	private void _buildVehicle(BiHuParams params, Vehicle vehicle) {
		params.setLicenseNo(vehicle.getLicense());
		params.setEngineNo(vehicle.getEngine());
		params.setCarVin(vehicle.getVin());
		params.setRegisterDate(vehicle.getEnrollDate());
		params.setMoldName(vehicle.getModel());
		if (null != vehicle.getTransferDate())
			params.setTransferDate(vehicle.getTransferDate());
		params.setSeatCount(String.valueOf(vehicle.getSeatCount()));
	}
	
	private void _buildSchema(BiHuParams params, InsuranceSchema schema) {
		if (null != schema.getCommercial()) {
			if (null != schema.getCompulsive()) {
				params.setForceTax(String.valueOf(1));
				params.setForceTimeStamp(schema.getCompulsive().getStart());
			} else
				params.setForceTax(String.valueOf(0));
			params.setBizTimeStamp(schema.getCommercial().getStart());
		} else {
			if (null == schema.getCompulsive())
				throw new IllegalArgumentException("compulsory and commercial insur must choose one!");
			else {
				params.setForceTax(String.valueOf(2));
				params.setForceTimeStamp(schema.getCompulsive().getStart());
			}
		}
		
		CommercialInsurance cmi = schema.getCommercial();
		if (null != cmi) {
			// 车损险
			params.setCheSun(null != cmi.getDamage() ? 1 : 0);
			// 车损不计免赔
			params.setBuJiMianCheSun(null != cmi.getDamageOfDeductible() ? 1 : 0);
			// 第三者责任险
			params.setSanZhe(null != cmi.getThird() ? cmi.getThird().getQuota() : 0);
			// 第三者不计免赔
			params.setBuJiMianSanZhe(null == cmi.getThirdOfDeductible() ? 1 : 0);
			// 车上人员责任险(司机座位)
			params.setSiJi(null != cmi.getDriver() ? cmi.getDriver().getQuota() : 0);
			// 车上人员责任险(司机座位)不计免赔
			params.setBuJiMianSiJi(null != cmi.getDriverOfDeductible() ? 1 : 0);
			// 车上人员责任险(乘客座位)
			params.setChengKe(null != cmi.getPassenger() ? cmi.getPassenger().getQuota() : 0);
			// 车上人员责任险(乘客座位)不计免赔
			params.setBuJiMianChengKe(null != cmi.getPassengerOfDeductible() ? 1 : 0);
			// 盗抢险
			params.setDaoQiang(null != cmi.getRobbery() ? 1 : 0);
			// 盗抢险不计免赔
			params.setBuJiMianDaoQiang(null != cmi.getRobberyOfDeductible() ? 1 : 0);
			// 玻璃险
			params.setBoLi(null != cmi.getGlass() ? cmi.getGlass().getQuota() : 0);
			// 自燃
			params.setZiRan(null != cmi.getAutoFire() ? 1 : 0);
			// 自燃不计免赔
			params.setBuJiMianZiRan(null != cmi.getAutoFireOfDeductible() ? 1 : 0);
			// 划痕险
			params.setHuaHen(null != cmi.getScratch() ? cmi.getScratch().getQuota(): 0);
			// 划痕险不计免赔
			params.setBuJiMianHuaHen(null != cmi.getScratchOfDeductible() ? 1 : 0);
			// 涉水险
			params.setSheShui(null != cmi.getWading() ? 1 : 0);
			// 涉水险不计免赔
			params.setBuJiMianSheShui(null != cmi.getWadingOfDeductible() ? 1 : 0);
			// 指定专修厂
			Insurance insurance = cmi.getGarageDesignated();
			if (null != insurance) {
				params.setHcXiuLiChangType(insurance.getQuota());
				params.setHcXiuLiChang(insurance.getPrice());
			}
			// 无法找到第三方特约
			params.setHcSanFangTeYue(null != cmi.getUnknownThird() ? 1 : 0);
		}
	}
	
	@Override
	public Result<Policy> quoteResult(EmployeeForm employeeForm, String license, int insurId) {
		Insurer insurer = insurerMapper.getByKey(insurId);
		if (null == insurer)
			return Result.result(BtkjCode.INSURER_NOT_EXIST);
		
		BiHuParams params = new BiHuParams(RequestType.QUOTE_RESULT);
		params.setLicenseNo(license);
		params.setCustKey(DigestUtils.md5Hex(String.valueOf(employeeForm.getUser().getUid())));
		TenantConfig tc = tenantConfigMapper.getByKey(employeeForm.getTenant().getTid());
		if (null != tc) 
			params.setAgent(tc.getAgent()).setKey(tc.getKey());
		else
			params.setAgent(agent).setKey(key);
		params.setQuoteGroup(insurId);
		HttpUriRequest request = _requestUri(quoteResultPath, params, quoteResultTimeout);
		try {
			QuoteResult result = httpProxy.syncRequest(request, QuoteResult.JSON_HANDLER);
			if (result.getBusinessStatus() != 1)
				return Result.result(BtkjCode.QUOTE_FAILURE);
			return Result.result(result.toQuote());
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
	public Result<Void> insureResult(EmployeeForm employeeForm, String license, int insurId) {
		Insurer insurer = insurerMapper.getByKey(insurId);
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
		params.setSubmitGroup(insurId);
		HttpUriRequest request = _requestUri(insureResultPath, params, insureResultTimeout);
		try {
			InsureResult result = httpProxy.syncRequest(request, InsureResult.JSON_HANDLER);
			if (result.getBusinessStatus() != 1 || null == result.getItem())
				return Result.result(BtkjCode.QUOTE_FAILURE);
			if (result.getItem().getSubmitStatus() == 4)
				return Result.result(BtkjCode.NOT_QUOTE);
			return null;
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
