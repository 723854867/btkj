package org.btkj.lebaoba.vehicle.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.btkj.lebaoba.vehicle.api.LeBaoBaVehicle;
import org.btkj.lebaoba.vehicle.domain.AuditSubmit;
import org.btkj.lebaoba.vehicle.domain.BasicResult;
import org.btkj.lebaoba.vehicle.domain.InsureResult;
import org.btkj.lebaoba.vehicle.domain.QuoteResult;
import org.btkj.lebaoba.vehicle.domain.QuoteResult.Attach;
import org.btkj.lebaoba.vehicle.domain.QuoteSubmit;
import org.btkj.lebaoba.vehicle.domain.VehicleInfos;
import org.btkj.lebaoba.vehicle.domain.VehicleSubmit;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.exception.BusinessException;
import org.btkj.pojo.info.VehicleInfo;
import org.btkj.pojo.model.PolicySchema;
import org.btkj.pojo.param.VehicleOrderParam;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.common.serializer.SerializeUtil;
import org.rapid.util.crypto.DesUtil;
import org.rapid.util.lang.StringUtil;
import org.rapid.util.net.http.HttpProxy;
import org.rapid.util.net.http.handler.SyncStrRespHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("leBaoBaVehicle")
public class LeBaoBaVehicleImpl implements LeBaoBaVehicle {
	
	private static final Logger logger = LoggerFactory.getLogger(LeBaoBaVehicleImpl.class);
	
	@Resource
	private HttpProxy httpProxy;
	@Value("${lebaoba.username}")
	private String username;
	@Value("${lebaoba.password}")
	private String  password;
	@Value("${lebaoba.des.key}")
	private String desKey;
	@Value("${lebaoba.schema}")
	private String schema;
	@Value("${lebaoba.host}")
	private String host;
	@Value("${lebaoba.port}")
	private int port;
	@Value("${lebaba.vehicle.info.path}")
	private String vehicleInfoPath;
	@Value("${lebaba.vehicle.info.timeout}")
	private int vehicleInfoTimeout;
	@Value("${lebaba.quote.path}")
	private String quotePath;
	@Value("${lebaba.quote.timeout}")
	private int quoteTimeout;
	@Value("${lebaba.insure.path}")
	private String insurePath;
	@Value("${lebaba.insure.timeout}")
	private int insureTimeout;
	
	@Override
	public List<VehicleInfo> vehicleInfos(String username, String password, String vin) {
		VehicleSubmit submit = new VehicleSubmit();
		if (!StringUtil.hasText(username, password)) {
			username = this.username;
			password = this.password;
		}
		submit.setUsername(username);
		submit.setPassword(password);
		submit.setVin(vin);
		String xml = SerializeUtil.XmlUtil.beanToXml(submit, Consts.UTF_8.name());
		xml = DesUtil.EncryptDES(xml, desKey);
		xml = new String(Base64.encodeBase64(xml.getBytes()));
		
		HttpUriRequest request = _request(vehicleInfoPath, xml, vehicleInfoTimeout);
		String result;
		try {
			result = httpProxy.syncRequest(request, SyncStrRespHandler.INSTANCE);
		} catch (IOException e) {
			logger.error("乐保吧车辆信息获取失败!", e);
			return null;
		}
		BasicResult br = SerializeUtil.XmlUtil.xmlToBean(result, BasicResult.class);
		VehicleInfos infos = SerializeUtil.XmlUtil.xmlToBean(new String(Base64.decodeBase64(br.getContent())), VehicleInfos.class);
		return infos.infos();
	}

	@Override
	public Result<PolicySchema> order(String username, String password, String insurer, boolean insure, VehicleOrderParam param) {
		Result<QuoteResult> qr = _quote(username, password, insurer, param);
		Result<InsureResult> ir = null;
		if (insure && qr.isSuccess()) 
			ir = _insure( username, password, qr.attach().getAttach());
		if (!qr.isSuccess())
			return Result.result(qr.getCode(), qr.getDesc(), null);
		PolicySchema schema = qr.attach().getAttach().schema();
		Result<PolicySchema> result = Result.result(schema);
		if (insure) {
			if (ir.isSuccess()) {
				schema.setCommercialNo(ir.attach().getAttach().getCommercePolicyNo());
				schema.setCompulsoryNo(ir.attach().getAttach().getCompulsoryPolicyNo());
			} else {
				result.setCode(ir.getCode());
				result.setDesc(ir.getDesc());
			}
		}
		return result;
	}
	
