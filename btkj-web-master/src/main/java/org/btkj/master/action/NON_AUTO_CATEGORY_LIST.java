package org.btkj.master.action;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.nonauto.api.NonAutoService;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.param.Param;
import org.btkj.pojo.po.NonAutoCategory;
import org.rapid.util.common.message.Result;

/**
 * 非车险类型列表
 * 
 * @author ahab
 */
public class NON_AUTO_CATEGORY_LIST extends AdminAction<Param> {
	
	@Resource
	private NonAutoService nonAutoService;

	@Override
	protected Result<List<NonAutoCategory>> execute(Administrator admin, Param param) {
		return Result.result(nonAutoService.categories());
	}
	
	@Override
	protected Client client() {
		return Client.BAO_TU_MANAGER;
	}
}
