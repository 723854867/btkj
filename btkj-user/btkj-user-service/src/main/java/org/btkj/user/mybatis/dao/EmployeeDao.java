package org.btkj.user.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.info.EmployeeListInfo;
import org.btkj.pojo.submit.EmployeeSearcher;
import org.btkj.user.mybatis.provider.EmployeeSQLProvider;
import org.rapid.data.storage.db.Dao;

public interface EmployeeDao extends Dao<Integer, Employee> {
	
	@Override
	@InsertProvider(type = EmployeeSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(Employee entity);
	
	@Override
	@SelectProvider(type = EmployeeSQLProvider.class, method = "selectByKey")
	Employee selectByKey(Integer key);
	
	@Override
	@UpdateProvider(type = EmployeeSQLProvider.class, method = "update")
	void update(Employee entity);
	
	@Override
	@SelectProvider(type = EmployeeSQLProvider.class, method = "selectAll")
	List<Employee> selectAll();
	
	@SelectProvider(type = EmployeeSQLProvider.class, method = "selectByTidAndUid")
	Employee selectByTidAndUid(@Param("tid") int tid, @Param("uid") int uid);
	
	@SelectProvider(type = EmployeeSQLProvider.class, method = "selectByUid")
	List<Employee> selectByUid(@Param("uid") int uid);
	
	@SelectProvider(type = EmployeeSQLProvider.class, method = "selectByTidForUpdate")
	List<Employee> selectByTidForUpdate(int tid);
	
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
	List<Employee> team(@Param("id") int id, @Param("left") int left, @Param("right") int right, @Param("level") int level);
	
	/**
	 * 新雇员加入代理公司，需要修改该代理公司一部分雇员的 left 和 right 值
	 * 
	 * @param tid
	 */
	@UpdateProvider(type = EmployeeSQLProvider.class, method = "updateForJoin")
	void updateForJoin(@Param("tid") int tid, @Param("value") int value);
	
	int searchCount(EmployeeSearcher searcher);
	
	List<EmployeeListInfo> search(EmployeeSearcher searcher);
}
