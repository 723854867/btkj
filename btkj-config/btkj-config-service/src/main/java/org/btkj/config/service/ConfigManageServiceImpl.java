package org.btkj.config.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.config.mybatis.EntityGenerator;
import org.btkj.config.pojo.entity.Area;
import org.btkj.config.pojo.info.AreaInfo;
import org.btkj.config.redis.AreaMapper;
import org.btkj.config.redis.InsurerMapper;
import org.btkj.config.redis.RegionMapper;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.po.Insurer;
import org.btkj.pojo.po.Region;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.DateUtil;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service("configManageService")
public class ConfigManageServiceImpl implements ConfigManageService {
	
	@Resource
	private AreaMapper areaMapper;
	@Resource
	private RegionMapper regionMapper;
	@Resource
	private InsurerMapper insurerMapper;

	@Override
	public List<Insurer> insurers() {
		return new ArrayList<Insurer>(insurerMapper.getAll().values());
	}
	
	@Override
	public Result<Void> insurerAdd(int id, String name, String icon, boolean bindBiHu, String leBaoBaId, int jianJieId) {
		Insurer insurer = EntityGenerator.newInsurer(id, name, icon, bindBiHu, leBaoBaId, jianJieId);
		try {
			insurerMapper.insert(insurer);
			return Consts.RESULT.OK;
		} catch (DuplicateKeyException e) {
			return Consts.RESULT.KEY_DUPLICATED;
		}
	}
	
	@Override
	public Result<Void> insurerUpdate(int id, String name, String icon, boolean bindBiHu, String leBaoBaId, int jianJieId) {
		Insurer insurer = insurerMapper.getByKey(id);
		if (null == insurer)
			return BtkjConsts.RESULT.INSURER_NOT_EXIST;
		insurer.setName(name);
		insurer.setIcon(icon);
		insurer.setBiHuId(bindBiHu ? id : 0);
		insurer.setLeBaoBaId(leBaoBaId);
		insurer.setJianJieId(jianJieId);
		try {
			insurerMapper.update(insurer);
		} catch (DuplicateKeyException e) {
			return Consts.RESULT.KEY_DUPLICATED;
		}
		return Consts.RESULT.OK;
	}
	
	@Override
	public List<AreaInfo> areas() {
		List<Area> list = new ArrayList<Area>(areaMapper.getAll().values());
		if (CollectionUtil.isEmpty(list))
			return Collections.EMPTY_LIST;
		List<Integer> l = new ArrayList<Integer>();
		for (Area area : list)
			l.add(area.getCode());
		List<Region> regions = new ArrayList<Region>(regionMapper.getByKeys(l).values());
		List<AreaInfo> areas = new ArrayList<AreaInfo>();
		a : for (Area area : list) {
			for (Region region : regions) {
				if (region.getId() == area.getCode()) {
					areas.add(new AreaInfo(area, region));
					continue a;
				}
			}
			areas.add(new AreaInfo(area, null));
		}
		return areas;
	}
	
	@Override
	public Result<Void> areaAdd(int code, int renewalPeriod, int biHuId) {
		Area area = EntityGenerator.newArea(code, renewalPeriod, biHuId);
		try {
			areaMapper.insert(area);
			return Consts.RESULT.OK;
		} catch (DuplicateKeyException e) {
			return Consts.RESULT.KEY_DUPLICATED;
		}
	}
	
	@Override
	public Result<Void> areaUpdate(int code, int renewalPeriod, int biHuId) {
		Area area = areaMapper.getByKey(code);
		if (null == area)
			return BtkjConsts.RESULT.AREA_NOT_EXIST;
		area.setBiHuId(biHuId);
		area.setRenewalPeriod(renewalPeriod);
		area.setUpdated(DateUtil.currentTime());
		areaMapper.update(area);
		return Consts.RESULT.OK;
	}
}
