package org.btkj.courier.redis;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
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
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.btkj.courier.Config;
import org.btkj.courier.pojo.submit.QuotaNoticeSubmit;
import org.btkj.pojo.bo.Insurance;
import org.btkj.pojo.bo.PolicySchema;
import org.btkj.pojo.enums.CommercialInsuranceType;
import org.btkj.pojo.po.VehicleOrder;
import org.rapid.data.storage.redis.Redis;
import org.rapid.util.common.Consts;
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
	private String QUOTA_MODEL			= "交强险{0}，车船税{1}，商业险{2}(商业险包含：";
	private DecimalFormat df2 			= new DecimalFormat("###.##");
	private String template				= "【车险报价通知】尊敬的{0}车主，您的车险即将到期。{1}报价：{2}；总计{3}，优惠后价格{4}，共优惠{5}。联系人：{6}，联系电话:{7}";
	
	@Resource
	private Redis redis;
	@Resource
	private HttpProxy httpProxy;
	@Value("${yunPian.apiKey}")
	private String yunPianKey;
	@Value("${yunpian.captcha.tplId}")
	private int captchaTplId;
	@Value("${yunpian.quota.tplId}")
	private int quotaTplId;
	@Value("${yunpian.captcha.tplSendUrl}")
	private String tplSendUrl;
	
	private NameValuePair API_KEY;
	private NameValuePair QUOTA_TPL_ID;
	private NameValuePair CAPTCHA_TPL_ID;
	private CaptchaSendHandler handler;
	
	@PostConstruct
	private void init() {
		handler = new CaptchaSendHandler();
		API_KEY = new BasicNameValuePair("apikey", yunPianKey);
		QUOTA_TPL_ID = new BasicNameValuePair("tpl_id", String.valueOf(quotaTplId));
		CAPTCHA_TPL_ID = new BasicNameValuePair("tpl_id", String.valueOf(captchaTplId));
	}
	
	public Result<String> captchaObtain(int appId, String mobile) {
		String captchaKey = _captchaKey(appId, mobile);
		String captchaCountKey = _captchaCountKey(appId, mobile);
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
			return Result.result(flag, captcha);
//			_sendCaptcha(mobile, captcha);
//			return Result.result(flag);
		default:
			return Result.result(Code.SYSTEM_ERROR);
		}
	}
	
	public Result<String> captchaVerifier(int appId, String mobile, String captcha) {
		if (!redis.delIfEquals(_captchaKey(appId, mobile), captcha))
			return Result.result(-1);
		return Result.result(0);
	}
	
	private void _sendCaptcha(String mobile, String captcha) {
		String tplValue = null;
		try {
			tplValue = URLEncoder.encode("#code#", Consts.UTF_8.name()) + "=" + URLEncoder.encode(captcha, Consts.UTF_8.name());
			List<NameValuePair> params = new ArrayList<NameValuePair>(3);
			params.add(API_KEY);
			params.add(CAPTCHA_TPL_ID);
			params.add(new BasicNameValuePair(PARAM_TPL_VALUE, tplValue));
			params.add(new BasicNameValuePair(PARAM_MOBILE, mobile));
			HttpPost method = new HttpPost(tplSendUrl);
			method.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8.name()));
			httpProxy.asyncRequest(method, handler);
		} catch (UnsupportedEncodingException e) {
			logger.error("captcha url encode failure!", e);
			return;
		}
	}
	
	public void sendQuotaNotice(VehicleOrder order, QuotaNoticeSubmit submit) {
		String tplValue = null;
		try {
			PolicySchema schema = order.getTips().getSchema();
			double discount = (schema.getCompulsiveTotal() * submit.getCompulsoryRate() / (submit.isTaxInclude() ? 100.0 : 106.0)) 
					+ (schema.getCommericalTotal() * submit.getCommercialRate() / (submit.isTaxInclude() ? 100.0 : 106.0));
			double price = schema.getCommericalTotal() + schema.getCompulsiveTotal() + schema.getVehicleVesselTotal();
			tplValue = URLEncoder.encode("#license#",Consts.UTF_8.name()) + "=" + URLEncoder.encode(order.getTips().getLicense(), Consts.UTF_8.name()) 
					+ "&" + URLEncoder.encode("#insurer#", Consts.UTF_8.name()) + "=" + URLEncoder.encode(order.getInsurerName(), Consts.UTF_8.name())
					+ "&" + URLEncoder.encode("#insurance#", Consts.UTF_8.name()) + "=" + URLEncoder.encode(_buildQuotaInsurances(schema), Consts.UTF_8.name())
					+ "&" + URLEncoder.encode("#price#", Consts.UTF_8.name()) + "=" + URLEncoder.encode(df2.format(price), Consts.UTF_8.name())
					+ "&" + URLEncoder.encode("#price1#", Consts.UTF_8.name()) + "=" + URLEncoder.encode(df2.format(price - discount), Consts.UTF_8.name())
					+ "&" + URLEncoder.encode("#price2#", Consts.UTF_8.name()) + "=" + URLEncoder.encode(df2.format(discount), Consts.UTF_8.name())
					+ "&" + URLEncoder.encode("#name#", Consts.UTF_8.name()) + "=" + URLEncoder.encode(submit.getAgentName(), Consts.UTF_8.name())
					+ "&" + URLEncoder.encode("#mobile#", Consts.UTF_8.name()) + "=" + URLEncoder.encode(submit.getAgentMobile(), Consts.UTF_8.name());
			List<NameValuePair> params = new ArrayList<NameValuePair>(3);
			params.add(API_KEY);
			params.add(QUOTA_TPL_ID);
			params.add(new BasicNameValuePair(PARAM_TPL_VALUE, tplValue));
			params.add(new BasicNameValuePair(PARAM_MOBILE, submit.getCustomerMobile()));
			HttpPost method = new HttpPost(tplSendUrl);
			method.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8.name()));
			httpProxy.asyncRequest(method, handler);
		} catch (UnsupportedEncodingException e) {
			logger.error("captcha url encode failure!", e);
			return;
		}
	}
	
	private String _buildQuotaInsurances(PolicySchema schema) {
		StringBuilder builder = new StringBuilder();
		builder.append(MessageFormat.format(QUOTA_MODEL, String.valueOf(schema.getCompulsiveTotal()), 
				String.valueOf(schema.getVehicleVesselTotal()), String.valueOf(schema.getCommericalTotal())));
		for (CommercialInsuranceType type : CommercialInsuranceType.values()) {
			Insurance insurance = null == schema.getInsurances() ? null : schema.getInsurances().get(type);
			if (null == insurance)
				continue;
			switch (type) {
			case DAMAGE:
			case THIRD:
			case DRIVER:
			case PASSENGER:
			case SCRATCH:
				builder.append(type.title()).append(df2.format(insurance.getQuota() / 10000.0)).append("万、");
				break;
			case GLASS:
				builder.append(type.title()).append("(").append(insurance.getQuota() == 1 ? "国产" : "进口").append(")、");
				break;
			case GARAGE_DESIGNATED:
			case UNKNOWN_THIRD:
			case WADDING:
			case AUTO_FIRE:
			case ROBBERY:
			case DAMAGE_DEDUCTIBLE:
			case THIRD_DEDUCTIBLE:
			case DRIVER_DEDUCTIBLE:
			case PASSENGER_DEDUCTIBLE:
			case ROBBERY_DEDUCTIBLE:
			case AUTO_FIRE_DEDUCTIBLE:
			case SCRATCH_DEDUCTIBLE:
			case WADDING_DEDUCTIBLE:
				builder.append(type.title()).append("、");
				break;
			default:
				break;
			}
		}
		builder.deleteCharAt(builder.length() - 1);
		builder.append(")");
		return builder.toString();
	}
	
	public String _captchaKey(int appId, String mobile) {
		return MessageFormat.format(CAPTCHA, String.valueOf(appId), mobile);
	}
	
	public String _captchaCountKey(int appId, String mobile) {
		return MessageFormat.format(CAPTCHA_COUNT, String.valueOf(appId), mobile);
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
