package org.btkj.pojo.model.insur.vehicle;

import java.io.Serializable;

import org.btkj.pojo.enums.DeliveryType;
import org.btkj.pojo.model.Recipient;

/**
 * 配送信息
 * 
 * @author ahab
 */
public class DeliveryInfo implements Serializable {

	private static final long serialVersionUID = 4261026604826735899L;

	private DeliveryType type;
	private Recipient recipient;

	public DeliveryType getType() {
		return type;
	}

	public void setType(DeliveryType type) {
		this.type = type;
	}

	public Recipient getRecipient() {
		return recipient;
	}

	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}
}
