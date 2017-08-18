package org.btkj.courier.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.btkj.courier.api.JianJieService;
import org.btkj.courier.pojo.JianJieResp;
import org.btkj.courier.pojo.JianJieUser;
import org.btkj.pojo.info.JianJiePoliciesInfo;
import org.rapid.util.common.serializer.SerializeUtil;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.net.http.HttpProxy;
import org.rapid.util.net.http.handler.AsyncRespHandler;
import org.rapid.util.net.http.handler.SyncJsonRespHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service("jianJieService")
public class JianJieServiceImpl implements JianJieService {
	
	private static final Logger logger = LoggerFactory.getLogger(JianJieServiceImpl.class);

	@Value("${url.user.add}")
	private String urlUserAdd;
	@Value("${url.vehicle.policies}")
	private String urlVehiclePolicies;
	@Resource
	private HttpProxy httpProxy;
	
	@Override
	public void addEmployee(String name, String identity, int employeeId) {
		try {
			URIBuilder uriBuilder = new URIBuilder(urlUserAdd);
			long timestamp = DateUtil.currentTime();
			uriBuilder.addParameter("Timestamp", String.valueOf(timestamp));
			uriBuilder.addParameter("Sign", _userAddSign(timestamp));
			URI uri = uriBuilder.build();
			HttpPost request = new HttpPost(uri);
			request.setHeader(HttpHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE);
			request.setEntity(new StringEntity(SerializeUtil.JsonUtil.GSON.toJson(new JianJieUser(name, identity, employeeId))));
			httpProxy.asyncRequest(request, new AsyncRespHandler() {
				@Override
				public void cancelled() {
					logger.error("简捷添加业务员请求被取消！");
				}
				@Override
				public void failed(Exception ex) {
					logger.error("简捷添加业务员请求失败！", ex);
				}
				@Override
				public void completed(HttpResponse response) {
					StatusLine statusLine = response.getStatusLine();
					HttpEntity entity = response.getEntity();
					try {
						if (null == entity)
							return;
						if (statusLine.getStatusCode() >= 300) 
							logger.error("简捷业务员添加失败!{}, {} --- {}", statusLine.getStatusCode(), statusLine.getReasonPhrase(), EntityUtils.toString(entity));
						
						JianJieResp resp = SerializeUtil.JsonUtil.GSON.fromJson(EntityUtils.toString(entity), JianJieResp.class);
						if (!resp.isSuccessStatus())
							logger.warn("简捷业务员添加异常：{}", resp.getErrorMessage());
						logger.info("简捷业务员添加成功");
					} catch (ParseException | IOException e) {
						logger.error("简捷添加业务员响应解析失败！");
					} finally {
						try {
							EntityUtils.consume(entity);
						} catch (IOException e) {
							logger.warn("Http entity release failure!", e);
						}
					}
				}
			});
		} catch (URISyntaxException | UnsupportedEncodingException e) {
			logger.error("简捷业务员添加失败！", e);
			return;
		}
	}
	
	@Override
	public JianJiePoliciesInfo vehiclePolicies(String jianJieId, String begin, String end) {
		try {
			URIBuilder uriBuilder = new URIBuilder(urlVehiclePolicies);
			long timestamp = DateUtil.currentTime();
			uriBuilder.addParameter("Timestamp", String.valueOf(timestamp));
			uriBuilder.addParameter("Sign", _vehiclePoliciesSign(timestamp, begin, end));
			uriBuilder.addParameter("CompanyId", "-1");
			uriBuilder.addParameter("DeptId", jianJieId);
			uriBuilder.addParameter("TimeStr", begin + end);
			URI uri = uriBuilder.build();
			HttpGet request = new HttpGet(uri);
			request.setHeader(HttpHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE);
			JianJiePoliciesInfo info = httpProxy.syncRequest(request, SyncJsonRespHandler.build(JianJiePoliciesInfo.class));
			return info;
		} catch (URISyntaxException | IOException e) {
			logger.warn("简捷保单获取失败，简捷ID-{}", jianJieId, e);
			return null;
		}
	}
	
	private String _userAddSign(long timestamp) {
		StringBuilder builder = new StringBuilder();
		builder.append(timestamp).append("000000").append(timestamp).append("CarCorder");
		return DigestUtils.md5Hex(builder.toString());
	}
	
	private String _vehiclePoliciesSign(long timestamp, String begin, String end) {
		StringBuilder builder = new StringBuilder();
		builder.append("-1").append(begin).append(end)
			.delete(16, builder.length())
			.append(timestamp).append("CarCorder");
		return DigestUtils.md5Hex(builder.toString());
	}
}
