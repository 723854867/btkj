package org.btkj.nonauto.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.btkj.nonauto.api.NonAutoService;
import org.btkj.nonauto.mongo.NonAutoCategoryMapper;
import org.btkj.nonauto.mongo.NonAutoProductMapper;
import org.btkj.pojo.entity.NonAutoCategory;
import org.btkj.pojo.entity.NonAutoProduct;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.NonAutoProductSearcher;
import org.springframework.stereotype.Service;

@Service("nonAutoService")
public class NonAutoServiceImpl implements NonAutoService {
	
	@Resource
	private NonAutoCategoryMapper nonAutoCategoryMapper;
	@Resource
	private NonAutoProductMapper nonAutoProductMapper;
	
	@Override
	public void editCategory(NonAutoCategory category) {
		if (0 == category.get_id())
			nonAutoCategoryMapper.insert(category);
		else
			nonAutoCategoryMapper.update(category);
	}
	
	@Override
	public List<NonAutoCategory> getAllCategories() {
		return new ArrayList<NonAutoCategory>(nonAutoCategoryMapper.getAll().values());
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
	public void editProduct(NonAutoProduct product) {
		if (0 == product.get_id())
			nonAutoProductMapper.insert(product);
		else
			nonAutoProductMapper.update(product);
	}
	
	@Override
	public Pager<NonAutoProduct> productList(NonAutoProductSearcher searcher) {
		return nonAutoProductMapper.productList(searcher);
	}
}
