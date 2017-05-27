package org.btkj.vehicle.mybatis;

import org.rapid.data.storage.db.Table;

public interface Tables {

	final Table ROUTE						= new Table("route");
	final Table CITY_RULE					= new Table("city_rule");
	final Table VEHICLE_CONFIG				= new Table("vehicle_config");
}
