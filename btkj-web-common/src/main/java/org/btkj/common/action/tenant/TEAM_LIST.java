package org.btkj.common.action.tenant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.btkj.common.pojo.info.TeamInfo;
import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.statistics.api.StatisticsService;
import org.btkj.statistics.pojo.model.Exploits;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;

/**
 * 我的团队分页
 * 
 * @author ahab
 */
public class TEAM_LIST extends TenantAction {
	
	@Resource
	private UserService userService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private StatisticsService statisticsService;

	@Override
	protected Result<TeamInfo> execute(Request request, Employee employee) {
		List<EmployeePO> list = employeeService.team(employee.getTid(), employee.getId(), employee.getTeamDepth());
		if (CollectionUtil.isEmpty(list))
			return Consts.RESULT.EMPTY_LIST;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (EmployeePO temp : list)
			map.put(temp.getId(), temp.getUid());
		
		Exploits exploits = statisticsService.multiExploits(new ArrayList<Integer>(map.keySet()), request.getParam(Params.BEGIN_TIME), 
				request.getParam(Params.END_TIME), request.getParam(Params.MOD));
		return Result.result(new TeamInfo(map, exploits, userService.users(new ArrayList<Integer>(map.values()))));
	}
}
