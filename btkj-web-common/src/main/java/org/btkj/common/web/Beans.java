package org.btkj.common.web;

import org.btkj.courier.api.CourierService;
import org.rapid.util.common.SpringContextUtil;

public interface Beans {

	final CourierService courierService = SpringContextUtil.getBean("courierService", CourierService.class);
}
