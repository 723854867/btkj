package org.btkj.vehicle;

import org.btkj.pojo.bo.InsurUnit;
import org.btkj.pojo.enums.CoefficientType;
import org.btkj.pojo.po.VehicleCoefficient;
import org.btkj.pojo.po.VehicleOrder;
import org.btkj.pojo.vo.JianJiePoliciesInfo.BaseInfo;
import org.btkj.pojo.vo.JianJiePoliciesInfo.VehicleInfomation;
import org.btkj.pojo.vo.VehiclePolicyTips;
import org.btkj.vehicle.pojo.BonusManageConfigType;
import org.btkj.vehicle.pojo.Lane;
import org.btkj.vehicle.pojo.entity.BonusManageConfig;
import org.btkj.vehicle.pojo.entity.BonusScaleConfig;
import org.btkj.vehicle.pojo.entity.Route;
import org.btkj.vehicle.pojo.entity.VehiclePolicy;
import org.rapid.util.common.Consts;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.math.compare.ComparisonSymbol;

public class EntityGenerator {

	public static final Route newRoute(int tid, int insurerId, Lane lane) { 
		Route route = new Route();
		route.setKey(tid + Consts.SYMBOL_UNDERLINE + insurerId);
		route.setTid(tid);
		route.setInsurerId(insurerId);
		route.setLane(lane.mark());
		
		int time = DateUtil.currentTime();
		route.setCreated(time);
		route.setUpdated(time);
		return route;
	}
	
	public static final VehicleCoefficient newVehicleCoefficient(int tid, CoefficientType type, ComparisonSymbol symbol, String value, String name) {
		VehicleCoefficient coefficient = new VehicleCoefficient();
		coefficient.setTid(tid);
		coefficient.setName(name);
		coefficient.setType(type.mark());
		coefficient.setComparableValue(value);
		coefficient.setComparison(symbol.mark());
		
		int time = DateUtil.currentTime();
		coefficient.setCreated(time);
		coefficient.setUpdated(time);
		return coefficient;
	}
	
	public static final BonusManageConfig newBonusManageConfig(int tid, BonusManageConfigType type, int depth, int rate) {
		BonusManageConfig config = new BonusManageConfig();
		config.setTid(tid);
		config.setRate(rate);
		config.setType(type.mark());
		config.setDepth(depth);
		config.setKey(tid + Consts.SYMBOL_UNDERLINE + type.mark() + Consts.SYMBOL_UNDERLINE + depth);
		
		int time = DateUtil.currentTime();
		config.setCreated(time);
		config.setUpdated(time);
		return config;
	}
	
	public static final BonusScaleConfig newBonusScaleConfig(int tid, int rate, ComparisonSymbol symbol, String value) {
		BonusScaleConfig config = new BonusScaleConfig();
		config.setTid(tid);
		config.setRate(rate);
		config.setComparison(symbol.mark());
		config.setComparableValue(value);
		
		int time = DateUtil.currentTime();
		config.setCreated(time);
		config.setUpdated(time);
		return config;
	}
	
	public static final boolean newPolicy(VehiclePolicy policy, VehicleOrder order, BaseInfo commercial, BaseInfo compulsory) {
		policy.setTid(order.getTid());
		policy.setInsurerId(order.getInsurerId());
		
		VehiclePolicyTips tips = order.getTips();
		InsurUnit owner = tips.getOwner();
		if (!owner.getName().equals(commercial.getCz()))
			return false;
		if (!owner.getIdNo().equals(commercial.getCzZjhm()))
			return false;
		policy.setOwner(owner.getName());
		policy.setIdNo(owner.getIdNo());
		
		policy.setIssueDate(tips.getIssueDate());
		policy.setEnrollDate(tips.getEnrollDate());
		policy.setName(tips.getName());
		VehicleInfomation vehicleInfo = commercial.getVehicleInfomation();
		if (!vehicleInfo.getCphm().equals(tips.getLicense()))
			return false;
		if (!vehicleInfo.getFdjh().equals(tips.getEngine()))
			return false;
		if (!vehicleInfo.getCjh().equals(tips.getVin()))
			return false;
		if (!vehicleInfo.getZws().equals(String.valueOf(tips.getSeat())))
			return false;
		if (vehicleInfo.isGH() ^ tips.isTransfer())
			return false;
		policy.setLicense(tips.getLicense());
		policy.setEngine(tips.getEngine());
		policy.setVin(tips.getVin());
		policy.setSeat(tips.getSeat());
		policy.setTransfer(tips.isTransfer());
//		private int renewalType;						// 转续保类型
//		private VehicleUsedType usedType;	
		return true;
	}
}
