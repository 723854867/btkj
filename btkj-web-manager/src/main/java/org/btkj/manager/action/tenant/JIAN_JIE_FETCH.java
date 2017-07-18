package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.courier.api.JianJieService;
import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.bo.EmployeeForm;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.vo.JianJiePoliciesInfo;
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
	private VehicleManageService vehicleManageService;
	
	@Override
	protected Result<Void> execute(Request request, EmployeeForm ef) {
		TenantPO tenant = ef.getTenant();
		if (null == tenant.getJianJieId())
			return BtkjConsts.RESULT.JIAN_JIE_ID_NEEDED;
		JianJiePoliciesInfo info = jianJieService.vehiclePolicies(ef.getTenant().getJianJieId(),
				DateUtil.getDate(DateUtil.YYYYMMDD, ef.getTenant().getJianJieFetchTime()),
				DateUtil.getDate(DateUtil.YYYYMMDD, DateUtil.currentTime()));
		if (!info.isSuccessStatus())
			return Result.result(Code.FAILURE, info.getErrorMessage());
		vehicleManageService.jianJieSynchronize(info);
		return Consts.RESULT.OK;
	}
}