	private Result<QuoteResult> _quote(String username, String password, String insurer, VehicleOrderParam param) {
		QuoteSubmit submit = QuoteSubmit.instance(username, password, insurer, param);
		String xml = SerializeUtil.XmlUtil.beanToXml(submit, Consts.UTF_8.name());
		xml = DesUtil.EncryptDES(xml, desKey);
		xml = new String(Base64.encodeBase64(xml.getBytes()));
		
		HttpUriRequest request = _request(quotePath, xml, quoteTimeout);
		String result;
		try {
			result = httpProxy.syncRequest(request, SyncStrRespHandler.INSTANCE);
		} catch (IOException e) {
			logger.error("乐保吧报价提交失败!", e);
			return Result.result(BtkjCode.QUOTE_FAILURE, "报价请求失败！");
		}
		
		BasicResult br = SerializeUtil.XmlUtil.xmlToBean(result, BasicResult.class);
		QuoteResult or = SerializeUtil.XmlUtil.xmlToBean(new String(Base64.decodeBase64(br.getContent())), QuoteResult.class);
		if (!or.isSuccess())
			return Result.result(BtkjCode.QUOTE_FAILURE, or.error());
		if (1 != or.getAttach().getAiErrorInfo().getQuoteResult())
			return Result.result(BtkjCode.QUOTE_FAILURE, or.getAttach().getAiErrorInfo().getErrorInfo());
		return Result.result(or);
	}
	
	private Result<InsureResult> _insure(String username, String password, Attach quoteResult) {
		AuditSubmit submit = new AuditSubmit(username, password, quoteResult.getPolicyNo(), quoteResult.getCommercePolicyNo(), quoteResult.getCompulsoryPolicyNo());
		String xml = SerializeUtil.XmlUtil.beanToXml(submit, Consts.UTF_8.name());
		xml = DesUtil.EncryptDES(xml, desKey);
		xml = new String(Base64.encodeBase64(xml.getBytes()));
		
		String result;
		HttpUriRequest request = _request(insurePath, xml, insureTimeout);
		try {
			result = httpProxy.syncRequest(request, SyncStrRespHandler.INSTANCE);
		} catch (IOException e) {
			logger.error("乐保吧核保提交失败!", e);
			return Result.result(BtkjCode.INSURE_FAILURE, "核保请求失败！");
		}
		
		BasicResult br = SerializeUtil.XmlUtil.xmlToBean(result, BasicResult.class);
		InsureResult ir = SerializeUtil.XmlUtil.xmlToBean(new String(Base64.decodeBase64(br.getContent())), InsureResult.class);
		if (!ir.isSuccess())
			return Result.result(BtkjCode.INSURE_FAILURE, ir.error());
		org.btkj.lebaoba.vehicle.domain.InsureResult.Attach attach = ir.getAttach();
		if (1 != attach.getPolicyStatus()) {
			StringBuilder reason = new StringBuilder();
			if (-1 != attach.getCommerceCheckStatus())
				reason.append("商业险核保信息：【").append(ir.getAttach().getCommerceCheckReply()).append("】\r\n");
			if (-1 != attach.getCompulsoryCheckStatus())
				reason.append("交强险核保信息：【").append(ir.getAttach().getCompulsoryCheckReply()).append("】");
			return Result.result(BtkjCode.INSURE_FAILURE, reason.toString());
		}
		return Result.result(ir);
	}
	
	private HttpUriRequest _request(String path, String content, int timeout) {
		URIBuilder builder = new URIBuilder();
		builder.setScheme(schema);
		builder.setHost(host);
		builder.setPort(port);
		builder.setPath(path);
		
		try {
			HttpPost request = new HttpPost(builder.build());
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("xmlData", content));
			request.setEntity(new UrlEncodedFormEntity(list, Charset.forName("UTF-8")));
			request.setConfig(RequestConfig.custom().setSocketTimeout(timeout).build());
			return request;
		} catch (URISyntaxException e) {
			throw new BusinessException(Code.SYSTEM_ERROR, "request uri build failure", e);
		}
	}
}
