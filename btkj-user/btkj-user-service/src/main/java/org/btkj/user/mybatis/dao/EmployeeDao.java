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
import org.btkj.pojo.po.EmployeePO;
import org.btkj.user.mybatis.provider.EmployeeSQLProvider;
import org.btkj.user.pojo.info.EmployeePagingInfo;
import org.btkj.user.pojo.param.EmployeesParam;
import org.rapid.data.storage.mapper.DBMapper;

public interface EmployeeDao extends DBMapper<Integer, EmployeePO> {
	
	@Override
	@InsertProvider(type = EmployeeSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(EmployeePO entity);
	
	@Override
	@SelectProvider(type = EmployeeSQLProvider.class, method = "getByKey")
	EmployeePO getByKey(Integer key);
	
	@Override
	@MapKey("id")
	@SelectProvider(type = EmployeeSQLProvider.class, method = "getByKeys")
	Map<Integer, EmployeePO> getByKeys(Collection<Integer> keys);
	
	@SelectProvider(type = EmployeeSQLProvider.class, method = "getByTidAndUid")
	EmployeePO getByTidAndUid(@Param("tid") int tid, @Param("uid") int uid);
	
	@MapKey("id")
	@SelectProvider(type = EmployeeSQLProvider.class, method = "getByUid")
	Map<Integer, EmployeePO> getByUid(@Param("uid") int uid);
	
	@MapKey("id")
	@SelectProvider(type = EmployeeSQLProvider.class, method = "getByTidForUpdate")
	Map<Integer, EmployeePO> getByTidForUpdate(int tid);
	
	/**
	 * 团队：自己和 level - 自己的 level 范围内的成员
	 * 
	 * @param id
	 * @param left
	 * @param right
	 * @param level
	 * @return
	 */
	@SelectProvider(type = EmployeeSQLProvider.class, method = "team")
	List<EmployeePO> team(@Param("id") int id, @Param("left") int left, @Param("right") int right, @Param("level") int level);
	
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
	void update(EmployeePO entity);
}
