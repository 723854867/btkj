package org.btkj.user.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.BtkjTables;

public class NonAutoBindSQLProvider {

	public String selectByTid() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.NON_AUTO_BIND.name());
				WHERE("tid=#{tid}");
			}
		}.toString();
	}
}
