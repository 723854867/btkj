package org.btkj.master.action.vehicle.bihu;

import javax.annotation.Resource;

import org.btkj.bihu.vehicle.api.BiHuConfigService;
import org.btkj.config.api.ConfigService;
import org.btkj.pojo.entity.Administrator;
import org.btkj.pojo.entity.Region;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;

/**
 * 新增壁虎城市
 * 
 * @author ahab
 */
public class BI_HU_CITY_ADD extends AdministratorAction {
	
	@Resource
	private ConfigService configService;
	@Resource
	private BiHuConfigService biHuConfigService;

	@Override
	protected Result<Void> execute(Request request, Administrator operator) {
		Region region = configService.getRegionById(request.getParam(Params.REGION));
		if (null == region)
			throw ConstConvertFailureException.errorConstException(Params.REGION);
		int cid = request.getParam(Params.ID);
		String name = request.getParam(Params.NAME);
		biHuConfigService.addBiHuCity(cid, region, name);
		return Consts.RESULT.OK;
	}
}
