package org.btkj.pojo.param.nonauto;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.NonAutoProduct;
import org.btkj.pojo.param.Param;
import org.rapid.util.lang.DateUtil;

public class NonAutoProductEditParam extends Param {

	private static final long serialVersionUID = -4600785135515450706L;

	@Min(0)
	private long id;
	@Min(1)
	private long cid;						// 所属非车险类型ID
	@NotNull
	@Size(min = BtkjConsts.LIMITS.NAME_MIN, max = BtkjConsts.LIMITS.NAME_MAX)
	private String name; 					// 名字
	@Min(1)
	private int price; 						// 价格(单位：分)
	@Min(0)
	@Max(900)
	private int bonus; 						// 返利点数
	@Min(1)
	private int insurerId; 					// 保险公司id
	@NotNull
	@Size(min = BtkjConsts.LIMITS.NAME_MIN, max = BtkjConsts.LIMITS.NAME_MAX)
	private String coverage; 				// 保额
	@NotNull
	@Size(min = BtkjConsts.LIMITS.URL_MIN, max = BtkjConsts.LIMITS.NAME_MAX)
	private String icon; 					// 产品展示图片链接
	@NotNull
	@Size(min = BtkjConsts.LIMITS.URL_MIN, max = BtkjConsts.LIMITS.NAME_MAX)
	private String link; 					// 产品 h5 链接
	@Size(max = BtkjConsts.LIMITS.NONAUTO_TAG_MAX)
	private List<String> tags; 				// 产品标签
	@Size(max = BtkjConsts.LIMITS.NONAUTO_FILTER_MAX)
	private Map<String, String> filters;	// 筛选条件
	@NotNull
	@Size(min = BtkjConsts.LIMITS.NONAUTO_LIABILITY_MIN, max = BtkjConsts.LIMITS.NONAUTO_LIABILITY_MAX)
	private List<String> liabilities; 		// 保险责任:仅供参考，详细条款会在产品的 h5 页面展示
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public int getInsurerId() {
		return insurerId;
	}

	public void setInsurerId(int insurerId) {
		this.insurerId = insurerId;
	}

	public String getCoverage() {
		return coverage;
	}

	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Map<String, String> getFilters() {
		return filters;
	}

	public void setFilters(Map<String, String> filters) {
		this.filters = filters;
	}

	public List<String> getLiabilities() {
		return liabilities;
	}

	public void setLiabilities(List<String> liabilities) {
		this.liabilities = liabilities;
	}

	public NonAutoProduct entity() {
		NonAutoProduct product = new NonAutoProduct();
		product.set_id(id);
		product.setCid(cid);
		product.setName(name);
		product.setPrice(price);
		product.setBonus(bonus);
		product.setInsurerId(insurerId);
		product.setCoverage(coverage);
		product.setIcon(icon);
		product.setLink(link);
		product.setTags(tags);
		product.setFilters(filters);
		product.setLiabilities(liabilities);
		
		int time = DateUtil.currentTime();
		if (0 == id)
			product.setCreated(time);
		product.setUpdated(time);
		return product;
	}
}
