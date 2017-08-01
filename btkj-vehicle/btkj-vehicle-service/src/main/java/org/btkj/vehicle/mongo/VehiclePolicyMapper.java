package org.btkj.vehicle.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.conversions.Bson;
import org.btkj.pojo.bo.Pager;
import org.btkj.vehicle.pojo.entity.VehiclePolicy;
import org.btkj.vehicle.pojo.submit.VehiclePolicySearcher;
import org.rapid.data.storage.mapper.MongoMapper;
import org.springframework.stereotype.Component;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

@Component("vehiclePolicyMapper")
public class VehiclePolicyMapper extends MongoMapper<String, VehiclePolicy> {
	
	private String FIELD_TID						= "tid";
	private String FIELD_UID						= "uid";
	private String FIELD_APP_ID						= "appId";
	private String FIELD_EMPLOYEE_ID				= "employeeId";
	private String FIELD_INSURER_ID					= "insurerId";
	private String FIELD_LICENSE					= "license";
	private String FIELD_OWNER						= "owner";
	private String FIELD_SALESMAN					= "salesman";
	private String FIELD_TRANSFER					= "transfer";
	private String FIELD_NATURE						= "nature";
	private String FIELD_TYPE						= "type";
	private String FIELD_BONUS_TYPE					= "bonusType";
	private String FIELD_CREATED					= "created";
	private String FIELD_ISSUE_TIME					= "issueTime";

	public VehiclePolicyMapper() {
		super("vehiclePolicy");
	}
	
	public void batchInsert(List<VehiclePolicy> policies) {
		mongo.insertMany(collection, policies);
	}
	
	public Pager<VehiclePolicy> paging(VehiclePolicySearcher searcher) {
		List<VehiclePolicy> list = null;
		long total = 0;
		List<Bson> filters = new ArrayList<Bson>();
		if (null != searcher.getTid())
			filters.add(Filters.eq(FIELD_TID, searcher.getTid()));
		if (null != searcher.getUid())
			filters.add(Filters.eq(FIELD_UID, searcher.getUid()));
		if (null != searcher.getAppId())
			filters.add(Filters.eq(FIELD_APP_ID, searcher.getAppId()));
		if (null != searcher.getEmployeeId())
			filters.add(Filters.eq(FIELD_EMPLOYEE_ID, searcher.getEmployeeId()));
		if (null != searcher.getInsurerId())
			filters.add(Filters.eq(FIELD_INSURER_ID, searcher.getInsurerId()));
		if (null != searcher.getLicense())
			filters.add(Filters.eq(FIELD_LICENSE, searcher.getLicense()));
		if (null != searcher.getOwner())
			filters.add(Filters.eq(FIELD_OWNER, searcher.getOwner()));
		if (null != searcher.getSalesman())
			filters.add(Filters.eq(FIELD_SALESMAN, searcher.getSalesman()));
		if (null != searcher.getTransfer()) 
			filters.add(Filters.eq(FIELD_TRANSFER, searcher.getTransfer()));
		if (null != searcher.getNature()) 
			filters.add(Filters.eq(FIELD_NATURE, searcher.getNature()));
		if (null != searcher.getType()) 
			filters.add(Filters.eq(FIELD_TYPE, searcher.getType()));
		if (null != searcher.getBonusType()) 
			filters.add(Filters.eq(FIELD_BONUS_TYPE, searcher.getBonusType()));
		total = filters.isEmpty() ? mongo.count(collection) : mongo.count(collection, Filters.and(filters));
		if (0 == total)
			return Pager.EMPLTY;
		searcher.calculate((int) total);
		list = filters.isEmpty() ?
				mongo.pagingAndSort(collection, Sorts.descending(FIELD_CREATED), 
							searcher.getStart(), searcher.getPageSize(), VehiclePolicy.class):
				mongo.pagingAndSort(collection, Filters.and(filters), Sorts.descending(FIELD_CREATED),
							searcher.getStart(), searcher.getPageSize(), VehiclePolicy.class);
		return new Pager<VehiclePolicy>(searcher.getTotal(), list);
	}
	
	public List<VehiclePolicy> policies(int tid, int start, int end) {
		return mongo.find(collection, 
				Filters.and(Filters.eq(FIELD_TID, tid), Filters.gte(FIELD_ISSUE_TIME, start), Filters.lte(FIELD_ISSUE_TIME, start)),
				VehiclePolicy.class);
	}
}
