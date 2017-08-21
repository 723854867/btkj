package org.btkj.master.action;

import java.util.Map;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.config.pojo.TarType;
import org.btkj.config.pojo.info.ModularDocument;
import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Admin;
import org.btkj.pojo.enums.ModularType;
import org.btkj.pojo.param.NilParam;
import org.rapid.util.common.message.Result;

/**
 * 获取 app 模块的文档结构
 * 
 * @author ahab
 */
public class MODULARS_APP extends AdminAction<NilParam> {

	@Resource
	private ConfigManageService configManageService;
	
	@Override
	protected Result<Map<Integer, ModularDocument>> execute(Admin admin, NilParam param) {
		return Result.result(configManageService.modulars(0, TarType.APP, ModularType.APP));
	}
}
