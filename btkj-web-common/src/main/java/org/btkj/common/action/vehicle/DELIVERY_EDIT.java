package org.btkj.common.action.vehicle;

import java.util.LinkedList;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.Customer;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.enums.DeliveryType;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.Recipient;
import org.btkj.pojo.model.insur.vehicle.DeliveryInfo;
import org.btkj.user.api.UserService;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;

/**
 * 编辑配送信息
 * 
 * @author ahab
 */
public class DELIVERY_EDIT  extends TenantAction {
	
	@Resource
	private UserService userService;
	@Resource
	private ConfigService configService;
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<Void> execute(Request request, Client client, EmployeeForm ef) {
		String orderId = request.getParam(Params.ORDER_ID);
		DeliveryType deliveryType = request.getParam(Params.DELIVERY_TYPE);
		DeliveryInfo deliveryInfo = null;
		switch (deliveryType) {
		case DOT_DISPATCH:
			deliveryInfo = new DeliveryInfo();
			deliveryInfo.setType(deliveryType);
			return vehicleService.deliveryEdit(orderId, deliveryInfo);
		default:
			int customerId = request.getOptionalParam(Params.CUSTOMER_ID);
			if (0 != customerId) {							// 将一个已经存在的客户作为收件人
				Customer customer = userService.getCustomerById(customerId);
				if (null == customer)
					return BtkjConsts.RESULT.CUSTOMER_NOT_EXIST;
				deliveryInfo = _build(customer, deliveryType);
			} else {
				String name = request.getParam(Params.NAME);
				String mobile = request.getParam(Params.MOBILE);
				String address = request.getParam(Params.ADDRESS);
				int region = request.getParam(Params.REGION);
				LinkedList<Region> regions = 0 == region ? null : configService.regionRoute(region);
				if (null == regions || regions.size() < 3)
					throw ConstConvertFailureException.errorConstException(Params.REGION);
				deliveryInfo = _build(name, mobile, address, regions, deliveryType);
			}
			return vehicleService.deliveryEdit(orderId, deliveryInfo);
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
