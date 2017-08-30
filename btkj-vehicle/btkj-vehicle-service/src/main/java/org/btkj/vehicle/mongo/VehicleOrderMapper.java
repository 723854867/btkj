package org.btkj.vehicle.mongo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.conversions.Bson;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.pojo.enums.InsuranceType;
import org.btkj.pojo.enums.VehicleOrderState;
import org.btkj.pojo.model.Pager;
import org.btkj.vehicle.pojo.enums.Lane;
import org.btkj.vehicle.pojo.param.VehicleOrdersParam;
import org.rapid.data.storage.mapper.MongoMapper;
import org.rapid.data.storage.mongo.MongoUtil;
import org.rapid.util.lang.CollectionUtil;
import org.springframework.stereotype.Component;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;

/**
 * 保单映射
 * 
 * @author ahab
 */
@Component("vehicleOrderMapper")
public class VehicleOrderMapper extends MongoMapper<String, VehicleOrder> {
	
	private String FIELD_LICENSE					= "tips.license";
	private String FIELD_COMMERCIAL_POLICY_NO		= "tips.detail.commercialNo";
	private String FIELD_COMPULSORY_POLICY_NO		= "tips.detail.compulsiveNo";
	
	public VehicleOrderMapper() {
		super("vehicleOrder");
	}
	
	@Override
	public void insert(VehicleOrder model) {
		mongo.replaceOne(collection, Filters.eq(FIELD_ID, model.key()), model, new UpdateOptions().upsert(true));
	}
	
	public void insert(Map<Lane, Map<Object, VehicleOrder>> orders) {
		Map<String, VehicleOrder> map = new HashMap<String, VehicleOrder>();
		for (Map<Object, VehicleOrder> temp : orders.values()) {
			for (VehicleOrder order : temp.values())
				map.put(order.get_id(), order);
		}
		mongo.bulkReplaceOne(collection, map);
	}
	
	public void delete(int employeeId, String license) {
		mongo.deleteMany(collection, Filters.and(Filters.eq(BtkjConsts.FIELD.EMPLOYEEID, employeeId), Filters.eq(FIELD_LICENSE, license)));
	}
	
	public long orderNum(int employeeId, int begin, int end, int stateMod) {
		Filters.and(Filters.eq(BtkjConsts.FIELD.EMPLOYEEID, employeeId), 
				Filters.gte(BtkjConsts.FIELD.CREATED, begin), Filters.lte(BtkjConsts.FIELD.CREATED, end));
		List<Bson> states = new ArrayList<Bson>();
		for (VehicleOrderState state : VehicleOrderState.values()) {
			if ((state.mark() & stateMod) != state.mark())
				continue;
			states.add(Filters.eq(BtkjConsts.FIELD.STATE, state.name()));
		}
		Bson filter = CollectionUtil.isEmpty(states) 
				? Filters.and(Filters.eq(BtkjConsts.FIELD.EMPLOYEEID, employeeId), Filters.gte(BtkjConsts.FIELD.CREATED, begin), Filters.lte(BtkjConsts.FIELD.CREATED, end)) 
				: Filters.and(Filters.eq(BtkjConsts.FIELD.EMPLOYEEID, employeeId), Filters.gte(BtkjConsts.FIELD.CREATED, begin), Filters.lte(BtkjConsts.FIELD.CREATED, end), Filters.or(states));
		return mongo.count(collection, filter);
	}
	
