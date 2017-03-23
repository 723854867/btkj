package org.btkj.user.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.pojo.entity.Employee;
import org.btkj.user.persistence.provider.EmployeeSQLProvider;
import org.rapid.data.storage.db.Dao;

public interface EmployeeDao extends Dao<Integer, Employee> {

	@Override
	@InsertProvider(type = EmployeeSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(Employee entity);
	
	@Override
	@SelectProvider(type = EmployeeSQLProvider.class, method = "selectAll")
	List<Employee> selectAll();
	
	@SelectProvider(type = EmployeeSQLProvider.class, method = "selectByUidAndTid")
	Employee selectByUidAndTid(@Param("uid") int uid, @Param("tid") int tid);
	
	@SelectProvider(type = EmployeeSQLProvider.class, method = "selectByUidAndAppId")
	List<Employee> selectByUidAndAppId(@Param("uid") int uid, @Param("appId") int appId);
	
	@SelectProvider(type = EmployeeSQLProvider.class, method = "selectByAppIdAndMobile")
	Employee selectByAppIdAndMobile(@Param("appId") int appId, @Param("mobile") String mobile);
}
