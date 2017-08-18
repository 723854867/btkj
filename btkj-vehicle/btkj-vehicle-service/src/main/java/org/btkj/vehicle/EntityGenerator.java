package org.btkj.vehicle;

import org.btkj.pojo.VehicleUtil;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.VehicleBrand;
import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.entity.VehicleDept;
import org.btkj.pojo.entity.VehicleModel;
import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.pojo.enums.InsuranceType;
import org.btkj.pojo.info.VehiclePolicyTips;
import org.btkj.pojo.info.JianJiePoliciesInfo.BaseInfo;
import org.btkj.pojo.info.JianJiePoliciesInfo.VehicleInfomation;
import org.btkj.pojo.model.InsurUnit;
import org.btkj.pojo.model.PolicySchema;
import org.btkj.vehicle.pojo.BonusManageConfigType;
import org.btkj.vehicle.pojo.Lane;
import org.btkj.vehicle.pojo.VehiclePolicyType;
import org.btkj.vehicle.pojo.entity.BonusManageConfig;
import org.btkj.vehicle.pojo.entity.BonusScaleConfig;
import org.btkj.vehicle.pojo.entity.TenantInsurer;
import org.btkj.vehicle.pojo.entity.VehiclePolicy;
import org.btkj.vehicle.pojo.param.PoundageCoefficientEditParam;
import org.btkj.vehicle.service.VehicleManageServiceImpl;
import org.rapid.util.common.Consts;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.math.compare.ComparisonSymbol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityGenerator {
	
	private static final Logger vehicle_manager_logger = LoggerFactory.getLogger(VehicleManageServiceImpl.class);

	public static final TenantInsurer newTenantInsurer(int tid, int insurerId, Lane lane, int jianJieId, int time) { 
		TenantInsurer temp = new TenantInsurer();
		temp.setKey(tid + Consts.SYMBOL_UNDERLINE + insurerId);
		temp.setTid(tid);
		temp.setInsurerId(insurerId);
		temp.setLane(lane.mark());
		temp.setJianJieId(jianJieId);
		temp.setCreated(time);
		temp.setUpdated(time);
		return temp;
	}
	
	public static final VehicleCoefficient newVehicleCoefficient(PoundageCoefficientEditParam param, String value) {
		VehicleCoefficient coefficient = new VehicleCoefficient();
		coefficient.setTid(param.getTid());
		coefficient.setName(param.getName());
		coefficient.setType(param.getCoefficientType().mark());
		coefficient.setComparableValue(value);
		coefficient.setComparison(param.getSymbol().mark());
		
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
	
	public static final VehiclePolicy newVehiclePolicy(EmployeePO employee, int insurerId, String policyId, VehicleOrder order, BaseInfo info, BaseInfo relationInfo) {
		VehiclePolicy policy = new VehiclePolicy(employee, insurerId, policyId);
		VehicleInfomation vehicleInfo = info.getVehicleInfomation();
		if (null != order) {
			policy.bindVehicleOrder(order);
			VehiclePolicyTips tips = order.getTips();
			InsurUnit owner = tips.getOwner();
			if (!owner.getName().equals(info.getCz()))
				_fillPolicyErrorLog(policy, "车主姓名");
			if (!owner.getIdNo().equals(info.getCzZjhm()))
				_fillPolicyErrorLog(policy, "车主证件号");
			if (!vehicleInfo.getCphm().equals(tips.getLicense()))
				_fillPolicyErrorLog(policy, "车牌号码");
			if (!vehicleInfo.getFdjh().equals(tips.getEngine()))
				_fillPolicyErrorLog(policy, "发动机号");
			if (!vehicleInfo.getCjh().equals(tips.getVin()))
				_fillPolicyErrorLog(policy, "车架号");
			if (!vehicleInfo.getZws().equals(String.valueOf(tips.getSeat())))
				_fillPolicyErrorLog(policy, "座位数");
			if (vehicleInfo.isGH() ^ tips.isTransfer())
				_fillPolicyErrorLog(policy, "过户状态");
			if (null != info && !_deliverNoMatches(order, info))
				_fillPolicyErrorLog(policy, info.getBdType().equals(InsuranceType.COMMERCIAL.title()) ? "商业险投保单号" : "交强险投保单号");
			if (null != relationInfo && !_deliverNoMatches(order, relationInfo))
				_fillPolicyErrorLog(policy, relationInfo.getBdType().equals(InsuranceType.COMMERCIAL.title()) ? "商业险投保单号" : "交强险投保单号");
			policy.setType(VehiclePolicyType.TENANT_SELF);
		} else
			policy.setType(VehiclePolicyType.EXTERNAL);
		policy.setOwner(info.getCz());
		policy.setIdNo(info.getCzZjhm());
		
		policy.setIssueDate(vehicleInfo.getFzrq());
		policy.setEnrollDate(vehicleInfo.getCdrq());
		policy.setName(vehicleInfo.getPpxh());
		policy.setVehiclePrice(vehicleInfo.getNewCarCost());
		policy.setNature(VehicleUtil.natureFromJianJie(info.getBaseStatus()));
		policy.setBonusType(VehicleUtil.bonusTypeFromJianJie(vehicleInfo.getSsxz(), vehicleInfo.getCllx()));
		policy.setLicense(vehicleInfo.getCphm());
		policy.setEngine(vehicleInfo.getFdjh());
		policy.setVin(vehicleInfo.getCjh());
		policy.setSeat(vehicleInfo.getZws());
		policy.setTransfer(vehicleInfo.isGH());
		policy.setDetail(info);
		policy.setDetail(relationInfo);
		policy.setIssueTime((int) DateUtil.getTime(info.getSkrq(), "yyyy-MM-dd'T'HH:mm:ss") / 1000);
		BaseInfo commercial = info.getBdType().equals(InsuranceType.COMMERCIAL.title()) ? info : relationInfo;
		if (null != commercial && !CollectionUtil.isEmpty(commercial.getInsurances())) 
			policy.setInsurances(VehicleUtil.jianJieInsuranceMapping(commercial.getInsurances()));
		return policy;
	}
	
	private static void _fillPolicyErrorLog(VehiclePolicy policy, String desc) {
		vehicle_manager_logger.error("简捷保单 - {} 和保途订单 - {} 的【" + desc +"】不匹配，以简捷保单为准！", policy.get_id(), policy.getOrderDetail().getOrderId());
	}
	
	
	private static final boolean _deliverNoMatches(VehicleOrder order, BaseInfo info) {
		PolicySchema schema = order.getTips().getSchema();
		String no = info.getBdType().equals(InsuranceType.COMMERCIAL.title()) ? schema.getCommercialNo() : schema.getCompulsoryNo();
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
