package org.btkj.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.btkj.pojo.entity.config.Insurer;
import org.btkj.pojo.entity.config.Region;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.EmployeePO;
import org.btkj.pojo.entity.user.TenantPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.model.Version;

import com.alibaba.dubbo.common.serialize.support.SerializationOptimizer;

@SuppressWarnings("rawtypes")
public class SerializationOptimizerImpl implements SerializationOptimizer {

	@Override
	public Collection<Class> getSerializableClasses() {
		List<Class> list = new ArrayList<Class>();
		list.add(AppPO.class);
		list.add(Insurer.class);
		list.add(Region.class);
		list.add(TenantPO.class);
		list.add(UserPO.class);
		list.add(EmployeePO.class);
		
		list.add(Version.class);
		return list;
	}
}
