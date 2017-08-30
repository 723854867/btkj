package org.btkj.vehicle.mongo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.conversions.Bson;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.enums.InsuranceType;
import org.btkj.pojo.model.Pager;
import org.btkj.vehicle.pojo.entity.VehiclePolicy;
import org.btkj.vehicle.pojo.param.VehiclePoliciesParam;
import org.rapid.data.storage.mapper.MongoMapper;
import org.rapid.util.lang.CollectionUtil;
import org.springframework.stereotype.Component;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

@Component("vehiclePolicyMapper")
public class VehiclePolicyMapper extends MongoMapper<String, VehiclePolicy> {
	
	private static final String FIELD_COMPULSORY_NO		= "compulsoryDetail.no";
	private static final String FIELD_COMMERCIAL_NO		= "commercialDetail.no";

	public VehiclePolicyMapper() {
		super("vehiclePolicy");
	}
	
	public void batchInsert(Map<String, VehiclePolicy> policies) {
		if (CollectionUtil.isEmpty(policies))
			return;
		mongo.bulkReplaceOne(collection, policies);
	}
	
	public VehiclePolicy getByNo(InsuranceType type, String no) {
		String field = type == InsuranceType.COMMERCIAL ? FIELD_COMMERCIAL_NO : FIELD_COMPULSORY_NO;
		return mongo.findOne(collection, Filters.eq(field, no), VehiclePolicy.class);
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
	
	public Map<String, VehiclePolicy> policies(int tid, int start, int end) {
		return mongo.findMap(collection, Filters.and(
						Filters.eq(BtkjConsts.FIELD.TID, tid), 
						Filters.gte(BtkjConsts.FIELD.ISSUETIME, start), 
						Filters.lte(BtkjConsts.FIELD.ISSUETIME, start)),
				VehiclePolicy.class);
	}
}
