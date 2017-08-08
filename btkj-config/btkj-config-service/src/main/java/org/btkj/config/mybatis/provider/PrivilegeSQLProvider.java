package org.btkj.config.mybatis.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.config.pojo.entity.Privilege;
import org.rapid.data.storage.mybatis.SQLProvider;

public class PrivilegeSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "privilege";
	
	public PrivilegeSQLProvider() {
		super(TABLE, "id", false);
	}
	
	public String replace (Map<String, Privilege> privileges) {
		StringBuilder builder = new StringBuilder("REPLACE INTO `privilege`(`id`, `tarId`, `tarType`, `modularId`, `created`) VALUES");
		for (Privilege privilege : privileges.values()) {
			builder.append("('").append(privilege.getId()).append("', ").append(privilege.getTarId()).append(", ")
					.append(privilege.getTarType()).append(", ").append(privilege.getModularId()).append(", ")
					.append(privilege.getCreated()).append("),");
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}

	public String getByTarTypeAndTarId() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("tar_type=#{tarType}");
				AND();
				WHERE("tar_id=#{tarId}");
			}
		}.toString();
	}
	
	public String deleteByTarTypeAndTarId() {
		return new SQL() {
			{
				DELETE_FROM(table);
				WHERE("tar_type=#{tarType}");
				AND();
				WHERE("tar_id=#{tarId}");
			}
		}.toString();
	}
}
