package org.btkj.manager.action.tenant;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.courier.api.JianJieService;
import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.pojo.vo.JianJiePoliciesInfo;
import org.btkj.pojo.vo.JianJiePoliciesInfo.BaseInfo;
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
	private VehicleManageService vehicleManageService;
	
	@Override
	protected Result<?> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeParam param) {
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
		vehicleManageService.jianJieSynchronize(employee, employeeService.employeeTips(set), info);
		return Consts.RESULT.OK;
	}
}
