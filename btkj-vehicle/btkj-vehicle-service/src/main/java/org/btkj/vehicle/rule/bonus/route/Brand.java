package org.btkj.vehicle.rule.bonus.route;

import java.util.LinkedList;
import java.util.List;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.bo.BonusRouteBody;
import org.btkj.pojo.po.VehicleBrand;
import org.btkj.pojo.po.VehicleCoefficient;
import org.btkj.pojo.po.VehicleDept;
import org.btkj.pojo.po.VehicleModel;
import org.btkj.vehicle.pojo.param.BonusPoundageEditParam;
import org.btkj.vehicle.redis.VehicleBrandMapper;
import org.btkj.vehicle.redis.VehicleDeptMapper;
import org.btkj.vehicle.redis.VehicleModelMapper;
import org.rapid.util.Node;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

public class Brand extends BonusRoute<BonusRoute<?>> {
	
	private VehicleDeptMapper vehicleDeptMapper;
	private VehicleBrandMapper vehicleBrandMapper;
	private VehicleModelMapper vehicleModelMapper;
	
	public Brand() {
		super("brand", "品牌");
	}
	
	@Override
	public Result<Void> settings(LinkedList<String> paths, Node<BonusRouteBody> parent, BonusPoundageEditParam param, List<VehicleCoefficient> coefficients) {
		BonusRouteBody body = parent.getChild(id);
		if (null == body) {
			body = new BonusRouteBody(id, title);
			parent.addChiled(body);
		}
		String nextId = paths.poll();
		if (null == nextId) {
			BonusRouteBody update = param.getRouteBody();
			body.setCommercialRate(update.getCommercialRate());
			body.setCompulsoryRate(update.getCompulsoryRate());
			body.setCommercialRetainRate(update.getCommercialRetainRate());
			body.setCompulsoryRetainRate(update.getCompulsoryRetainRate());
			body.setCommercialCommisionSpinner(checkCommercialCommisionSpinner(param, coefficients));
			return Consts.RESULT.OK;
		}
		
		VehicleBrand brand = vehicleBrandMapper.getByKey(Integer.valueOf(nextId));
		if (null == brand)
			return BtkjConsts.RESULT.VEHICLE_BRAND_NOT_EXIST;
		return _subSettings(InternalContrller.BRAND, paths, body, param, coefficients);
	}
	
	/**
	 * 品牌-车系-厂牌型号 设置
	 * 
	 * @param contrller
	 * @param paths
	 * @param parent
	 * @param searcher
	 * @param coefficients
	 * @return
	 */
	private Result<Void> _subSettings(InternalContrller contrller, LinkedList<String> paths, BonusRouteBody parent, BonusPoundageEditParam param, List<VehicleCoefficient> coefficients) { 
		BonusRouteBody body = parent.getChild(id);
		if (null == body) {
			body = new BonusRouteBody(id, title);
			parent.addChiled(body);
		}
		String nextId = paths.poll();
		if (null == nextId) {
			BonusRouteBody update = param.getRouteBody();
			body.setCommercialRate(update.getCommercialRate());
			body.setCompulsoryRate(update.getCompulsoryRate());
			body.setCommercialRetainRate(update.getCommercialRetainRate());
			body.setCompulsoryRetainRate(update.getCompulsoryRetainRate());
			body.setCommercialCommisionSpinner(checkCommercialCommisionSpinner(param, coefficients));
			return Consts.RESULT.OK;
		}
		
		switch (contrller) {
		case BRAND:
			VehicleDept dept = vehicleDeptMapper.getByKey(Integer.valueOf(nextId));
			if (null == dept)
				return BtkjConsts.RESULT.VEHICLE_DEPT_NOT_EXIST;
			return _subSettings(InternalContrller.DEPTE, paths, body, param, coefficients);
		case DEPTE:
			VehicleModel model = vehicleModelMapper.getByKey(Integer.valueOf(nextId));
			if (null == model)
				return BtkjConsts.RESULT.VEHICLE_MODEL_NOT_EXIST;
			return _subSettings(InternalContrller.MODEL, paths, body, param, coefficients);
		case MODEL:
		default:
			return Consts.RESULT.FORBID;
		}
	}
	
	public void setVehicleDeptMapper(VehicleDeptMapper vehicleDeptMapper) {
		this.vehicleDeptMapper = vehicleDeptMapper;
	}
	
	public void setVehicleBrandMapper(VehicleBrandMapper vehicleBrandMapper) {
		this.vehicleBrandMapper = vehicleBrandMapper;
	}
	
	public void setVehicleModelMapper(VehicleModelMapper vehicleModelMapper) {
		this.vehicleModelMapper = vehicleModelMapper;
	}
	
	private enum InternalContrller {
		BRAND,
		DEPTE,
		MODEL;
	}
}