	public Pager<VehicleOrder> orders(VehicleOrdersParam param) {
		List<VehicleOrder> orders = null;
		long total = 0;
		List<Bson> list = new ArrayList<Bson>();
		if (null != param.getAppId())
			list.add(Filters.eq(BtkjConsts.FIELD.APPID, param.getAppId()));
		if (null != param.getLicense())
			list.add(Filters.eq(FIELD_LICENSE, param.getLicense()));
		if (null != param.getTarId())
			list.add(Filters.eq(BtkjConsts.FIELD.EMPLOYEEID, param.getTarId()));
		if (null != param.getUid())
			list.add(Filters.eq(BtkjConsts.FIELD.UID, param.getUid()));
		if (null != param.getTid())
			list.add(Filters.eq(BtkjConsts.FIELD.TID, param.getTid()));
		if (null != param.getState()) {
			switch (param.getState()) {
			case INSURE:
				list.add(Filters.or(
						Filters.eq(BtkjConsts.FIELD.STATE, VehicleOrderState.QUOTING.name()), 
						Filters.eq(BtkjConsts.FIELD.STATE, VehicleOrderState.INSURING.name()), 
						Filters.eq(BtkjConsts.FIELD.STATE, VehicleOrderState.QUOTE_SUCCESS.name()),
						Filters.eq(BtkjConsts.FIELD.STATE, VehicleOrderState.INSURE_FAILURE.name())));
				break;
			case QUOTE_SUCCESS:
				list.add(Filters.eq(BtkjConsts.FIELD.STATE, VehicleOrderState.QUOTE_SUCCESS.name()));
				break;
			case INSURE_FAILURE:
				list.add(Filters.eq(BtkjConsts.FIELD.STATE, VehicleOrderState.INSURE_FAILURE.name()));
				break;
			case INSURING:
				list.add(Filters.eq(BtkjConsts.FIELD.STATE, VehicleOrderState.INSURING.name()));
				break;
			case ISSUE:
				list.add(Filters.or(
						Filters.eq(BtkjConsts.FIELD.STATE, VehicleOrderState.ISSUED.name()),
						Filters.eq(BtkjConsts.FIELD.STATE, VehicleOrderState.INSURE_SUCCESS.name()), 
						Filters.eq(BtkjConsts.FIELD.STATE, VehicleOrderState.ISSUE_APPOINTED.name())));
				break;
			case INSURE_SUCCESS:
				list.add(Filters.eq(BtkjConsts.FIELD.STATE, VehicleOrderState.INSURE_SUCCESS.name()));
				break;
			case ISSUE_SUCCESS:
				list.add(Filters.eq(BtkjConsts.FIELD.STATE, VehicleOrderState.ISSUE_APPOINTED.name()));
				break;
			case ISSUED:
				list.add(Filters.eq(BtkjConsts.FIELD.STATE, VehicleOrderState.ISSUED.name()));
				break;
			default:
				break;
			}
		}
		total = list.isEmpty() ? mongo.count(collection) : mongo.count(collection, Filters.and(list));
		if (0 == total)
			return Pager.EMPLTY;
		param.calculate((int) total);
		orders = list.isEmpty() ?
				mongo.pagingAndSort(collection, Sorts.descending(BtkjConsts.FIELD.CREATED), 
							param.getStart(), param.getPageSize(), VehicleOrder.class):
				mongo.pagingAndSort(collection, Filters.and(list), Sorts.descending(BtkjConsts.FIELD.CREATED),
							param.getStart(), param.getPageSize(), VehicleOrder.class);
		return new Pager<VehicleOrder>(total, orders);
	}
	
	/**
	 * 通过投保单号获取订单：只获取 state 为 insure_success
	 * 
	 * @param type
	 * @param policyNo
	 * @return
	 */
	public List<VehicleOrder> getByDeliverNos(InsuranceType type, Set<String> nos) {
		Bson bson = MongoUtil.or(type == InsuranceType.COMMERCIAL ? FIELD_COMMERCIAL_POLICY_NO : FIELD_COMPULSORY_POLICY_NO, nos);
		bson = Filters.and(bson, Filters.eq(BtkjConsts.FIELD.STATE, VehicleOrderState.INSURE_SUCCESS.name()));
		return mongo.find(collection, bson, clazz);
	}
	
	/**
	 * 更新已出单
	 * 
	 * @param orders
	 */
	public void issuedUpdate(List<VehicleOrder> orders) {
		if (CollectionUtil.isEmpty(orders))
			return;
		Map<Bson, Bson> updates = new HashMap<Bson, Bson>();
		for (VehicleOrder order : orders) 
			updates.put(Filters.eq(FIELD_ID, order.get_id()), 
					Filters.and(Updates.set(BtkjConsts.FIELD.STATE, order.getState()), Updates.set(BtkjConsts.FIELD.POLICYID, order.getPolicyId())));
		mongo.bulkUpdateOne(collection, updates);
	}
	
	public Map<String, VehicleOrder> rewardStandbyUpdate(int tid) {
		mongo.update(collection, Filters.eq(BtkjConsts.FIELD.STATE, VehicleOrderState.ISSUED.name()), Updates.set(BtkjConsts.FIELD.STATE, VehicleOrderState.REWARD_SDANDBY.name()));
		return mongo.findMap(collection, Filters.eq(BtkjConsts.FIELD.STATE, VehicleOrderState.REWARD_SDANDBY.name()), VehicleOrder.class);
	}
	
	public void rewardComplete(int tid) {
		mongo.update(collection, Filters.eq(BtkjConsts.FIELD.STATE, VehicleOrderState.REWARD_SDANDBY), Updates.set(BtkjConsts.FIELD.STATE, VehicleOrderState.REWARDED));
	}
}
