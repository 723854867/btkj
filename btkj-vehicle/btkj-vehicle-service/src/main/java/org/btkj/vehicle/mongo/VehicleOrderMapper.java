package org.btkj.vehicle.mongo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.pojo.enums.PolicyState;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.model.insur.vehicle.PolicyDetail;
import org.btkj.pojo.model.insur.vehicle.PolicySchema;
import org.btkj.vehicle.model.VehicleOrderListInfo;
import org.btkj.vehicle.model.VehicleOrderSearcher;
import org.rapid.data.storage.mapper.MongoMapper;
import org.rapid.util.common.serializer.SerializeUtil;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;

/**
 * 保单映射
 * 
 * @author ahab
 */
public class VehicleOrderMapper extends MongoMapper<String, VehicleOrder> {
	
	private String FIELD_BATCH_ID					= "batchId";
	private String FIELD_STATE						= "state";
	private String FIELD_DESC					 	= "desc";
	private String FIELD_SCHEMA						= "tips.schema";
	private String FIELD_DETAIL						= "tips.detail";
	private String FIELD_CREATED					= "created";
	
	public VehicleOrderMapper() {
		super("vehicle_order");
	}
	
	@Override
	public void insert(VehicleOrder model) {
		mongo.replaceOne(collection, Filters.eq(FIELD_ID, model.key()), serial(model), new UpdateOptions().upsert(true));
	}
	
	public void deleteBatchOrder(String batchId) {
		mongo.deleteMany(collection, Filters.eq(FIELD_BATCH_ID, batchId));
	}
	
	public Pager<VehicleOrderListInfo> list(EmployeeForm ef, VehicleOrderSearcher searcher) {
		List<Bson> list = new ArrayList<Bson>();
		if (null != searcher.getState())
			list.add(Filters.eq(FIELD_STATE, searcher.getState().toString()));
		long total = list.isEmpty() ? mongo.count(collection) : mongo.count(collection, Filters.and(list));
		if (0 == total)
			return Pager.EMPLTY;
		searcher.calculate((int) total);
		List<VehicleOrder> orders = list.isEmpty() ?
				mongo.pagingAndSort(collection, Sorts.descending(FIELD_CREATED), 
							searcher.getStart(), searcher.getPageSize(), VehicleOrder.class):
				mongo.pagingAndSort(collection, Filters.and(list), Sorts.descending(FIELD_CREATED),
							searcher.getStart(), searcher.getPageSize(), VehicleOrder.class);
		List<VehicleOrderListInfo> result = new ArrayList<VehicleOrderListInfo>(orders.size());
		for (VehicleOrder order : orders)
			result.add(new VehicleOrderListInfo(order));
		return new Pager<VehicleOrderListInfo>(searcher.getTotal(), result);
	}
	
	/**
	 * 发送壁虎请求失败
	 * 
	 * @param list
	 */
	public void requestFailure(Map<Integer, VehicleOrder> orders, Set<Integer> insurers, String desc) {
		if (null == insurers)
			return;
		for (Integer insurerId : insurers) {
			VehicleOrder order = orders.get(insurerId);
			Bson update = Filters.and(Updates.set(FIELD_STATE, PolicyState.SYSTEM_ERROR.toString()), Updates.set(FIELD_DESC, desc));
			mongo.findOneAndUpdate(collection, Filters.eq(FIELD_ID, order.key()), update);
		}
	}
	
	public void quoteFailure(VehicleOrder model, String desc) {
		mongo.findOneAndUpdate(collection, Filters.eq(FIELD_ID, model.key()), 
				Filters.and(Updates.set(FIELD_STATE, PolicyState.QUOTE_FAILURE.toString()), Updates.set(FIELD_DESC, desc)));
	}
	
	public void quoteSuccess(VehicleOrder model, String desc, PolicySchema schema) {
		mongo.findOneAndUpdate(collection, Filters.eq(FIELD_ID, model.key()), 
				Filters.and(Updates.set(FIELD_SCHEMA, Document.parse(SerializeUtil.JsonUtil.GSON.toJson(schema))),
						Updates.set(FIELD_STATE, PolicyState.QUOTE_SUCCESS.toString()), Updates.set(FIELD_DESC, desc)));
	}
	
	public void insureFailure(VehicleOrder model, String desc) {
		mongo.findOneAndUpdate(collection, Filters.eq(FIELD_ID, model.key()), 
				Filters.and(Updates.set(FIELD_STATE, PolicyState.QUOTE_SUCCESS_INSURE_FAILURE.toString()), Updates.set(FIELD_DESC, desc)));
	}
	
	public void insureSuccess(VehicleOrder model, PolicyDetail detail, String desc) {
		mongo.findOneAndUpdate(collection, Filters.eq(FIELD_ID, model.key()), 
				Filters.and(Updates.set(FIELD_DETAIL, Document.parse(SerializeUtil.JsonUtil.GSON.toJson(detail))),
						Updates.set(FIELD_STATE, PolicyState.INSURE_SUCCESS.toString()),Updates.set(FIELD_DESC, desc)));
	}
}
