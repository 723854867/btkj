package org.btkj.pojo.submit;

import java.io.Serializable;

import org.btkj.pojo.entity.Renewal;

/**
 * 车险下单需要的提交资料
 * 
 * @author ahab
 */
public class VehicleOrderSubmit implements Serializable {

	private static final long serialVersionUID = -108775801806960634L;

	private int quoteMod;			// 报价模值(需要报价的保险公司的模值之和)
	private int insureMod;			// 投保模值(需要核保的保险公司的模值之和：只能是 quoteMod 的子集)
	private Renewal renewal;
	
	public int getQuoteMod() {
		return quoteMod;
	}
	
	public void setQuoteMod(int quoteMod) {
		this.quoteMod = quoteMod;
	}
	
	public int getInsureMod() {
		return insureMod;
	}
	
	public void setInsureMod(int insureMod) {
		this.insureMod = insureMod;
	}
	
	public Renewal getRenewal() {
		return renewal;
	}
	
	public void setRenewal(Renewal renewal) {
		this.renewal = renewal;
	}
}
