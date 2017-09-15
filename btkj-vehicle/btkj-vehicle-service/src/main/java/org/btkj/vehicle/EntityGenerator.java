package org.btkj.vehicle;

import org.btkj.pojo.VehicleUtil;
import org.btkj.pojo.entity.config.Insurer;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.vehicle.BonusManageConfig;
import org.btkj.pojo.entity.vehicle.BonusScaleConfig;
import org.btkj.pojo.entity.vehicle.JianJieInsurer;
import org.btkj.pojo.entity.vehicle.PoundageCoefficientRange;
import org.btkj.pojo.entity.vehicle.TenantInsurer;
import org.btkj.pojo.entity.vehicle.VehicleOrder;
import org.btkj.pojo.entity.vehicle.VehiclePolicy;
import org.btkj.pojo.enums.BonusManageConfigType;
import org.btkj.pojo.enums.InsuranceType;
import org.btkj.pojo.enums.Lane;
import org.btkj.pojo.enums.VehiclePolicyType;
import org.btkj.pojo.info.JianJiePoliciesInfo.BaseInfo;
import org.btkj.pojo.info.JianJiePoliciesInfo.VehicleInfomation;
import org.btkj.pojo.info.VehiclePolicyTips;
import org.btkj.pojo.model.InsurUnit;
import org.btkj.pojo.model.PolicySchema;
import org.btkj.pojo.param.vehicle.CoefficientRangeEditParam;
import org.btkj.vehicle.service.VehicleManageServiceImpl;
import org.rapid.util.common.Consts;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.math.compare.Comparison;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityGenerator {
	
	private static final Logger vehicle_manager_logger = LoggerFactory.getLogger(VehicleManageServiceImpl.class);

	public static final TenantInsurer newTenantInsurer(int tid, int insurerId, Lane lane, int time) { 
		TenantInsurer temp = new TenantInsurer();
		temp.setKey(tid + Consts.SYMBOL_UNDERLINE + insurerId);
		temp.setTid(tid);
		temp.setInsurerId(insurerId);
		temp.setLane(lane.mark());
		temp.setCreated(time);
		temp.setUpdated(time);
		return temp;
	}
	
	public static final JianJieInsurer newJianJieInsurer(int tid, int insurerId, int companyId) { 
		JianJieInsurer temp = new JianJieInsurer();
		temp.setCompanyId(companyId);
		temp.setTid(tid);
		temp.setInsurerId(insurerId);
		temp.setCreated(DateUtil.currentTime());
		return temp;
	}
	
	public static final PoundageCoefficientRange newPoundageCoefficientRange(CoefficientRangeEditParam param, String value) {
		PoundageCoefficientRange temp = new PoundageCoefficientRange();
		temp.setTid(param.getTid());
		temp.setName(param.getName());
		temp.setComparableValue(value);
		temp.setComparison(param.getSymbol().mark());
		temp.setCfgCoefficientId(param.getCoefficientId());
		
		int time = DateUtil.currentTime();
		temp.setCreated(time);
		temp.setUpdated(time);
		return temp;
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
	
	public static final BonusScaleConfig newBonusScaleConfig(int tid, int rate, Comparison symbol, String value) {
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
	
	public static final VehiclePolicy newVehiclePolicy(Employee employee, Insurer insurer, String policyId, VehicleOrder order, BaseInfo info, BaseInfo relationInfo) {
		VehiclePolicy policy = new VehiclePolicy(employee, insurer, policyId);
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
		
		policy.setIssueDate(DateUtil.convert(vehicleInfo.getFzrq(), DateUtil.YYYY_MM_DDTHH_MM_SS, DateUtil.YYYY_MM_DD_HH_MM_SS, DateUtil.TIMEZONE_GMT_8));
		policy.setEnrollDate(DateUtil.convert(vehicleInfo.getCdrq(), DateUtil.YYYY_MM_DDTHH_MM_SS, DateUtil.YYYY_MM_DD, DateUtil.TIMEZONE_GMT_8));
		policy.setName(vehicleInfo.getPpxh());
		policy.setVehiclePrice(vehicleInfo.getNewCarCost());
		policy.setNature(VehicleUtil.natureFromJianJie(info.getBaseStatus()));
		policy.setBonusType(VehicleUtil.bonusTypeFromJianJie(vehicleInfo.getSsxz(), vehicleInfo.getCllx()));
		policy.setLicense(vehicleInfo.getCphm());
		policy.setEngine(vehicleInfo.getFdjh());
		policy.setVin(vehicleInfo.getCjh());
		policy.setSeat(vehicleInfo.getZws());
		policy.setPrice(vehicleInfo.getNewCarCost());
		policy.setTransfer(vehicleInfo.isGH());
		policy.setDetail(info);
		policy.setDetail(relationInfo);
		policy.setIssuanceTime(DateUtil.getSecondTime(info.getSkrq(), DateUtil.YYYY_MM_DDTHH_MM_SS));
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
}
