package org.btkj.user.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.config.pojo.entity.Area;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.config.GlobalConfigContainer;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.info.ApplyInfo;
import org.btkj.pojo.info.EmployeeTip;
import org.btkj.pojo.model.identity.Employee;
import org.btkj.pojo.model.identity.User;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.api.TenantService;
import org.btkj.user.api.UserService;
import org.btkj.user.mybatis.EntityGenerator;
import org.btkj.user.mybatis.Tx;
import org.btkj.user.pojo.info.TenantListInfo;
import org.btkj.user.pojo.param.TenantAddParam;
import org.btkj.user.redis.AppMapper;
import org.btkj.user.redis.ApplyMapper;
import org.btkj.user.redis.EmployeeMapper;
import org.btkj.user.redis.TenantMapper;
import org.btkj.user.redis.UserMapper;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.concurrent.ThreadLocalUtil;
import org.springframework.stereotype.Service;

@Service("tenantService")
public class TenantServiceImpl implements TenantService {

	@Resource
	private Tx tx;
	@Resource
	private AppMapper appMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private UserService userService;
	@Resource
	private ApplyMapper applyMapper;
	@Resource
	private TenantMapper tenantMapper;
	@Resource
	private ConfigService configService;
	@Resource
	private EmployeeMapper employeeMapper;
	@Resource
	private EmployeeService employeeService;
	

	@Override
	public TenantPO tenant(int tid) {
		return tenantMapper.getByKey(tid);
	}

	@Override
	public Result<?> apply(User user, Employee chief) {
		ApplyInfo ai = applyMapper.getByTidAndUid(chief.getTid(), user.getUid());
		if (null != ai)
			return Result.result(BtkjCode.APPLY_EXIST);
		if (employeeMapper.isEmployee(chief.getTid(), user.getUid()))
			return Result.result(BtkjCode.ALREADY_IS_EMPLOYEE);
		applyMapper.insert(EntityGenerator.newApply(chief.getTenant(), user, chief));
		return Result.success();
	}

	@Override
	public Result<EmployeeTip> tenantAdd(int appId, TenantAddParam param) {
		Area area = configService.area(param.getRegion());
		if (null == area)
			return BtkjConsts.RESULT.AREA_NOT_EXIST;
		Result<UserPO> ru = userMapper.lockUserByMobile(appId, param.getMobile());
		if (!ru.isSuccess())
			return Consts.RESULT.USER_NOT_EXIST;
		UserPO user = ru.attach();
		try {
			if (null == user.getName() || null == user.getIdentity())				// 资料不齐的用户不能作为商户顶级雇员
				return BtkjConsts.RESULT.USER_DATA_INCOMPLETE;
			if (_tenantNumMax(user.getUid()))
				return BtkjConsts.RESULT.USER_TENANT_NUM_MAXIMUM;
			tx.tenantAdd(user, param).finish();
			return Result.result(ThreadLocalUtil.getAndRemove(EntityGenerator.EMPLOYEETIP_HOLDER));
		} finally {
			userMapper.releaseUserLock(user.getUid(), ru.getDesc());
		}
	}

	@Override
	public TenantListInfo tenantListInfo(UserPO user) {
		List<EmployeePO> employees = employeeMapper.ownedTenants(user.getUid());
		List<Integer> tids = new ArrayList<Integer>(employees.size());
		for (EmployeePO employee : employees)
			tids.add(employee.getTid());
		List<TenantPO> own = new ArrayList<TenantPO>(tenantMapper.getByKeys(tids).values());
		List<TenantPO> audit = new ArrayList<TenantPO>(tenantMapper.getByKeys(applyMapper.applyTenants(user.getUid())).values());
		return new TenantListInfo(own, employees, audit);
	}
	
	private boolean _tenantNumMax(int uid) {
		int limit = employeeMapper.ownedTenants(uid).size() + applyMapper.applyTenants(uid).size();
		return limit >= GlobalConfigContainer.getGlobalConfig().getMaxTenantNum();
	}
}
