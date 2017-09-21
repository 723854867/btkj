package org.btkj.vehicle;

import org.btkj.lebaoba.vehicle.api.LeBaoBaVehicle;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.vehicle.VehicleOrder;
import org.btkj.pojo.enums.VehicleOrderState;
import org.btkj.pojo.model.PolicySchema;
import org.btkj.pojo.param.VehicleOrderParam;
import org.btkj.statistics.api.StatisticsService;
import org.btkj.vehicle.mongo.VehicleOrderMapper;
import org.btkj.vehicle.realm.poundage.Poundage;
import org.rapid.util.common.message.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeBaoBaOrderTask implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(LeBaoBaOrderTask.class);
	
	private String insurer;
	private boolean insure;
	private Tenant tenant;
	private Employee employee;
	private VehicleOrder order;
	private VehicleOrderParam param;
	
	private Poundage poundage;
	private LeBaoBaVehicle leBaoBaVehicle;
	private StatisticsService statisticsService;
	private VehicleOrderMapper vehicleOrderMapper;
	
	public LeBaoBaOrderTask(Tenant tenant, Employee employee, Poundage poundage, String insurer, 
			boolean insure, VehicleOrder order, VehicleOrderParam param, LeBaoBaVehicle leBaoBaVehicle, 
			StatisticsService statisticsService, VehicleOrderMapper vehicleOrderMapper) {
		this.order = order;
		this.param = param;
		this.tenant = tenant;
		this.insure = insure;
		this.insurer = insurer;
		this.employee = employee;
		this.poundage = poundage;
		this.leBaoBaVehicle = leBaoBaVehicle;
		this.statisticsService = statisticsService;
		this.vehicleOrderMapper = vehicleOrderMapper;
	}

	@Override
	public void run() {
		try {
			Result<PolicySchema> result = leBaoBaVehicle.order(tenant.getLeBaoBaUsername(), tenant.getLeBaoBaPassword(), insurer, insure, param);
			VehicleOrderState state = order.getState();
			if (state != VehicleOrderState.QUOTING)
				throw new RuntimeException("乐保吧订单状态异常，必须是报价中订单才可以进行后续操作，实际状态为：" + state);
			order.setDesc(result.getDesc());
			PolicySchema schema = null;
			if (result.getCode() == BtkjCode.QUOTE_FAILURE.id())
				order.setState(VehicleOrderState.QUOTE_FAILURE);
			else if (result.getCode() == BtkjCode.INSURE_FAILURE.id()) {
				schema = result.attach();
				order.setState(VehicleOrderState.INSURE_FAILURE);
			} else {
				schema = result.attach();
				order.setState(insure ? VehicleOrderState.INSURE_SUCCESS : VehicleOrderState.QUOTE_SUCCESS);
				statisticsService.quoteRecord(employee, param.getVin());
			}
			if (null != schema)
				order.getTips().setSchema(result.attach());
			if (order.getState().mark() >= VehicleOrderState.QUOTE_SUCCESS.mark())
				poundage.calculate(order, employee);
			vehicleOrderMapper.insert(order);
		} catch (Exception e) {
			order.setState(VehicleOrderState.SYSTEM_ERROR);
			order.setDesc(e.getMessage());
			logger.error("乐保吧报价系统异常！", e);
		}
	}
	
	void onReject() {
		order.setState(VehicleOrderState.QUOTE_FAILURE);
		order.setDesc("系统繁忙，请稍后重新报价！");
		vehicleOrderMapper.insert(order);
	}
}
