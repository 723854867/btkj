package org.btkj.nonauto.service;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.nonauto.api.NonAutoService;
import org.btkj.nonauto.mongo.NonAutoCategoryMapper;
import org.btkj.pojo.entity.NonAutoCategory;
import org.btkj.pojo.entity.NonAutoProduct;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.NonAutoProductSearcher;
import org.springframework.stereotype.Service;

@Service("nonAutoService")
public class NonAutoServiceImpl implements NonAutoService {
	
	@Resource
	private NonAutoCategoryMapper nonAutoCategoryMapper;
	
	@Override
	public void editCategory(NonAutoCategory category) {
		if (0 == category.get_id())
			nonAutoCategoryMapper.insert(category);
		else
			nonAutoCategoryMapper.update(category);
	}
	
	@Override
	public List<NonAutoCategory> getAllCategories() {
		return nonAutoCategoryMapper.getAll();
	}
	
	@Override
	public List<NonAutoCategory> getCategoriesByIds(List<Long> cids) {
		return nonAutoCategoryMapper.getByKeys(cids);
	}
	
	@Override
	public NonAutoCategory getCategoryById(long id) {
		return nonAutoCategoryMapper.getByKey(id);
	}
	
	@Override
	public Pager<NonAutoProduct> productList(NonAutoProductSearcher searcher) {
		return null;
	}
}
