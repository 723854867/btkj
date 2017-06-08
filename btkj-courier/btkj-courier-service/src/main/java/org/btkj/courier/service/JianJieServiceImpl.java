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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.btkj.courier.api.JianJieService;
import org.btkj.courier.pojo.JianJieResp;
import org.btkj.courier.pojo.JianJieUser;
import org.btkj.pojo.entity.User;
import org.rapid.util.common.serializer.SerializeUtil;
import org.rapid.util.lang.DateUtils;
import org.rapid.util.lang.StringUtils;
import org.rapid.util.net.http.HttpProxy;
import org.rapid.util.net.http.handler.AsyncRespHandler;
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
	@Resource
	private HttpProxy httpProxy;
	
	@Override
	public void addUser(User user) {
		try {
			URIBuilder uriBuilder = _uri(urlUserAdd);
			URI uri = uriBuilder.build();
			HttpPost request = new HttpPost(uri);
			request.setHeader(HttpHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE);
			user.setMobile(String.valueOf(StringUtils.getNationalNumber(user.getMobile())));
			request.setEntity(new StringEntity(SerializeUtil.JsonUtil.GSON.toJson(new JianJieUser(user))));
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
	
	private URIBuilder _uri(String url) throws URISyntaxException {
		URIBuilder uri = new URIBuilder(url);
		long timestamp = DateUtils.currentTime();
		StringBuilder builder = new StringBuilder();
		builder.append(timestamp).append("000000").append(timestamp).append("CarCorder");
		String sign = DigestUtils.md5Hex(builder.toString()).toUpperCase();
		uri.addParameter("Timestamp", String.valueOf(timestamp));
		uri.addParameter("Sign", sign);
		return uri;
	}
}
