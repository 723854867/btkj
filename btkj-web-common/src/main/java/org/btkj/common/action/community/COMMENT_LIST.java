package org.btkj.common.action.community;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.common.pojo.info.CommentForm;
import org.btkj.community.api.CommunityService;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Comment;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.model.Pager;
import org.btkj.user.api.EmployeeService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtils;

/**
 * 评论列表
 * 
 * @author ahab
 */
public class COMMENT_LIST extends UserAction {
	
	@Resource
	private EmployeeService employeeService;
	@Resource
	private CommunityService communityService;

	@Override
	protected Result<?> execute(Request request, App app, Client client, User operator) {
		int page = request.getParam(Params.PAGE);
		int pageSize = request.getParam(Params.PAGE_SIZE);
		Result<Pager<Comment>> result = communityService.comments(app.getId(), request.getParam(Params.ID), page, pageSize);
		if (!result.isSuccess())
			return result;
		List<Comment> list = result.attach().getList();
		if (CollectionUtils.isEmpty(list))
			return BtkjConsts.RESULT.EMPTY_PAGING;
		Set<Integer> ids = new HashSet<Integer>();
		for (Comment comment : list)
			ids.add(comment.getEmployeeId());
		
		List<Employee> employees = employeeService.employees(new ArrayList<Integer>(ids));
		List<CommentForm> l = new ArrayList<CommentForm>(list.size());
		for (Comment comment : list) {
			for (Employee employee : employees) {
				if (employee.getId() != comment.getEmployeeId())
					continue;
				l.add(new CommentForm(employee, comment));
				break;
			}
		}
		return Result.result(new Pager<CommentForm>(result.attach().getTotal(), l));
	}
}
