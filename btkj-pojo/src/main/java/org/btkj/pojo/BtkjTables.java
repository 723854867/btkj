package org.btkj.pojo;

import org.rapid.data.storage.db.Table;

public interface BtkjTables {
	 
	// ******************************* config tables *******************************
	final Table REGION								= new Table("region");
	final Table INSURER								= new Table("insurer");

	// ******************************* user tables *******************************
	final Table APP									= new Table("app");
	final Table USER								= new Table("user");
	final Table BANNER								= new Table("banner");
	final Table TENANT								= new Table("tenant");
	final Table EMPLOYEE							= new Table("employee");
	final Table COMMUNITY							= new Table("community");
	final Table NON_AUTO_INSURANCE					= new Table("non_auto_insurance");
}
