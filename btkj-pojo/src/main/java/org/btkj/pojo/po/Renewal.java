package org.btkj.pojo.po;

import org.btkj.pojo.vo.VehiclePolicyTips;
import org.rapid.util.common.model.UniqueModel;

/**
 * 续保信息
 * 
 * @author ahab
 */
public class Renewal implements UniqueModel<String> {

	private static final long serialVersionUID = 6082011346840718571L;

	private String _id;
	private int insurerId;						// 保险公司ID
	private String insurerName;					// 保险公司名字
	private String insurerIcon;					// 保险公司图标
	
	private String commercialNo;				// 商业险保单号
	private String compulsiveNo;				// 交强险保单号
	private VehiclePolicyTips tips;
	private int created;						// 创建时间
	
	public String get_id() {
		return _id;
	}
	
	public void set_id(String _id) {
		this._id = _id;
	}
	
	public int getInsurerId() {
		return insurerId;
	}
	
	public void setInsurerId(int insurerId) {
		this.insurerId = insurerId;
	}
	
	public String getInsurerName() {
		return insurerName;
	}
	
	public void setInsurerName(String insurerName) {
		this.insurerName = insurerName;
	}
	
	public String getInsurerIcon() {
		return insurerIcon;
	}
	
	public void setInsurerIcon(String insurerIcon) {
		this.insurerIcon = insurerIcon;
	}
	
	public String getCommercialNo() {
		return commercialNo;
	}
	
	public void setCommercialNo(String commercialNo) {
		this.commercialNo = commercialNo;
	}
	
	public String getCompulsiveNo() {
		return compulsiveNo;
	}
	
	public void setCompulsiveNo(String compulsiveNo) {
		this.compulsiveNo = compulsiveNo;
	}
	
	public VehiclePolicyTips getTips() {
		return tips;
	}
	
	public void setTips(VehiclePolicyTips tips) {
		this.tips = tips;
	}
	
	public int getCreated() {
		return created;
	}
	
	public void setCreated(int created) {
		this.created = created;
	}

	@Override
	public String key() {
		return this._id;
	}
}
