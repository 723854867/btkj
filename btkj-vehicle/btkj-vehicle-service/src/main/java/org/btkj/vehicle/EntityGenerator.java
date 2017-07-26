package org.btkj.vehicle;

import org.btkj.pojo.VehicleRule;
import org.btkj.pojo.bo.InsurUnit;
import org.btkj.pojo.bo.PolicyDetail;
import org.btkj.pojo.enums.CoefficientType;
import org.btkj.pojo.enums.InsuranceType;
import org.btkj.pojo.po.VehicleBrand;
import org.btkj.pojo.po.VehicleCoefficient;
import org.btkj.pojo.po.VehicleDept;
import org.btkj.pojo.po.VehicleModel;
import org.btkj.pojo.po.VehicleOrder;
import org.btkj.pojo.vo.JianJiePoliciesInfo.BaseInfo;
import org.btkj.pojo.vo.JianJiePoliciesInfo.VehicleInfomation;
import org.btkj.pojo.vo.VehiclePolicyTips;
import org.btkj.vehicle.pojo.BonusManageConfigType;
import org.btkj.vehicle.pojo.Lane;
import org.btkj.vehicle.pojo.VehiclePolicyType;
import org.btkj.vehicle.pojo.entity.BonusManageConfig;
import org.btkj.vehicle.pojo.entity.BonusScaleConfig;
import org.btkj.vehicle.pojo.entity.Route;
import org.btkj.vehicle.pojo.entity.VehiclePolicy;
import org.rapid.util.common.Consts;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.math.compare.ComparisonSymbol;

public class EntityGenerator {

	public static final Route newRoute(int tid, int insurerId, Lane lane, int jianJieId) { 
		Route route = new Route();
		route.setKey(tid + Consts.SYMBOL_UNDERLINE + insurerId);
		route.setTid(tid);
		route.setInsurerId(insurerId);
		route.setLane(lane.mark());
		route.setJianJieId(jianJieId);
		
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
	
	public static final boolean fillPolicy(VehiclePolicy policy, VehicleOrder order, BaseInfo info, BaseInfo relationInfo) {
		VehicleInfomation vehicleInfo = info.getVehicleInfomation();
		if (null != order) {
			VehiclePolicyTips tips = order.getTips();
			InsurUnit owner = tips.getOwner();
			if (!owner.getName().equals(info.getCz()))
				return false;
			if (!owner.getIdNo().equals(info.getCzZjhm()))
				return false;
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
			if (null != info && !_deliverNoMatches(order, info))
				return false;
			if (null != relationInfo && !_deliverNoMatches(order, relationInfo))
				return false;
			policy.setType(VehiclePolicyType.TENANT_SELF);
		} else
			policy.setType(VehiclePolicyType.EXTERNAL);
		policy.setOwner(info.getCz());
		policy.setIdNo(info.getCzZjhm());
		
		policy.setIssueDate(vehicleInfo.getFzrq());
		policy.setEnrollDate(vehicleInfo.getCdrq());
		policy.setName(vehicleInfo.getPpxh());
		policy.setVehiclePrice(vehicleInfo.getNewCarCost());
		policy.setNature(VehicleRule.natureFromJianJie(info.getBaseStatus()));
		policy.setScaleType(VehicleRule.scaleTypeFromJianJie(vehicleInfo.getSsxz(), vehicleInfo.getCllx()));
		policy.setLicense(vehicleInfo.getCphm());
		policy.setEngine(vehicleInfo.getFdjh());
		policy.setVin(vehicleInfo.getCjh());
		policy.setSeat(vehicleInfo.getZws());
		policy.setTransfer(vehicleInfo.isGH());
		policy.setDetail(info);
		policy.setDetail(relationInfo);
		policy.setSalesman(VehicleRule.nameFromJianJieGsUser(info.getGsUser()));
		policy.setSalesmanMobile(info.getGsPhone());
		BaseInfo commercial = info.getBdType().equals(InsuranceType.COMMERCIAL.title()) ? info : relationInfo;
		if  (null != commercial && !CollectionUtil.isEmpty(commercial.getInsurances())) 
			policy.setInsurances(VehicleRule.jianJieInsuranceMapping(commercial.getInsurances()));
		return true;
	}
	
	private static final boolean _deliverNoMatches(VehicleOrder order, BaseInfo info) {
		PolicyDetail detail = order.getTips().getDetail();
		String no = info.getBdType().equals(InsuranceType.COMMERCIAL.title()) ? detail.getCommercialNo() : detail.getCompulsiveNo();
		return info.getTBDH().equals(no);
	}
	
	public static final VehicleBrand newVehicleBrand(String name) {
		VehicleBrand brand = new VehicleBrand();
		brand.setName(name);
		
		int time = DateUtil.currentTime();
		brand.setCreated(time);
		brand.setUpdated(time);
		return brand;
	}
	
	public static final VehicleDept newVehicleDept(int brandId, String name) {
		VehicleDept dept = new VehicleDept();
		dept.setName(name);
		dept.setBrandId(brandId);
		
		int time = DateUtil.currentTime();
		dept.setCreated(time);
		dept.setUpdated(time);
		return dept;
	}
	
	public static final VehicleModel newVehicleModel(int deptId, String name) {
		VehicleModel model = new VehicleModel();
		model.setName(name);
		model.setDeptId(deptId);
		
		int time = DateUtil.currentTime();
		model.setCreated(time);
		model.setUpdated(time);
		return model;
	}
}
