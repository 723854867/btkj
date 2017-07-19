package org.btkj.vehicle.mongo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bson.conversions.Bson;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.enums.InsuranceType;
import org.btkj.pojo.enums.VehicleOrderState;
import org.btkj.pojo.po.VehicleOrder;
import org.btkj.vehicle.pojo.model.VehicleOrderSearcher;
import org.rapid.data.storage.mapper.MongoMapper;
import org.rapid.data.storage.mongo.MongoUtil;
import org.rapid.util.lang.CollectionUtil;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.UpdateOptions;

/**
 * 保单映射
 * 
 * @author ahab
 */
public class VehicleOrderMapper extends MongoMapper<String, VehicleOrder> {
	
	private String FIELD_BATCH_ID					= "batchId";
	private String FIELD_STATE						= "state";
	private String FIELD_CREATED					= "created";
	private String FIELD_EMPLOYEE_ID				= "employeeId";
	
	private String FIELD_COMMERCIAL_POLICY_NO		= "tips.detail.commercialNo";
	private String FIELD_COMPULSORY_POLICY_NO		= "tips.detail.compulsiveNo";
	
	public VehicleOrderMapper() {
		super("vehicleOrder");
	}
	
	@Override
	public void insert(VehicleOrder model) {
		mongo.replaceOne(collection, Filters.eq(FIELD_ID, model.key()), model, new UpdateOptions().upsert(true));
	}
	
	public void deleteBatchOrder(String batchId) {
		mongo.deleteMany(collection, Filters.eq(FIELD_BATCH_ID, batchId));
	}
	
	public long orderNum(int employeeId, int begin, int end, int stateMod) {
		Filters.and(Filters.eq(FIELD_EMPLOYEE_ID, employeeId), 
				Filters.gte(FIELD_CREATED, begin), Filters.lte(FIELD_CREATED, end));
		List<Bson> states = new ArrayList<Bson>();
		for (VehicleOrderState state : VehicleOrderState.values()) {
			if ((state.mark() & stateMod) != state.mark())
				continue;
			states.add(Filters.eq(FIELD_STATE, state.name()));
		}
		Bson filter = CollectionUtil.isEmpty(states) 
				? Filters.and(Filters.eq(FIELD_EMPLOYEE_ID, employeeId), Filters.gte(FIELD_CREATED, begin), Filters.lte(FIELD_CREATED, end)) 
				: Filters.and(Filters.eq(FIELD_EMPLOYEE_ID, employeeId), Filters.gte(FIELD_CREATED, begin), Filters.lte(FIELD_CREATED, end), Filters.or(states));
		return mongo.count(collection, filter);
	}
	
	public Pager<VehicleOrder> paging(VehicleOrderSearcher searcher) {
		List<VehicleOrder> orders = null;
		long total = 0;
		List<Bson> list = new ArrayList<Bson>();
		if (null != searcher.getBatchId())
			list.add(Filters.eq(FIELD_BATCH_ID, searcher.getBatchId()));
		if (null != searcher.getEmployeeId())
			list.add(Filters.eq(FIELD_EMPLOYEE_ID, searcher.getEmployeeId()));
		if (null != searcher.getState()) {
			switch (searcher.getState()) {
			case INSURE:
				list.add(Filters.or(Filters.eq(FIELD_STATE, VehicleOrderState.QUOTING.name()), 
						Filters.eq(FIELD_STATE, VehicleOrderState.QUOTE_SUCCESS.name()), 
						Filters.eq(FIELD_STATE, VehicleOrderState.INSURING.name()), 
						Filters.eq(FIELD_STATE, VehicleOrderState.INSURE_FAILURE.name())));
				break;
			case QUOTE_SUCCESS:
				list.add(Filters.eq(FIELD_STATE, VehicleOrderState.QUOTE_SUCCESS.name()));
				break;
			case INSURE_FAILURE:
				list.add(Filters.eq(FIELD_STATE, VehicleOrderState.INSURE_FAILURE.name()));
				break;
			case INSURING:
				list.add(Filters.eq(FIELD_STATE, VehicleOrderState.INSURING.name()));
				break;
			case ISSUE:
				list.add(Filters.or(Filters.eq(FIELD_STATE, VehicleOrderState.INSURE_SUCCESS.name()), 
						Filters.eq(FIELD_STATE, VehicleOrderState.ISSUE_APPOINTED.name()), 
						Filters.eq(FIELD_STATE, VehicleOrderState.ISSUED.name())));
				break;
			case INSURE_SUCCESS:
				list.add(Filters.eq(FIELD_STATE, VehicleOrderState.INSURE_SUCCESS.name()));
				break;
			case ISSUE_SUCCESS:
				list.add(Filters.eq(FIELD_STATE, VehicleOrderState.ISSUE_APPOINTED.name()));
				break;
			case ISSUED:
				list.add(Filters.eq(FIELD_STATE, VehicleOrderState.ISSUED.name()));
				break;
			default:
				break;
			}
		}
		total = list.isEmpty() ? mongo.count(collection) : mongo.count(collection, Filters.and(list));
		if (0 == total)
			return Pager.EMPLTY;
		searcher.calculate((int) total);
		orders = list.isEmpty() ?
				mongo.pagingAndSort(collection, Sorts.descending(FIELD_CREATED), 
							searcher.getStart(), searcher.getPageSize(), VehicleOrder.class):
				mongo.pagingAndSort(collection, Filters.and(list), Sorts.descending(FIELD_CREATED),
							searcher.getStart(), searcher.getPageSize(), VehicleOrder.class);
		return new Pager<VehicleOrder>(searcher.getTotal(), orders);
	}
	
	/**
	 * 通过投保单号获取订单：只获取 state 为 insure_success
	 * 
	 * @param type
	 * @param policyNo
	 * @return
	 */
	public List<VehicleOrder> getByNos(InsuranceType type, Set<String> nos) {
		Bson bson = MongoUtil.or(type == InsuranceType.COMMERCIAL ? FIELD_COMMERCIAL_POLICY_NO : FIELD_COMPULSORY_POLICY_NO, nos);
		bson = Filters.and(bson, Filters.eq(FIELD_STATE, VehicleOrderState.INSURE_SUCCESS.name()));
		return mongo.find(collection, bson, clazz);
	}
}
