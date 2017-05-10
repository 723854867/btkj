package org.btkj.nonauto.service;

import javax.annotation.Resource;

import org.btkj.nonauto.api.NonAutoService;
import org.btkj.nonauto.mongo.NonAutoCategoryMapper;
import org.btkj.pojo.entity.NonAutoCategory;
import org.springframework.stereotype.Service;

@Service("nonAutoService")
public class NonAutoServiceImpl implements NonAutoService {
	
	@Resource
	private NonAutoCategoryMapper nonAutoCategoryMapper;
	
	@Override
	public void edit(NonAutoCategory category) {
		if (0 == category.get_id())
			nonAutoCategoryMapper.insert(category);
		else
			nonAutoCategoryMapper.update(category);
	}
}
