package org.btkj.vehicle;

import org.btkj.lebaoba.vehicle.api.LeBaoBaVehicle;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.pojo.enums.VehicleOrderState;
import org.btkj.pojo.model.PolicySchema;
import org.btkj.pojo.param.VehicleOrderParam;
import org.btkj.vehicle.mongo.VehicleOrderMapper;
import org.rapid.util.common.message.Result;

public class LeBaoBaOrderTask implements Runnable {
	
	private String insurer;
	private boolean insure;
	private TenantPO tenant;
	private VehicleOrder order;
	private VehicleOrderParam param;
	
	private LeBaoBaVehicle leBaoBaVehicle;
	private VehicleOrderMapper vehicleOrderMapper;
	
	public LeBaoBaOrderTask(TenantPO tenant, String insurer, boolean insure, VehicleOrder order, VehicleOrderParam param, LeBaoBaVehicle leBaoBaVehicle) {
		this.tenant = tenant;
		this.param = param;
		this.insure = insure;
		this.insurer = insurer;
		this.leBaoBaVehicle = leBaoBaVehicle;
	}

	@Override
	public void run() {
		Result<PolicySchema> result = leBaoBaVehicle.order(tenant.getLeBaoBaUsername(), tenant.getLeBaoBaPassword(), insurer, insure, param);
		VehicleOrderState state = order.getState();
		if (state != VehicleOrderState.QUOTING)
			throw new RuntimeException("乐保吧订单状态异常，必须是报价中订单才可以进行后续操作，实际状态为：" + state);
		order.setDesc(result.getDesc());
		if (result.getCode() == BtkjCode.QUOTE_FAILURE.id())
			order.setState(VehicleOrderState.QUOTE_FAILURE);
		else if (result.getCode() == BtkjCode.INSURE_FAILURE.id())
			order.setState(VehicleOrderState.INSURE_FAILURE);
		else
			order.setState(insure ? VehicleOrderState.INSURE_SUCCESS : VehicleOrderState.QUOTE_SUCCESS);
		if (result.isSuccess())
			order.getTips().setSchema(result.attach());
		vehicleOrderMapper.insert(order);
	}
	
	void onReject() {
		order.setState(VehicleOrderState.QUOTE_FAILURE);
		order.setDesc("系统繁忙，请稍后重新报价！");
		vehicleOrderMapper.insert(order);
	}
}
