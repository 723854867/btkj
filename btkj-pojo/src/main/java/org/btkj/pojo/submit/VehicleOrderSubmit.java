package org.btkj.pojo.submit;

import java.io.Serializable;
import java.util.Set;

import org.btkj.pojo.entity.Renewal;

/**
 * 车险下单需要的提交资料
 * 
 * @author ahab
 */
public class VehicleOrderSubmit implements Serializable {

	private static final long serialVersionUID = -108775801806960634L;

	private Set<Integer> quote;						// 报价公司ID列表
	private Set<Integer> insure;					// 投保公司ID列表
	private Renewal renewal;
	
	public Set<Integer> getQuote() {
		return quote;
	}
	
	public void setQuote(Set<Integer> quote) {
		this.quote = quote;
	}
	
	public Set<Integer> getInsure() {
		return insure;
	}
	
	public void setInsure(Set<Integer> insure) {
		this.insure = insure;
	}
	
	public Renewal getRenewal() {
		return renewal;
	}
	
	public void setRenewal(Renewal renewal) {
		this.renewal = renewal;
	}
}
