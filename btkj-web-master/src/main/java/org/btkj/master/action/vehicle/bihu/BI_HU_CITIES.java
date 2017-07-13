package org.btkj.master.action.vehicle.bihu;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.bihu.vehicle.api.BiHuConfigService;
import org.btkj.bihu.vehicle.pojo.entity.BiHuArea;
import org.btkj.pojo.entity.Administrator;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.message.Result;

/**
 * 获取所有的壁虎城市
 * 
 * @author ahab
 */
public class BI_HU_CITIES extends AdministratorAction {
	
	@Resource
	private BiHuConfigService biHuConfigService;

	@Override
	protected Result<List<BiHuArea>> execute(Request request, Administrator operator) {
		return Result.result(biHuConfigService.cities());
	}
}
