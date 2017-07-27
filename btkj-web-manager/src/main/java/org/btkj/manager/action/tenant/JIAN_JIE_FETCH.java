package org.btkj.manager.action.tenant;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.courier.api.JianJieService;
import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.vo.JianJiePoliciesInfo;
import org.btkj.pojo.vo.JianJiePoliciesInfo.BaseInfo;
import org.btkj.user.api.EmployeeService;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.web.util.Request;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtil;

/**
 * 同步简捷的后台保单数据
 * 
 * @author ahab
 */
public class JIAN_JIE_FETCH extends TenantAction {
	
	@Resource
	private JianJieService jianJieService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private VehicleManageService vehicleManageService;
	
	@Override
	protected Result<?> execute(Request request, Employee employee) {
		if (null == employee.jianJieId())
			return BtkjConsts.RESULT.JIAN_JIE_ID_NEEDED;
		JianJiePoliciesInfo info = jianJieService.vehiclePolicies(employee.jianJieId(),
				DateUtil.getDate(DateUtil.YYYYMMDD, employee.jianJieFetchTime()),
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
