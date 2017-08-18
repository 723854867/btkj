package org.btkj.lebaoba.vehicle.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.btkj.lebaoba.vehicle.domain.internal.Message;

@XmlRootElement(name = "RETURN")
public class InsureResult {

	private Attach attach;
	private Message message;
	
	@XmlElement(name = "Table")
	public Attach getAttach() {
		return attach;
	}
	public void setAttach(Attach attach) {
		this.attach = attach;
	}
	@XmlElement(name = "MESSAGE")
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return null != message && null != message.getValue() && message.getValue().equals("OK");
	}
	public String error() {
		return null == message ? null : message.getValue();
	}
	public static class Attach {
		private String PolicyID;
		private int PolicyStatus;
		private int CommerceCheckStatus;
		private String CommerceCheckReply;
		private int CompulsoryCheckStatus;
		private String CompulsoryCheckReply;
		private String CommercePolicyNo;
		private String CompulsoryPolicyNo;
		@XmlElement(name = "PolicyID")
		public String getPolicyID() {
			return PolicyID;
		}
		public void setPolicyID(String policyID) {
			PolicyID = policyID;
		}
		@XmlElement(name = "PolicyStatus")
		public int getPolicyStatus() {
			return PolicyStatus;
		}
		public void setPolicyStatus(int policyStatus) {
			PolicyStatus = policyStatus;
		}
		@XmlElement(name = "CommerceCheckStatus")
		public int getCommerceCheckStatus() {
			return CommerceCheckStatus;
		}
		public void setCommerceCheckStatus(int commerceCheckStatus) {
			CommerceCheckStatus = commerceCheckStatus;
		}
		@XmlElement(name = "CommerceCheckReply")
		public String getCommerceCheckReply() {
			return CommerceCheckReply;
		}
		public void setCommerceCheckReply(String commerceCheckReply) {
			CommerceCheckReply = commerceCheckReply;
		}
		@XmlElement(name = "CompulsoryCheckStatus")
		public int getCompulsoryCheckStatus() {
			return CompulsoryCheckStatus;
		}
		public void setCompulsoryCheckStatus(int compulsoryCheckStatus) {
			CompulsoryCheckStatus = compulsoryCheckStatus;
		}
		@XmlElement(name = "CompulsoryCheckReply")
		public String getCompulsoryCheckReply() {
			return CompulsoryCheckReply;
		}
		public void setCompulsoryCheckReply(String compulsoryCheckReply) {
			CompulsoryCheckReply = compulsoryCheckReply;
		}
		@XmlElement(name = "CommercePolicyNo")
		public String getCommercePolicyNo() {
			return CommercePolicyNo;
		}
		public void setCommercePolicyNo(String commercePolicyNo) {
			CommercePolicyNo = commercePolicyNo;
		}
		@XmlElement(name = "CompulsoryPolicyNo")
		public String getCompulsoryPolicyNo() {
			return CompulsoryPolicyNo;
		}
		public void setCompulsoryPolicyNo(String compulsoryPolicyNo) {
			CompulsoryPolicyNo = compulsoryPolicyNo;
		}
	}
}
