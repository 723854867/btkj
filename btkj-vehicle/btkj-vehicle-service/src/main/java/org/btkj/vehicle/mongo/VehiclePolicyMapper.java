package org.btkj.vehicle.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.conversions.Bson;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.model.Pager;
import org.btkj.vehicle.pojo.entity.VehiclePolicy;
import org.btkj.vehicle.pojo.param.VehiclePoliciesParam;
import org.rapid.data.storage.mapper.MongoMapper;
import org.springframework.stereotype.Component;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

@Component("vehiclePolicyMapper")
public class VehiclePolicyMapper extends MongoMapper<String, VehiclePolicy> {

	public VehiclePolicyMapper() {
		super("vehiclePolicy");
	}
	
	public void batchInsert(List<VehiclePolicy> policies) {
		mongo.insertMany(collection, policies);
	}
	
	public Pager<VehiclePolicy> policies(VehiclePoliciesParam param) {
		List<VehiclePolicy> list = null;
		long total = 0;
		List<Bson> filters = new ArrayList<Bson>();
		if (null != param.getTid())
			filters.add(Filters.eq(BtkjConsts.FIELD.TID, param.getTid()));
		if (null != param.getUid())
			filters.add(Filters.eq(BtkjConsts.FIELD.UID, param.getUid()));
		if (null != param.getAppId())
			filters.add(Filters.eq(BtkjConsts.FIELD.APPID, param.getAppId()));
		if (null != param.getTarId())
			filters.add(Filters.eq(BtkjConsts.FIELD.EMPLOYEEID, param.getTarId()));
		if (null != param.getInsurerId())
			filters.add(Filters.eq(BtkjConsts.FIELD.INSURERID, param.getInsurerId()));
		if (null != param.getLicense())
			filters.add(Filters.eq(BtkjConsts.FIELD.LICENSE, param.getLicense()));
		if (null != param.getOwner())
			filters.add(Filters.eq(BtkjConsts.FIELD.OWNER, param.getOwner()));
		if (null != param.getSalesman())
			filters.add(Filters.eq(BtkjConsts.FIELD.SALESMAN, param.getSalesman()));
		if (null != param.getTransfer()) 
			filters.add(Filters.eq(BtkjConsts.FIELD.TRANSFER, param.getTransfer()));
		if (null != param.getNature()) 
			filters.add(Filters.eq(BtkjConsts.FIELD.NATURE, param.getNature()));
		if (null != param.getType()) 
			filters.add(Filters.eq(BtkjConsts.FIELD.TYPE, param.getType()));
		if (null != param.getBonusType()) 
			filters.add(Filters.eq(BtkjConsts.FIELD.BONUSTYPE, param.getBonusType()));
		total = filters.isEmpty() ? mongo.count(collection) : mongo.count(collection, Filters.and(filters));
		if (0 == total)
			return Pager.EMPLTY;
		param.calculate((int) total);
		list = filters.isEmpty() ?
				mongo.pagingAndSort(collection, Sorts.descending(BtkjConsts.FIELD.CREATED), 
							param.getStart(), param.getPageSize(), VehiclePolicy.class):
				mongo.pagingAndSort(collection, Filters.and(filters), Sorts.descending(BtkjConsts.FIELD.CREATED),
							param.getStart(), param.getPageSize(), VehiclePolicy.class);
		return new Pager<VehiclePolicy>(total, list);
	}
	
	public List<VehiclePolicy> policies(int tid, int start, int end) {
		return mongo.find(collection, Filters.and(
						Filters.eq(BtkjConsts.FIELD.TID, tid), 
						Filters.gte(BtkjConsts.FIELD.ISSUETIME, start), 
						Filters.lte(BtkjConsts.FIELD.ISSUETIME, start)),
				VehiclePolicy.class);
	}
}
