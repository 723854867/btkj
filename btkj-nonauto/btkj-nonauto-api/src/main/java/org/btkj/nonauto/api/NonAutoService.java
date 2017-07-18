package org.btkj.nonauto.api;

import java.util.List;

import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.po.NonAutoCategory;
import org.btkj.pojo.po.NonAutoProduct;
import org.btkj.pojo.vo.NonAutoProductSearcher;

/**
 * 非车险服务类
 * 
 * @author ahab
 */
public interface NonAutoService {

	/**
	 * 新增或修改非车险类型
	 * 
	 * @param category
	 */
	void editCategory(NonAutoCategory category);
	
	/**
	 * 获取所有的非车险类型
	 * 
	 * @return
	 */
	List<NonAutoCategory> getAllCategories();
	
	/**
	 * 同时获取多条非车险种类
	 * 
	 * @param cids
	 * @return
	 */
	List<NonAutoCategory> getCategoriesByIds(List<Long> cids);
	
	/**
	 * 通过非车险Id获取非车险信息
	 * 
	 * @param id
	 * @return
	 */
	NonAutoCategory category(long id);
	
	/**
	 * 新增或者修改非车险产品
	 * 
	 * @param product
	 */
	void editProduct(NonAutoProduct product);
	
	/**
	 * 获取非车险产品列表
	 * 
	 * @param searcher
	 * @return
	 */
	Pager<NonAutoProduct> productList(NonAutoProductSearcher searcher);
}
