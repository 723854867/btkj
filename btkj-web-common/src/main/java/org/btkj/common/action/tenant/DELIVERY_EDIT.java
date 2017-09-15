package org.btkj.common.action.tenant;

import java.util.LinkedList;

import javax.annotation.Resource;

import org.btkj.common.pojo.param.DeliveryEditParam;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.config.Region;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.Customer;
import org.btkj.pojo.entity.user.EmployeePO;
import org.btkj.pojo.entity.user.TenantPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.enums.DeliveryType;
import org.btkj.pojo.model.DeliveryInfo;
import org.btkj.pojo.model.Recipient;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.web.util.action.EmployeeAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.StringUtil;

/**
 * 编辑配送信息
 * 
 * @author ahab
 */
public class DELIVERY_EDIT  extends EmployeeAction<DeliveryEditParam> {
	
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<Void> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, DeliveryEditParam param) {
		DeliveryInfo deliveryInfo = null;
		switch (param.getType()) {
		case ACTIVE_PICK:
			deliveryInfo = new DeliveryInfo();
			deliveryInfo.setType(param.getType());
			return vehicleService.deliveryEdit(param.getOrderId(), deliveryInfo);
		default:
			if (null != param.getCustomerId()) {							// 将一个已经存在的客户作为收件人
				Customer customer = userService.getCustomerById(param.getCustomerId());
				if (null == customer)
					return BtkjConsts.RESULT.CUSTOMER_NOT_EXIST;
				deliveryInfo = _build(customer, param.getType());
			} else {
				if (!StringUtil.hasText(param.getName(), param.getMobile(), param.getAddress()) || null == param.getRegion())
					return Consts.RESULT.FORBID;
				LinkedList<Region> regions = configService.regionRoute(param.getRegion());
				if (null == regions || regions.size() < 3)
					return BtkjConsts.RESULT.REGION_NOT_EXIST;
				deliveryInfo = _build(param.getName(), param.getMobile(), param.getAddress(), regions, param.getType());
			}
			return vehicleService.deliveryEdit(param.getOrderId(), deliveryInfo);
		}
	}
	
	private DeliveryInfo _build(Customer customer, DeliveryType type) {
		DeliveryInfo deliveryInfo = new DeliveryInfo();
		deliveryInfo.setType(type);
		Recipient recipient = new Recipient();
		recipient.setName(customer.getName());
		recipient.setMobile(customer.getMobile());
		recipient.setAddressPrefix(customer.getProvince() + customer.getCity() + customer.getCounty());
		recipient.setAddressSuffix(customer.getAddress());
		deliveryInfo.setRecipient(recipient);
		return deliveryInfo;
	}
	
	private DeliveryInfo _build(String name, String mobile, String address, LinkedList<Region> regions, DeliveryType type) {
		DeliveryInfo deliveryInfo = new DeliveryInfo();
		deliveryInfo.setType(type);
		Recipient recipient = new Recipient();
		recipient.setName(name);
		recipient.setMobile(mobile);
		recipient.setAddressSuffix(address);
		deliveryInfo.setRecipient(recipient);
		while (true) {
			Region region = regions.poll();
			if (null == region)
				break;
			recipient.setAddressPrefix(null == recipient.getAddressPrefix() ? region.getName() : recipient.getAddressPrefix() + region.getName());
		}
		return deliveryInfo;
	}
	
	@Override
	protected boolean userLock() {
		return true;
	}
}
