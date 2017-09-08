package org.btkj.user.mybatis;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.TxCallback;
import org.btkj.pojo.VehicleUtil;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.TenantPO.Mod;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.exception.BusinessException;
import org.btkj.pojo.info.EmployeeTip;
import org.btkj.pojo.model.BonusScale;
import org.btkj.pojo.param.user.TenantAddParam;
import org.btkj.user.mybatis.dao.AppDao;
import org.btkj.user.mybatis.dao.EmployeeDao;
import org.btkj.user.mybatis.dao.TenantDao;
import org.btkj.user.mybatis.dao.UserDao;
import org.btkj.user.redis.EmployeeMapper;
import org.btkj.user.redis.TenantMapper;
import org.btkj.user.redis.UserMapper;
import org.rapid.util.common.Consts;
import org.rapid.util.lang.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 所有事物中的 mapper 不能直接 insert，防止前面 insert 成功刷入缓存了，
 * 但是 后面的 insert 失败导致所有 db 数据回滚，但是redis缓存数据没有回滚
 * 
 * @author ahab
 */
@Service("tx")
public class Tx {
	
	private static final Logger logger = LoggerFactory.getLogger(Tx.class);
	
	@Resource
	private AppDao appDao;
	@Resource
	private UserDao userDao;
	@Resource
	private TenantDao tenantDao;
	@Resource
	private EmployeeDao employeeDao;
	
	@Resource
	private UserMapper userMapper;
	@Resource
	private TenantMapper tenantMapper;
	@Resource
	private EmployeeMapper employeeMapper;
	
	@Transactional
	public TxCallback tenantAdd(UserPO user, TenantAddParam param) {
		AppPO apo = appDao.getByKeyForUpdate(user.getAppId());
		if (0 < apo.getMaxTenantsCount()) {			// 如果有代理商个数限制，则需要检查是否已经超出代理商的个数限制了
			int tenantNum = tenantDao.countByAppIdForUpdate(user.getAppId());
			if (tenantNum >= apo.getMaxTenantsCount())
				throw new BusinessException(BtkjCode.APP_TENANT_NUM_MAXIMUM);
		}
		TenantPO tenant = EntityGenerator.newTenant(user.getAppId(), param);
		tenantDao.insert(tenant);
		EmployeePO ep = EntityGenerator.newEmployee(user.getUid(), tenant, null);
		employeeDao.insert(ep);
		EntityGenerator.EMPLOYEETIP_HOLDER.set(new EmployeeTip(ep, apo, user, tenant));
		return new TxCallback() {
			@Override
			public void finish() {
				tenantMapper.flush(tenant);
				employeeMapper.flush(ep);
			}
		};
	}
	
	@Transactional
	public EmployeePO employeeAdd(int tid, int uid, int chiefId) {
		TenantPO tenant = tenantMapper.getByKey(tid);
		Map<Integer, EmployeePO> employees = employeeDao.getByTidForUpdate(tid); 
		EmployeePO chief = employees.get(chiefId);
		EmployeePO employee = EntityGenerator.newEmployee(uid, tenant, chief);
		employeeDao.updateForJoin(employee.getTid(), employee.getLeft());
		employeeDao.insert(employee);
		return employee;
	}
	
	@Transactional
	public List<EmployeePO> team(int tid, int employeeId, int teamDepth) {
		Map<Integer, EmployeePO> employees = employeeDao.getByTidForUpdate(tid); 
		EmployeePO employee = employees.get(employeeId);
		if (null == employee)
			throw new BusinessException(BtkjCode.EMPLOYEE_NOT_EXIST);
		return employeeDao.team(employee.getId(), employee.getLeft(), employee.getRight(), employee.getLayer() + teamDepth - 1);
	}

	/**
	 * 在统计期间如果商户属性修改了不管，只管当前对象属性就可以了，这里不是并发问题
	 * 
	 * @param tenant
	 * @param personalExploits
	 */
	@Transactional
	public Map<String, BonusScale> calculateTeamExploits(int time, TenantPO tenant, Map<Integer, BonusScale> personalExploits) {
		int tid = tenant.getTid();
		int teamDepth = tenant.getTeamDepth();
		Map<Integer, EmployeePO> employees = employeeDao.getByTidForUpdate(tid);
		Map<String, BonusScale> teamExploits = new HashMap<String, BonusScale>();
		for (Entry<Integer, BonusScale> entry : personalExploits.entrySet()) {
			EmployeePO employee = employees.get(entry.getKey());
			if (null == employee) {
				logger.error("规模佣金计算：商户 - {} 业务员 - {} 不存在，本人业务被忽略", tid, entry.getKey());
				continue;
			}
			if ((employee.getMod() & EmployeePO.Mod.BONUS_SCALE.mark()) != EmployeePO.Mod.BONUS_SCALE.mark()) {
				logger.info("规模佣金计算：商户  - {} 业务员 - {} 没有设置规模奖励！", tid, entry.getKey());
				continue;
			}
			LinkedList<Integer> list = VehicleUtil.relationEmployees(employee.getRelationPath(), teamDepth);
			if (CollectionUtil.isEmpty(list))
				continue;
			for (int employeeId : list) {
				EmployeePO temp = employees.get(employeeId);
				if (null == employee) {
					logger.error("规模佣金计算：商户 - {} 业务员 - {} 不存在，下级业务被忽略", tid, entry.getKey());
					continue;
				}
				if ((temp.getMod() & EmployeePO.Mod.BONUS_SCALE.mark()) != EmployeePO.Mod.BONUS_SCALE.mark()) {
					logger.info("规模佣金计算：商户  - {} 业务员 - {} 没有设置规模奖励！", tid, entry.getKey());
					continue;
				}
				BonusScale bs = teamExploits.get(employeeId);
				if (null == bs) {
					bs = new BonusScale(employeeId + Consts.SYMBOL_UNDERLINE + time);
					bs.setEmployeeId(employeeId);
					bs.setTid(tenant.getTid());
					teamExploits.put(bs.get_id(), bs);
				}
				bs.setQuota(entry.getValue().getQuota());
				bs.setRCQuota(entry.getValue().getRCQuota());
				bs.setSCQuota(entry.getValue().getSCQuota());
				bs.addPolicies(entry.getValue().getPolicies());
				if ((tenant.getMod() & Mod.RC_CM.mark()) == Mod.RC_CM.mark())
					bs.setCmRate(entry.getValue().getCmRate());
				if ((tenant.getMod() & Mod.RC_CP.mark()) == Mod.RC_CP.mark())
					bs.setCpRate(entry.getValue().getCmRate());
			}
		}
		return teamExploits;
	}
}
