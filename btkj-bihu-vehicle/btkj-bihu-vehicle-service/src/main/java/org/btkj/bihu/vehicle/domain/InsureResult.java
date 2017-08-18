package org.btkj.bihu.vehicle.domain;

import java.io.Serializable;

import org.btkj.bihu.vehicle.RespHandler;
import org.btkj.pojo.model.VehicleAuditModel;

public class InsureResult implements Serializable {

	private static final long serialVersionUID = 4948744581074288324L;

	public static final RespHandler<InsureResult> JSON_HANDLER			= RespHandler.build(InsureResult.class);

	private int BusinessStatus;
	private String StatusMessage;
	private Item Item;
	private String CustKey;
	
	public int getBusinessStatus() {
		return BusinessStatus;
	}
	
	public void setBusinessStatus(int businessStatus) {
		BusinessStatus = businessStatus;
	}
	
	public String getStatusMessage() {
		return StatusMessage;
	}
	
	public void setStatusMessage(String statusMessage) {
		StatusMessage = statusMessage;
	}
	
	public Item getItem() {
		return Item;
	}
	
	public void setItem(Item item) {
		Item = item;
	}
	
	public String getCustKey() {
		return CustKey;
	}
	
	public void setCustKey(String custKey) {
		CustKey = custKey;
	}
	
	public class Item implements Serializable {
		private static final long serialVersionUID = -5431311046813637321L;
		private int Source;
		private int SubmitStatus;		// 0:核保失败，1：核保成功，2：未到期核保(无需再核保),3:人工审核中(单独调用第四个接口)，4：非意向和报(没有报价)，5：报价失败未核保
		private String SubmitResult;
		private String BizNo;
		private String ForceNo;
		private double BizRate;
		private double ForceRate;
		public int getSource() {
			return Source;
		}
		public void setSource(int source) {
			Source = source;
		}
		public int getSubmitStatus() {
			return SubmitStatus;
		}
		public void setSubmitStatus(int submitStatus) {
			SubmitStatus = submitStatus;
		}
		public String getSubmitResult() {
			return SubmitResult;
		}
		public void setSubmitResult(String submitResult) {
			SubmitResult = submitResult;
		}
		public String getBizNo() {
			return BizNo;
		}
		public void setBizNo(String bizNo) {
			BizNo = bizNo;
		}
		public String getForceNo() {
			return ForceNo;
		}
		public void setForceNo(String forceNo) {
			ForceNo = forceNo;
		}
		public double getBizRate() {
			return BizRate;
		}
		public void setBizRate(double bizRate) {
			BizRate = bizRate;
		}
		public double getForceRate() {
			return ForceRate;
		}
		public void setForceRate(double forceRate) {
			ForceRate = forceRate;
		}
	}
	
	public VehicleAuditModel auditModel() {
		return new VehicleAuditModel(this.Item.BizNo, this.Item.ForceNo);
	}
}
