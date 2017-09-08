package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.AdminAction;
import org.btkj.nonauto.api.NonAutoService;
import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.param.nonauto.NonAutoEditParam;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

/**
 * 添加非车险类型
 * 
 * @author ahab
 */
public class NON_AUTO_CATEGORY_EDIT extends AdminAction<NonAutoEditParam> {
	
	@Resource
	private NonAutoService nonAutoService;
	
	@Override
	protected Result<?> execute(Admin admin, NonAutoEditParam param) {
		nonAutoService.editCategory(param.entity());
		return Consts.RESULT.OK;
	}
}
