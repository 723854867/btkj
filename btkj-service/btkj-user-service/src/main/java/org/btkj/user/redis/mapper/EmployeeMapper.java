package org.btkj.user.redis.mapper;

import org.btkj.pojo.entity.Employee;
import org.btkj.user.persistence.dao.EmployeeDao;
import org.rapid.data.storage.mapper.O2OMapper;
import org.rapid.data.storage.redis.RedisTable;

public class EmployeeMapper extends O2OMapper<Integer, Employee, byte[], EmployeeDao> {

	protected EmployeeMapper(RedisTable<Integer, Employee> table, byte[] redisKey) {
		super(table, redisKey);
	}

}
