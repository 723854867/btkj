package org.btkj.user.mybatis.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.info.user.EmployeePagingInfo;
import org.btkj.pojo.param.user.EmployeesParam;
import org.btkj.user.mybatis.provider.EmployeeSQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface EmployeeDao extends DBMapper<Integer, Employee> {
	
	@Override
	@InsertProvider(type = EmployeeSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(Employee entity);
	
	@Override
	@SelectProvider(type = EmployeeSQLProvider.class, method = "getByKey")
	Employee getByKey(Integer key);
	
	@Override
	@MapKey("id")
	@SelectProvider(type = EmployeeSQLProvider.class, method = "getByKeys")
	Map<Integer, Employee> getByKeys(Collection<Integer> keys);
	
	@SelectProvider(type = EmployeeSQLProvider.class, method = "getByTidAndUid")
	Employee getByTidAndUid(@Param("tid") int tid, @Param("uid") int uid);
	
	@MapKey("id")
	@SelectProvider(type = EmployeeSQLProvider.class, method = "getByUid")
	Map<Integer, Employee> getByUid(@Param("uid") int uid);
	
	@MapKey("id")
	@SelectProvider(type = EmployeeSQLProvider.class, method = "getByTidForUpdate")
	Map<Integer, Employee> getByTidForUpdate(int tid);
	
	/**
	 * 团队：自己和 layer - 自己的 layer 范围内的成员
	 * 
	 * @param id
	 * @param left
	 * @param right
	 * @param layer
	 * @return
	 */
	@SelectProvider(type = EmployeeSQLProvider.class, method = "team")
	List<Employee> team(@Param("id") int id, @Param("left") int left, @Param("right") int right, @Param("layer") int layer);
	
	/**
	 * 新雇员加入代理公司，需要修改该代理公司一部分雇员的 left 和 right 值
	 * 
	 * @param tid
	 */
	@UpdateProvider(type = EmployeeSQLProvider.class, method = "updateForJoin")
	void updateForJoin(@Param("tid") int tid, @Param("value") int value);
	
	@SelectProvider(type = EmployeeSQLProvider.class, method = "count")
	int count(EmployeesParam param);
	
	@SelectProvider(type = EmployeeSQLProvider.class, method = "employees")
	List<EmployeePagingInfo> employees(EmployeesParam param);
	
	@Override
	@UpdateProvider(type = EmployeeSQLProvider.class, method = "update")
	void update(Employee entity);
}
