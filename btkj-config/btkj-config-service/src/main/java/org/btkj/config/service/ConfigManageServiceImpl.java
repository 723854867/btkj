package org.btkj.config.service;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.config.mybatis.EntityGenerator;
import org.btkj.config.redis.InsurerMapper;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.Insurer;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service("configManageService")
public class ConfigManageServiceImpl implements ConfigManageService {
	
	@Resource
	private InsurerMapper insurerMapper;

	@Override
	public List<Insurer> insurers() {
		return insurerMapper.getAll();
	}
	
	@Override
	public Result<Void> insurerAdd(int id, String name, String icon, boolean bindBiHu, String leBaoBaId) {
		Insurer insurer = EntityGenerator.newInsurer(id, name, icon, bindBiHu, leBaoBaId);
		try {
			insurerMapper.insert(insurer);
			return Consts.RESULT.OK;
		} catch (DuplicateKeyException e) {
			return Consts.RESULT.KEY_DUPLICATED;
		}
	}
	
	@Override
	public Result<Void> insurerUpdate(int id, String name, String icon, boolean bindBiHu, String leBaoBaId) {
		Insurer insurer = insurerMapper.getByKey(id);
		if (null == insurer)
			return BtkjConsts.RESULT.INSURER_NOT_EXIST;
		insurer.setName(name);
		insurer.setIcon(icon);
		insurer.setBiHuId(bindBiHu ? id : 0);
		insurer.setLeBaoBaId(leBaoBaId);
		try {
			insurerMapper.update(insurer);
		} catch (DuplicateKeyException e) {
			return Consts.RESULT.KEY_DUPLICATED;
		}
		return Consts.RESULT.OK;
	}
}
