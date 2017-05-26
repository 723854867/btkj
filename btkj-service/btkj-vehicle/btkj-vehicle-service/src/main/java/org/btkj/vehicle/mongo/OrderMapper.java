package org.btkj.vehicle.mongo;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.bson.conversions.Bson;
import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.pojo.enums.PolicyState;
import org.rapid.data.storage.mapper.MongoMapper;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;

/**
 * 保单映射
 * 
 * @author ahab
 */
public class OrderMapper extends MongoMapper<String, VehicleOrder> {
	
	private String FIELD_STATE				= "policies.{0}.state";
	private String FIELD_DESC				= "policies.{0}.desc";
	
	public OrderMapper() {
		super("vehicle_order");
	}
	
	@Override
	public VehicleOrder insert(VehicleOrder model) {
		mongo.replaceOne(collection, Filters.eq(FIELD_ID, model.key()), serial(model), new UpdateOptions().upsert(true));
		return model;
	}
	
	/**
	 * 发送壁虎请求失败
	 * 
	 * @param list
	 */
	public void biHuRequestFailure(VehicleOrder model, List<Integer> list, String desc) {
		List<Bson> bsons = new ArrayList<Bson>();
		for (Integer insurerId : list) {
			bsons.add(Updates.set(MessageFormat.format(FIELD_STATE, String.valueOf(insurerId)), PolicyState.SYSTEM_ERROR.toString()));
			bsons.add(Updates.set(MessageFormat.format(FIELD_DESC, String.valueOf(insurerId)), desc));
		}
		mongo.findOneAndUpdate(collection, Filters.eq(FIELD_ID, model.key()), Filters.and(bsons));
	}
}
