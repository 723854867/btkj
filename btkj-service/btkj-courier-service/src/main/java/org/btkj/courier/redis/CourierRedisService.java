package org.btkj.courier.redis;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.btkj.courier.Config;
import org.btkj.pojo.model.CaptchaReceiver;
import org.btkj.pojo.model.CaptchaVerifier;
import org.rapid.data.storage.redis.Redis;
import org.rapid.util.common.Constants;
import org.rapid.util.common.Env;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.key.KeyUtil;
import org.rapid.util.common.message.Result;
import org.rapid.util.net.http.HttpProxy;
import org.rapid.util.net.http.handler.AsyncRespHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CourierRedisService {
	
	private static final Logger logger = LoggerFactory.getLogger(CourierRedisService.class);
	
	private String CAPTCHA				= "string:app:{0}:captcha:record:{1}";	// 保存验证码：0-app类型，1-手机号
	private String CAPTCHA_COUNT		= "string:app:{0}:captcha:count:{1}";	// 验证码获取次数：0-app类型，1-手机号
	
	private String PARAM_TPL_VALUE		= "tpl_value";
	private String PARAM_MOBILE			= "mobile";
	
	@Resource
	private Redis redis;
	@Resource
	private HttpProxy httpProxy;
	@Value("${yunPian.apiKey}")
	private String yunPianKey;
	@Value("${yunpian.captcha.tplId}")
	private int captchaTplId;
	@Value("${yunpian.captcha.tplSendUrl}")
	private static String tplSendUrl;
	
	private NameValuePair API_KEY;
	private NameValuePair TPL_ID;
	private CaptchaSendHandler handler;
	
	@PostConstruct
	private void init() {
		handler = new CaptchaSendHandler();
		API_KEY = new BasicNameValuePair("apiKey", yunPianKey);
		TPL_ID = new BasicNameValuePair("tpl_id", String.valueOf(captchaTplId));
	}
	
	public Result<String> captchaObtain(CaptchaReceiver receiver) {
		String captchaKey = _captchaKey(receiver);
		String captchaCountKey = _captchaCountKey(receiver);
		
		String captcha = KeyUtil.randomCaptcha(Config.getCaptchaLen());
		int flag = (int) redis.captchaObtain(captchaKey, captchaCountKey, captcha, 
				Config.getCaptchaLifeTime(), Config.getCaptchaMaximum(), Config.getCaptchaCountLifeTime());
		if (flag != 0)
			return Result.result(flag);
			
		Env env = Config.getEnv();
		switch (env) {
		case LOCAL:
		case TEST:
			return Result.result(flag, captcha);
		case ONLINE:
			_sendCaptcha(receiver.getIdentity(), captcha);
			return Result.result(flag);
		default:
			return Result.result(Code.SYSTEM_ERROR);
		}
	}
	
	public Result<String> captchaVerifier(CaptchaVerifier verifier) {
		if (!redis.delIfEquals(_captchaKey(verifier), verifier.getCaptcha()))
			return Result.result(-1);
		return Result.result(0);
	}
	
	private void _sendCaptcha(String mobile, String captcha) {
		String tplValue = null;
		try {
			tplValue = URLEncoder.encode("#code#", Constants.UTF_8.name()) + "=" + URLEncoder.encode(captcha, Constants.UTF_8.name());
		} catch (UnsupportedEncodingException e) {
			logger.error("captcha url encode failure!", e);
			return;
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>(3);
		params.add(API_KEY);
		params.add(TPL_ID);
		params.add(new BasicNameValuePair(PARAM_TPL_VALUE, tplValue));
		params.add(new BasicNameValuePair(PARAM_MOBILE, mobile));
		HttpPost method = new HttpPost(tplSendUrl);
		httpProxy.asyncRequest(method, handler);
	}
	
	public String _captchaKey(CaptchaReceiver receiver) {
		return MessageFormat.format(CAPTCHA, String.valueOf(receiver.getAppId()), receiver.getIdentity());
	}
	
	public String _captchaCountKey(CaptchaReceiver receiver) {
		return MessageFormat.format(CAPTCHA_COUNT, String.valueOf(receiver.getAppId()), receiver.getIdentity());
	}
	
	private class CaptchaSendHandler implements AsyncRespHandler {
		@Override
		public void completed(HttpResponse response) {
			StatusLine statusLine = response.getStatusLine();
			HttpEntity entity = response.getEntity();
			try {
				if (null == entity)
					return;
				if (statusLine.getStatusCode() >= 300) 
					logger.error("captcha send failure!{}, {} --- {}", statusLine.getStatusCode(), statusLine.getReasonPhrase(), EntityUtils.toString(entity));
				else
					logger.info("captcha send success --- {}", EntityUtils.toString(entity));
			} catch (ParseException | IOException e) {
				e.printStackTrace();
			} finally {
				try {
					EntityUtils.consume(entity);
				} catch (IOException e) {
					logger.warn("Http entity release failure!", e);
				}
			}
		}

		@Override
		public void failed(Exception ex) {
			logger.error("captcha send exception!", ex);
		}

		@Override
		public void cancelled() {
			logger.warn("captcha send cancelled!");
		}
	}
}
