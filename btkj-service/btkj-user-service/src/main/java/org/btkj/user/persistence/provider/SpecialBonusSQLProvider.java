package org.btkj.user.persistence.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.BtkjTables;

public class SpecialBonusSQLProvider {

	public String insert() { 
		return new SQL() {
			{
				INSERT_INTO(BtkjTables.SPECIAL_BONUS.name());
				VALUES("eid", "#{eid}");
				VALUES("no_business_car", "#{noBusinessCar}");
				VALUES("no_business_truck", "#{noBusinessTruck}");
				VALUES("business_car", "#{businessCar}");
				VALUES("business_truck", "#{businessTruck}");
				VALUES("start_time", "#{startTime}");
				VALUES("`vci_ratio`", "#{vciRatio}");
				VALUES("`vci_type`", "#{vciType}");
				VALUES("`tci_ratio`", "#{tciRatio}");
				VALUES("`tci_type`", "#{tciType}");
				VALUES("created", "#{created}");
				VALUES("updated", "#{updated}");
			}
		}.toString();
	}
	
	public String selectByeid() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.SPECIAL_BONUS.name());
				WHERE("eid=#{eid}");
			}
		}.toString();
	}
	
	public String update() {
		return new SQL() {
		   {
				UPDATE(BtkjTables.SPECIAL_BONUS.name());
				SET("`no_business_car`=#{noBusinessCar}");
				SET("`no_business_truck`=#{noBusinessTruck}");
				SET("`business_car`=#{businessCar}");
				SET("`business_truck`=#{businessTruck}");
				SET("`start_time`=#{startTime}");
				SET("`vci_ratio`=#{vciRatio}");
				SET("`vci_type`=#{vciType}");
				SET("`tci_ratio`=#{tciRatio}");
				SET("`tci_type`=#{tciType}");
				SET("`updated`=#{updated}");
				WHERE("eid=#{eid}");
		   }
		}.toString();
	}
}
