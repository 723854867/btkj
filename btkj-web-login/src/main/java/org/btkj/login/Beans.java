package org.btkj.login;

import org.btkj.courier.api.CourierService;
import org.btkj.user.api.LoginService;
import org.rapid.data.storage.redis.Redis;
import org.rapid.util.common.SpringContextUtil;

public interface Beans {

	final Redis redis = SpringContextUtil.getBean("redis", Redis.class);
	final LoginService loginService = SpringContextUtil.getBean("loginService", LoginService.class);
	final CourierService courierService = SpringContextUtil.getBean("courierService", CourierService.class);
}
