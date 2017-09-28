package org.btkj.statistics.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.pojo.entity.statistics.VehicleStatisticAct;
import org.btkj.pojo.param.statistics.Report3Param;
import org.btkj.statistics.mybatis.provider.VehicleStatisticActSQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface VehicleStatisticActDao extends DBMapper<Long, VehicleStatisticAct> {

	@Override
	@InsertProvider(type = VehicleStatisticActSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	void insert(VehicleStatisticAct model);
	
	@SelectProvider(type = VehicleStatisticActSQLProvider.class, method = "report_3")
	List<VehicleStatisticAct> report_3(Report3Param param);

	@SelectProvider(type = VehicleStatisticActSQLProvider.class, method = "orderNum")
	int orderNum(@Param("employeeId") int employeeId, @Param("beginTime") int beginTime, @Param("endTime") int endTime);
}
