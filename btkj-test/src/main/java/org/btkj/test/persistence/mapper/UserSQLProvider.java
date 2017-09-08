package org.btkj.test.persistence.mapper;

import java.util.Collection;
import java.util.Map;

import org.btkj.pojo.entity.user.UserPO;
import org.rapid.data.storage.mybatis.SQLProvider;

public class UserSQLProvider extends SQLProvider {

	public UserSQLProvider() {
		super("user", "uid");
	}
	
	public String batchInsert(Map<String, Object> params) {
		Collection<UserPO> list = (Collection<UserPO>) params.get(COLLECTION);
		StringBuilder builder = new StringBuilder("INSERT INTO `").append(table).append("`(`app_id`, `mobile`,")
				.append("`app_login_time`, `pc_login_time`, `created`, `updated`) VALUES");
		for (Object temp : list) {
			UserPO user = (UserPO) temp;
			builder.append("(").append(user.getAppId()).append(",'").append(user.getMobile()).append("',").append(user.getAppLoginTime())
			.append(",").append(user.getPcLoginTime()).append(",").append(user.getCreated()).append(",").append(user.getUpdated()).append("),");
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}
}
