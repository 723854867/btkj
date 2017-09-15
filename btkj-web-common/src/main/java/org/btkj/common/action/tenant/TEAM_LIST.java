package org.btkj.common.action.tenant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.btkj.common.pojo.info.TeamInfo;
import org.btkj.common.pojo.param.TeamListParam;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.model.Exploits;
import org.btkj.statistics.api.StatisticsService;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.api.UserService;
import org.btkj.web.util.action.EmployeeAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;

/**
 * 我的团队分页
 * 
 * @author ahab
 */
public class TEAM_LIST extends EmployeeAction<TeamListParam> {
	
	@Resource
	private UserService userService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private StatisticsService statisticsService;

	@Override
	protected Result<TeamInfo> execute(App app, User user, Tenant tenant, Employee employee, TeamListParam param) {
		List<Employee> list = employeeService.team(employee.getTid(), employee.getId(), tenant.getTeamDepth());
		if (CollectionUtil.isEmpty(list))
			return Consts.RESULT.EMPTY_LIST;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (Employee temp : list)
			map.put(temp.getId(), temp.getUid());
		
		Exploits exploits = statisticsService.multiExploits(new ArrayList<Integer>(map.keySet()), param.getBeginTime(), param.getEndTime(), param.getMod());
		return Result.result(new TeamInfo(app, map, exploits, userService.users(new HashSet<Integer>(map.values()))));
	}
}
