package org.btkj.pojo;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Banner;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Insurer;
import org.btkj.pojo.entity.NonAutoInsurance;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.rapid.data.storage.redis.RedisTable;

public interface BtkjTables {
	 
	// ******************************* config tables *******************************
	final RedisTable<Integer, Region> REGION								= new RedisTable<Integer, Region>("region", Region.class);
	final RedisTable<Integer, Insurer> INSURER								= new RedisTable<Integer, Insurer>("insurer", Insurer.class);
	final RedisTable<Integer, NonAutoInsurance> NON_AUTO_INSURANCE			= new RedisTable<Integer, NonAutoInsurance>("non_auto_insurance", NonAutoInsurance.class);

	// ******************************* user tables *******************************
	final RedisTable<Integer, App> APP										= new RedisTable<Integer, App>("app", App.class);
	final RedisTable<Integer, User> USER									= new RedisTable<Integer, User>("user", User.class);
	final RedisTable<Integer, Banner> BANNER								= new RedisTable<Integer, Banner>("banner", Banner.class);
	final RedisTable<Integer, Tenant> TENANT								= new RedisTable<Integer, Tenant>("tenant", Tenant.class);
	final RedisTable<Integer, Employee> EMPLOYEE							= new RedisTable<Integer, Employee>("employee", Employee.class);
}
