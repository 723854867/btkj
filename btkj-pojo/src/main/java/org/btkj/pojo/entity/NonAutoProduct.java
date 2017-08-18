package org.btkj.pojo.entity;

import java.util.List;
import java.util.Map;

import org.rapid.util.common.model.UniqueModel;

public class NonAutoProduct implements UniqueModel<Long> {

	private static final long serialVersionUID = -5735762345442485441L;

	private long _id; 						// 唯一ID
	private long cid;						// 所属非车险类型ID
	private String name; 					// 名字
	private int price; 						// 价格
	private int bonus; 					// 返利点数
	private int insurerId; 					// 保险公司id
	private long sales; 					// 销量
	private String coverage; 				// 保额
	private String icon; 					// 产品展示图片链接
	private String link; 					// 产品 h5 链接
	private List<String> tags; 				// 产品标签
	private Map<String, String> filters;	// 筛选条件
	private List<String> liabilities; 		// 保险责任:仅供参考，详细条款会在产品的 h5 页面展示
	private int created;
	private int updated;

	public long get_id() {
		return _id;
	}
	
	public void set_id(long _id) {
		this._id = _id;
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

	public long getSales() {
		return sales;
	}

	public void setSales(long sales) {
		this.sales = sales;
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

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	public int getUpdated() {
		return updated;
	}

	public void setUpdated(int updated) {
		this.updated = updated;
	}
	
	@Override
	public Long key() {
		return this._id;
	}
}
