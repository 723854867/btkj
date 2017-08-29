package org.btkj.vehicle.pojo.model;

import java.io.Serializable;
import java.util.LinkedList;

import org.rapid.util.common.Consts;

/**
 * 手续费递归消息类型
 * 
 * @author ahab
 */
public interface PoundageErogidicMessage extends Serializable {

	int getTid();
	
	int getInsurerId();
	
	LinkedList<Integer> getNodePath();
	
	default String configKey() {
		return getTid() + Consts.SYMBOL_UNDERLINE + getInsurerId();
	}
}
