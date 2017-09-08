package org.btkj.nonauto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.btkj.nonauto.api.NonAutoService;
import org.btkj.nonauto.mongo.NonAutoCategoryMapper;
import org.btkj.nonauto.mongo.NonAutoProductMapper;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.nonauto.NonAutoCategory;
import org.btkj.pojo.entity.nonauto.NonAutoProduct;
import org.btkj.pojo.entity.nonauto.NonAutoCategory.Filter;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.nonauto.NonAutoProductListParam;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;
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
	public List<NonAutoCategory> categories() {
		return new ArrayList<NonAutoCategory>(nonAutoCategoryMapper.getAll().values());
	}
	
	@Override
	public List<NonAutoCategory> getCategoriesByIds(List<Long> cids) {
		return nonAutoCategoryMapper.getByKeys(cids);
	}
	
	@Override
	public NonAutoCategory category(long id) {
		return nonAutoCategoryMapper.getByKey(id);
	}
	
	@Override
	public Result<Void> editProduct(NonAutoProduct product) {
		NonAutoCategory category = nonAutoCategoryMapper.getByKey(product.getCid());
		if (null == category)
			return BtkjConsts.RESULT.NON_AUTO_CATEGORY_NOT_EXIST;
		// 检查标签
		if (!CollectionUtil.isEmpty(product.getTags())) {
			if (CollectionUtil.isEmpty(category.getTags()))
				return Consts.RESULT.FAILURE;
			a : for (String tag : product.getTags()) {
				for (String ctag : category.getTags()) {
					if (ctag.equals(tag)) 
						continue a;
				}
				return Consts.RESULT.FAILURE;
			}
		}
		// 检查筛选项
		if (!CollectionUtil.isEmpty(product.getFilters())) {
			if (CollectionUtil.isEmpty(category.getFilters()))
				return Consts.RESULT.FAILURE;
			a : for (Entry<String, String> entry : product.getFilters().entrySet()) {
				for (Filter filter : category.getFilters()) {
					if (!filter.getName().equals(entry.getKey()))
						continue;
					for (String option : filter.getOptions()) {
						if (option.equals(entry.getValue()))
							continue a;
					}
				}
				return Consts.RESULT.FAILURE;
			}
		}
		
		if (0 == product.get_id())
			nonAutoProductMapper.insert(product);
		else
			nonAutoProductMapper.update(product);
		return Consts.RESULT.OK;
	}
	
	@Override
	public Pager<NonAutoProduct> products(NonAutoProductListParam param) {
		return nonAutoProductMapper.products(param);
	}
}
