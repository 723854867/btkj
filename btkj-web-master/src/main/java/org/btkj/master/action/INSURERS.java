package org.btkj.master.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.pojo.AliyunResourceUtil;
import org.btkj.pojo.entity.config.Insurer;
import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.param.NilParam;
import org.btkj.web.util.action.AdminAction;
import org.rapid.util.common.message.Result;

public class INSURERS extends AdminAction<NilParam> {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<List<Insurer>> execute(Admin admin, NilParam param) {
		Map<Integer, Insurer> insurers = configManageService.insurers();
		for (Insurer insurer : insurers.values())
			insurer.setIcon(AliyunResourceUtil.btResource(insurer.getIcon()));
		return Result.result(new ArrayList<Insurer>(insurers.values()));
	}
}
