package org.btkj.vehicle.mongo;

import org.bson.Document;
import org.btkj.pojo.entity.Renewal;
import org.rapid.data.storage.mapper.MongoMapper;

import com.mongodb.client.model.Filters;

public class RenewalMapper extends MongoMapper<Void, Renewal> {
	
	private final String FIELD_VIN					= "tips.vin";
	private final String FIELD_LICENSE				= "tips.license";
	
	public RenewalMapper() {
		super("renewal");
	}
	
	@Override
	public Renewal getByKey(Void key) {
		throw new UnsupportedOperationException("Renewal can not get by key!");
	}
	
	public Renewal getByVin(String vin) {
		Document document = mongo.findOne(collection, Filters.eq(FIELD_VIN, vin));
		return null == document ? null : deserial(document);
	}
	
	public Renewal getByLicense(String license) {
		Document document = mongo.findOne(collection, Filters.eq(FIELD_LICENSE, license));
		return null == document ? null : deserial(document);
	}
	
	/**
	 * 通过车牌和车架号获取唯一的续保信息
	 * 
	 * @param license
	 * @param vin
	 * @return
	 */
	public Renewal getByLicenseAndVin(String license, String vin) {
		Document document = mongo.findOne(collection, Filters.and(Filters.eq(FIELD_LICENSE, license), Filters.eq(FIELD_VIN, vin)));
		return null == document ? null : deserial(document);
	}
}
