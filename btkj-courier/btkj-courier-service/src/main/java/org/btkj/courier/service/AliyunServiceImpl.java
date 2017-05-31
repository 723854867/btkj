package org.btkj.courier.service;

import java.text.MessageFormat;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.btkj.courier.api.AliyunService;
import org.btkj.courier.redis.AliyunMapper;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.StsInfo;
import org.rapid.aliyun.AliyunConfig;
import org.rapid.aliyun.AliyunOptions;
import org.rapid.aliyun.policy.Action;
import org.rapid.aliyun.policy.Effect;
import org.rapid.aliyun.policy.Policy;
import org.rapid.aliyun.policy.Statement;
import org.rapid.aliyun.service.sts.StsService;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.lang.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse.Credentials;

@Service("aliyunService")
public class AliyunServiceImpl implements AliyunService {
	
	private static final Logger logger = LoggerFactory.getLogger(AliyunServiceImpl.class);
	
	private static final String USER_KEY			= "app:{0}_user:{1}";
	
	@Resource
	private StsService stsService;
	@Resource
	private AliyunMapper aliyunMapper;
	@Resource
	private AliyunConfig aliyunConfig;
	
	private String ossAccess = "acs:oss:*:*:";
	private Statement bucketReadOnly = null;
	
	@PostConstruct
	private void init() {
		bucketReadOnly = new Statement();
		bucketReadOnly.setEffect(Effect.Allow);
		bucketReadOnly.setAction(Action.OSS_READ_ONLY_ACCESS);
		bucketReadOnly.setResource(ossAccess + aliyunConfig.getConfig(AliyunOptions.OSS_BUCKET) + "/*");
	}

	@Override
	public StsInfo assumeRole(App app) {
		return null;
	}

	@Override
	public StsInfo assumeRole(Tenant tenant) {
		return null;
	}

	@Override
	public StsInfo assumeRole(User user) {
		String field = MessageFormat.format(USER_KEY, String.valueOf(user.getAppId()), String.valueOf(user.getUid()));
		StsInfo stsInfo = aliyunMapper.getByKey(field);
		if (null == stsInfo) {
			stsInfo = _doAssumeRole(user);
			if (null != stsInfo) {
				stsInfo.setKey(field);
				aliyunMapper.insert(stsInfo);
			}
		}
		return stsInfo;
	}

	@Override
	public StsInfo assumeRole(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private StsInfo _doAssumeRole(User user) {
		Policy policy = new Policy();
		policy.addStatement(bucketReadOnly);
		policy.addStatement(_ossFullAccess(user));
		AssumeRoleResponse response;
		try {
			response = stsService.assumeRole(
					aliyunConfig.getConfig(AliyunOptions.STS_ROLE_ARN), 
					"user-" + user.getUid(), policy,
					aliyunConfig.getConfig(AliyunOptions.STS_TOKEN_EXPIRATION));
		} catch (ConstConvertFailureException | ClientException e) {
			logger.warn("Aliyun sts assume role failure!", e);
			return null;
		}
		StsInfo stsInfo = _wrap(response);
		return stsInfo;
	}
	
	private Statement _ossFullAccess(User user) {
		Statement statement = new Statement(Effect.Allow);
		statement.setAction(Action.OSS_FULL_ACCESS);
		statement.setResource(ossAccess + aliyunConfig.getConfig(AliyunOptions.OSS_BUCKET) + "/user/" + user.getUid() + "/*");
		return statement;
	}
	
	private StsInfo _wrap(AssumeRoleResponse response) {
		StsInfo stsInfo = new StsInfo();
		stsInfo.setBucket(aliyunConfig.getConfig(AliyunOptions.OSS_BUCKET));
		stsInfo.setEndpoint(aliyunConfig.getConfig(AliyunOptions.OSS_ENDPOINT));
		Credentials credentials = response.getCredentials();
		stsInfo.setExpiration(credentials.getExpiration());
		stsInfo.setAccessKeyId(credentials.getAccessKeyId());
		stsInfo.setSecurityToken(credentials.getSecurityToken());
		stsInfo.setAccessKeySecret(credentials.getAccessKeySecret());
		
		// 设置缓存失效时间
		long expire = DateUtils.getTimeGap(stsInfo.getExpiration(), DateUtils.UTCDate(), DateUtils.ISO8601_UTC, DateUtils.TIMEZONE_UTC);
		// 提前 1 分钟失效
		expire -= 60000;
		stsInfo.setExpire(System.currentTimeMillis() + expire);
		return stsInfo;
	}
}
