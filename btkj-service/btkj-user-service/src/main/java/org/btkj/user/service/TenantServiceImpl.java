package org.btkj.user.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.NonAutoBind;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.enums.TenantState;
import org.btkj.pojo.info.ApplyInfo;
import org.btkj.pojo.info.TenantListInfo;
import org.btkj.pojo.info.TenantListPc;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.TenantSearcher;
import org.btkj.user.BeanGenerator;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.api.TenantService;
import org.btkj.user.api.UserService;
import org.btkj.user.persistence.Tx;
import org.btkj.user.redis.AppMapper;
import org.btkj.user.redis.ApplyMapper;
import org.btkj.user.redis.EmployeeMapper;
import org.btkj.user.redis.NonAutoBindMapper;
import org.btkj.user.redis.TenantMapper;
import org.btkj.user.redis.UserMapper;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtils;
import org.springframework.dao.DuplicateKeyException;
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
	private EmployeeMapper employeeMapper;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private NonAutoBindMapper nonAutoBindMapper;

	@Override
	public Result<Void> tenantEdit(Tenant tenant) {
		Tenant t = tenantMapper.getByKey(tenant.getTid());
		if (null == t)
			return Result.result(BtkjCode.TENANT_NOT_EXIST);
		tenant.setUpdated(DateUtils.currentTime());
		tenantMapper.update(tenant);
		return Result.success();
	}

	@Override
	public Result<Void> tenantState(int tid, TenantState state) {
		Tenant tenant = tenantMapper.getByKey(tid);
		if (null == tenant)
			return Result.result(BtkjCode.TENANT_NOT_EXIST);
		if (tenant.getState() != state.mark()) {
			tenant.setState(state.mark());
			tenant.setUpdated(DateUtils.currentTime());
			tenantMapper.update(tenant);
		}
		return Result.success();
	}

	@Override
	public Result<Pager<TenantListPc>> tenantList(TenantSearcher searcher) {
		return tenantMapper.tenantList(searcher);
	}

	@Override
	public Tenant getTenantById(int tid) {
		return tenantMapper.getByKey(tid);
	}

	@Override
	public Result<?> apply(User user, int employeeId, String name, String identity, String identityFace,
			String identityBack) {
		EmployeeForm chief = employeeService.getById(employeeId);
		if (null == chief)
			return Result.result(BtkjCode.EMPLOYEE_NOT_EXIST);
		if (chief.getApp().getId() != user.getAppId())
			return Result.result(Code.FORBID);
		return _doApply(chief.getTenant(), user, chief, name, identity, identityFace, identityBack);
	}

	@Override
	public Result<?> apply(String mobile, EmployeeForm chief, String name, String identity, String identityFace,
			String identityBack) {
		User user = userMapper.getUserByMobile(chief.getApp().getId(), mobile);
		if (null == user) {
			try {
				user = userMapper.insert(BeanGenerator.newUser(chief.getApp().getId(), mobile));
			} catch (DuplicateKeyException e) { // 如果unique冲突则说明 app-mobile
												// 组合已经存在了，则直接再次获取
				user = userMapper.getUserByMobile(chief.getApp().getId(), mobile);
			}
		}
		return _doApply(chief.getTenant(), user, chief, name, identity, identityFace, identityBack);
	}

	private Result<?> _doApply(Tenant tenant, User user, EmployeeForm chief, String name, String identity,
			String identityFace, String identityBack) {
		ApplyInfo ai = applyMapper.getByTidAndUid(tenant.getTid(), user.getUid());
		if (null != ai)
			return Result.result(BtkjCode.APPLY_EXIST);
		if (employeeMapper.isEmployee(tenant.getTid(), user.getUid()))
			return Result.result(BtkjCode.ALREADY_IS_EMPLOYEE);
		applyMapper.insert(BeanGenerator.newApply(tenant, user, chief, name, identity, identityFace, identityBack));
		return Result.success();
	}

	@Override
	public Result<Pager<ApplyInfo>> applyList(int tid, int page, int pageSize) {
		return applyMapper.applyList(tid, page, pageSize);
	}

	@Override
	public Result<Void> applyProcess(int tid, int uid, boolean agree) {
		ApplyInfo info = applyMapper.getAndDel(tid, uid);
		if (null == info)
			return Result.result(BtkjCode.APPLY_EXIST);
		if (!agree) // 拒绝申请直接返回即可
			return Result.success();

		Employee chief = employeeMapper.getByKey(info.getChief());
		Tenant tenant = tenantMapper.getByKey(info.getTid());
		Employee employee = BeanGenerator.newEmployee(userMapper.getByKey(info.getUid()), tenant, chief, info.getName(),
				info.getIdentity(), info.getIdentityFace(), info.getIdentityBack());
		tx.insertEmployee(employee);
		return Result.success();
	}

	@Override
	public Result<?> tenantAdd(App app, Region region, String tname, String mobile, String name, String identity,
			String identityFace, String identityBack) {
		Result<User> result = userMapper.lockUserByMobile(app.getId(), mobile);
		if (result.isSuccess()) { // 指定一个老用户作为新的租户的顶级用户
			try {
				if (userService.tenantNumMax(result.attach()))
					return Result.result(BtkjCode.USER_TENANT_NUM_MAXIMUM);
				tx.tenantAdd(app, region, tname, result.attach(), name, identity, identityFace, identityBack).finish();
			} finally {
				userMapper.releaseUserLock(result.attach().getUid(), result.getDesc());
			}
		} else if (result.getCode() == Code.USER_NOT_EXIST.id()) { // 指定一个新用户作为租户的顶级用户
			try {
				tx.tenantAdd(app, region, tname, mobile, name, identity, identityFace, identityBack).finish();
				;
			} catch (DuplicateKeyException e) { // 说明在这里的时候 mobile
												// 用户刚好也注册了，name再次添加一次
				return tenantAdd(app, region, tname, mobile, name, identity, identityFace, identityBack);
			}
		} else
			return result;
		return Result.success();
	}

	@Override
	public TenantListInfo tenantListInfo(Client client, App app, User user) {
		List<Employee> employees = employeeMapper.ownedTenants(user);
		List<Integer> tids = new ArrayList<Integer>(employees.size());
		for (Employee employee : employees)
			tids.add(employee.getTid());
		List<Tenant> own = tenantMapper.getWithinKey(tids);
		List<Tenant> audit = tenantMapper.getWithinKey(applyMapper.applyListTids(user));
		return new TenantListInfo(own, employees, audit);
	}

	@Override
	public List<NonAutoBind> getNonAutoBinds(int tid) {
		return nonAutoBindMapper.getByTid(tid);
	}
}
