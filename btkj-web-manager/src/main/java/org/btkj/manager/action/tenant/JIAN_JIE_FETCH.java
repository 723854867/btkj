package org.btkj.manager.action.tenant;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.courier.api.JianJieService;
import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.statistics.StatisticPolicy;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.entity.vehicle.VehiclePolicy;
import org.btkj.pojo.entity.vehicle.VehiclePolicy.CommercialPolicyDetail;
import org.btkj.pojo.entity.vehicle.VehiclePolicy.CompulsoryPolicyDetail;
import org.btkj.pojo.enums.InsuranceType;
import org.btkj.pojo.enums.StatisticUsedType;
import org.btkj.pojo.enums.VehicleBonusType;
import org.btkj.pojo.info.JianJiePoliciesInfo;
import org.btkj.pojo.info.JianJiePoliciesInfo.BaseInfo;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.statistics.api.StatisticsService;
import org.btkj.user.api.UserManageService;
import org.btkj.vehicle.api.VehicleManageService;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.lang.StringUtil;

/**
 * 同步简捷的后台保单数据
 * 
 * @author ahab
 */
public class JIAN_JIE_FETCH extends EmployeeAction<EmployeeParam> {
	
	@Resource
	private JianJieService jianJieService;
	@Resource
	private UserManageService userManageService;
	@Resource
	private StatisticsService statisticsService;
	@Resource
	private VehicleManageService vehicleManageService;
	
	@Override
	protected Result<?> execute(App app, User user, Tenant tenant, Employee employee, EmployeeParam param) {
		if (!StringUtil.hasText(tenant.getJianJieId()))
			return BtkjConsts.RESULT.JIAN_JIE_ID_NEEDED;
		JianJiePoliciesInfo info = jianJieService.vehiclePolicies(tenant.getJianJieId(),
				DateUtil.getDate(DateUtil.YYYYMMDD, tenant.getJianJieFetchTime()),
				DateUtil.getDate(DateUtil.YYYYMMDD, DateUtil.currentTime()));
		if (!info.isSuccessStatus())
			return Result.result(Code.FAILURE, info.getErrorMessage());
		Set<Integer> set = new HashSet<Integer>();
		for (BaseInfo temp : info.getResult()) {
			String GsUser = temp.getGsUser();
			try {
				set.add(Integer.valueOf(GsUser.substring(GsUser.indexOf(":") + 1, GsUser.length() - 1)));
			} catch (NumberFormatException e) {
				continue;
			}
		}
		Map<String, VehiclePolicy> policies = vehicleManageService.jianJieSynchronize(employee, employeeService.employeeTips(set), info);
		List<StatisticPolicy> statsitics = _statistics(policies);
		statisticsService.statisticPolicies(statsitics);
		tenant.setJianJieFetchTime(DateUtil.currentTime());
		tenant.setUpdated(DateUtil.currentTime());
		userManageService.tenantUpdate(tenant);
		return Consts.RESULT.OK;
	}
	
	private List<StatisticPolicy> _statistics(Map<String, VehiclePolicy> policies) {
		List<StatisticPolicy> list = new ArrayList<StatisticPolicy>();
		for (VehiclePolicy policy : policies.values()) {
			StatisticPolicy temp = _policy(policy, InsuranceType.COMMERCIAL);
			if (null != temp)
				list.add(temp);
			temp = _policy(policy, InsuranceType.COMPULSORY);
			if (null != temp)
				list.add(temp);
		}
		return list;
	}
	
	private StatisticPolicy _policy(VehiclePolicy policy, InsuranceType insuranceType) {
		String id = null;
		String premium = null;
		String issueDate = null;
		switch (insuranceType) {
		case COMMERCIAL:
			CommercialPolicyDetail cmdetail = policy.getCommercialDetail();
			if (null == cmdetail)
				return null;
			id = cmdetail.getNo();
			premium = cmdetail.getPrice();
			issueDate = cmdetail.getIssueDate();
			break;
		case COMPULSORY:
			CompulsoryPolicyDetail cpdetail = policy.getCompulsoryDetail();
			if (null == cpdetail)
				return null;
			id = cpdetail.getNo();
			premium = new BigDecimal(cpdetail.getPrice()).add(new BigDecimal(cpdetail.getVesselPrice())).setScale(2, RoundingMode.HALF_UP).toString();
			issueDate = cpdetail.getIssueDate();
			break;
		default:
			return null;
		}
		
		StatisticPolicy temp = new StatisticPolicy();
		temp.setId(id);
		temp.setType(policy.getType().mark());
		temp.setInsurerId(policy.getInsurerId());
		temp.setInsuranceType(insuranceType.mark());
		temp.setAppId(policy.getAppId());
		temp.setTid(policy.getTid());
		temp.setUid(policy.getUid());
		temp.setEmployeeId(policy.getSalesmanId());
		temp.setUsedType(_statisticUsedType(policy.getBonusType()).mark());
		temp.setNature(policy.getNature().mark());
		temp.setTransfer(policy.isTransfer() ? 1 : 0);
		temp.setPremium(premium);
		Calendar calendar = GregorianCalendar.getInstance(DateUtil.TIMEZONE_GMT_8);
		calendar.setTimeInMillis(DateUtil.getTime(issueDate, DateUtil.YYYY_MM_DD_HH_MM_SS, DateUtil.TIMEZONE_GMT_8));
		temp.setYear(calendar.get(Calendar.YEAR));
		temp.setMonth(calendar.get(Calendar.MONTH));
		temp.setDay(calendar.get(Calendar.DAY_OF_MONTH));
		temp.setWeek(calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		temp.setSeason(temp.getMonth() % 3);
		temp.setCreated(policy.getIssuanceTime());
		return temp;
	}
	
	private StatisticUsedType _statisticUsedType(VehicleBonusType bonustype) {
		switch (bonustype) {
		case PC:
		case PT:
			return StatisticUsedType.BIZ;
		case NPC:
		case NPT:
			return StatisticUsedType.NO_BIZ;
		default:
			return StatisticUsedType.OTHER;
		}
	}
}
