package org.btkj.user.persistence.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.BtkjTables;

public class SpecialCommissionSQLProvider {

	public String insert() { 
		return new SQL() {
			{
				INSERT_INTO(BtkjTables.SPECIAL_COMMISSION.name());
				VALUES("eid", "#{eid}");
				VALUES("no_business_car", "#{noBusinessCar}");
				VALUES("no_business_truck", "#{noBusinessTruck}");
				VALUES("business_car", "#{businessCar}");
				VALUES("business_truck", "#{businessTruck}");
				VALUES("take_effect_time", "#{takeEffectTime}");
				VALUES("`commercial_insurance_ratio`", "#{commercialInsuranceRatio}");
				VALUES("`commercial_insurance_type`", "#{commercialInsuranceType}");
				VALUES("`compulsory_insurance_ratio`", "#{compulsoryInsuranceRatio}");
				VALUES("`compulsory_insurance_type`", "#{compulsoryInsuranceType}");
				VALUES("created", "#{created}");
				VALUES("updated", "#{updated}");
			}
		}.toString();
	}
	
	public String selectByeid() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.SPECIAL_COMMISSION.name());
				WHERE("eid=#{eid}");
			}
		}.toString();
	}
	
	public String updateInfo() {
		return new SQL() {
		   {
				UPDATE(BtkjTables.SPECIAL_COMMISSION.name());
				SET("`no_business_car`=#{noBusinessCar}");
				SET("`no_business_truck`=#{noBusinessTruck}");
				SET("`business_car`=#{businessCar}");
				SET("`business_truck`=#{businessTruck}");
				SET("`take_effect_time`=#{takeEffectTime}");
				SET("`commercial_insurance_ratio`=#{commercialInsuranceRatio}");
				SET("`commercial_insurance_type`=#{commercialInsuranceType}");
				SET("`compulsory_insurance_ratio`=#{compulsoryInsuranceRatio}");
				SET("`compulsory_insurance_type`=#{compulsoryInsuranceType}");
				SET("`updated`=#{updated}");
				WHERE("eid=#{eid}");
		   }
		}.toString();
	}
}
