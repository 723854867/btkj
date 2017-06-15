package org.btkj.statistics.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.statistics.model.TeamEmployee;
import org.btkj.statistics.mybatis.entity.Performance;
import org.btkj.statistics.mybatis.provider.PerformanceSQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface PerformanceDao extends DBMapper<Long, Performance> {
	
	/**
	 * 团队列表
	 * 
	 * @param employeeIds
	 * @param begin	开始时间
	 * @param end 结束时间
	 * @param categoryMod 种类模值：可以同时查看多个种类的业绩量
	 * @return
	 */
	@SelectProvider(type = PerformanceSQLProvider.class, method = "teamEmployees")
	List<TeamEmployee> teamEmployees(@Param("list") List<Integer> employeeIds, 
			@Param("begin") int begin, @Param("end") int end, @Param("categoryMod") int categoryMod);
	
	/**
	 * 团队总业绩
	 * 
	 * @param employeeIds
	 * @param begin
	 * @param end
	 * @param categoryMod
	 * @return
	 */
	@SelectProvider(type = PerformanceSQLProvider.class, method = "total")
	double total(@Param("list") List<Integer> employeeIds, 
			@Param("begin") int begin, @Param("end") int end, @Param("categoryMod") int categoryMod);
}
