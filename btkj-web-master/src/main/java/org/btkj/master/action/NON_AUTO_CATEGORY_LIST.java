package org.btkj.master.action;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.nonauto.api.NonAutoService;
import org.btkj.pojo.param.NilParam;
import org.btkj.pojo.po.NonAutoCategory;
import org.rapid.util.common.message.Result;

/**
 * 非车险类型列表
 * 
 * @author ahab
 */
public class NON_AUTO_CATEGORY_LIST extends AdminAction<NilParam> {
	
	@Resource
	private NonAutoService nonAutoService;

	@Override
	protected Result<List<NonAutoCategory>> execute(Administrator admin, NilParam param) {
		return Result.result(nonAutoService.categories());
	}
}
