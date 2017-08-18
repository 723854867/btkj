package org.btkj.nonauto.api;

import java.util.List;

import org.btkj.nonauto.pojo.param.NonAutoProductListParam;
import org.btkj.pojo.entity.NonAutoCategory;
import org.btkj.pojo.entity.NonAutoProduct;
import org.btkj.pojo.model.Pager;
import org.rapid.util.common.message.Result;

/**
 * 非车险服务类
 * 
 * @author ahab
 */
public interface NonAutoService {

	/**
	 * 获取所有的非车险类型
	 * 
	 * @return
	 */
	List<NonAutoCategory> categories();

	/**
	 * 新增或修改非车险类型
	 * 
	 * @param category
	 */
	void editCategory(NonAutoCategory category);
	
	/**
	 * 通过非车险Id获取非车险信息
	 * 
	 * @param id
	 * @return
	 */
	NonAutoCategory category(long id);
	
	/**
	 * 同时获取多条非车险种类
	 * 
	 * @param cids
	 * @return
	 */
	List<NonAutoCategory> getCategoriesByIds(List<Long> cids);
	
	/**
	 * 新增或者修改非车险产品
	 * 
	 * @param product
	 */
	Result<Void> editProduct(NonAutoProduct product);
	
	/**
	 * 非车险产品分页
	 * 
	 * @param param
	 * @return
	 */
	Pager<NonAutoProduct> products(NonAutoProductListParam param);
}
