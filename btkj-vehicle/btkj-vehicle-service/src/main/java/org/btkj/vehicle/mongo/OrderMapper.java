package org.btkj.vehicle.mongo;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.pojo.enums.PolicyState;
import org.btkj.pojo.model.insur.vehicle.InsuranceSchema;
import org.btkj.pojo.model.insur.vehicle.Policy;
import org.btkj.pojo.model.insur.vehicle.PolicyDetail;
import org.rapid.data.storage.mapper.MongoMapper;
import org.rapid.util.common.serializer.SerializeUtil;

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
	private String FIELD_SCHEMA				= "policies.{0}.schema";
	private String FIELD_DETAIL				= "policies.{0}.detail";
	
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
	public void requestFailure(VehicleOrder model, Set<Integer> list, String desc) {
		List<Bson> bsons = new ArrayList<Bson>();
		for (Integer insurerId : list) {
			bsons.add(Updates.set(MessageFormat.format(FIELD_STATE, String.valueOf(insurerId)), PolicyState.SYSTEM_ERROR.toString()));
			bsons.add(Updates.set(MessageFormat.format(FIELD_DESC, String.valueOf(insurerId)), desc));
		}
		mongo.findOneAndUpdate(collection, Filters.eq(FIELD_ID, model.key()), Filters.and(bsons));
	}
	
	public void quoteFailure(VehicleOrder model, Policy policy, String desc) {
		mongo.findOneAndUpdate(collection, Filters.eq(FIELD_ID, model.key()), 
				Filters.and(
						Updates.set(MessageFormat.format(FIELD_STATE, String.valueOf(policy.getInsurerId())), PolicyState.QUOTE_FAILURE.toString()),
						Updates.set(MessageFormat.format(FIELD_DESC, String.valueOf(policy.getInsurerId())), desc)));
	}
	
	public void quoteSuccess(VehicleOrder model, Policy policy, String desc, InsuranceSchema schema) {
		mongo.findOneAndUpdate(collection, Filters.eq(FIELD_ID, model.key()), 
				Filters.and(
						Updates.set(
								MessageFormat.format(FIELD_SCHEMA, String.valueOf(policy.getInsurerId())), 
								Document.parse(SerializeUtil.JsonUtil.GSON.toJson(schema))),
						Updates.set(MessageFormat.format(FIELD_STATE, String.valueOf(policy.getInsurerId())), 
								PolicyState.QUOTE_SUCCESS.toString()),
						Updates.set(MessageFormat.format(FIELD_DESC, String.valueOf(policy.getInsurerId())), desc)));
	}
	
	public void insureFailure(VehicleOrder model, Policy policy, String desc) {
		mongo.findOneAndUpdate(collection, Filters.eq(FIELD_ID, model.key()), 
				Filters.and(
						Updates.set(MessageFormat.format(FIELD_STATE, String.valueOf(policy.getInsurerId())), PolicyState.QUOTE_SUCCESS_INSURE_FAILURE.toString()),
						Updates.set(MessageFormat.format(FIELD_DESC, String.valueOf(policy.getInsurerId())), desc)));
	}
	
	public void insureSuccess(VehicleOrder model, Policy policy, PolicyDetail detail, String desc) {
		mongo.findOneAndUpdate(collection, Filters.eq(FIELD_ID, model.key()), 
				Filters.and(
						Updates.set(
								MessageFormat.format(FIELD_DETAIL, String.valueOf(policy.getInsurerId())), 
								Document.parse(SerializeUtil.JsonUtil.GSON.toJson(detail))),
						Updates.set(MessageFormat.format(FIELD_STATE, String.valueOf(policy.getInsurerId())), 
								PolicyState.INSURE_SUCCESS.toString()),
						Updates.set(MessageFormat.format(FIELD_DESC, String.valueOf(policy.getInsurerId())), desc)));
	}
}
