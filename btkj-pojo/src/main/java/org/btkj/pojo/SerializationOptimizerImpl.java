package org.btkj.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.btkj.pojo.entity.config.Insurer;
import org.btkj.pojo.entity.config.Region;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.model.Version;

import com.alibaba.dubbo.common.serialize.support.SerializationOptimizer;

@SuppressWarnings("rawtypes")
public class SerializationOptimizerImpl implements SerializationOptimizer {

	@Override
	public Collection<Class> getSerializableClasses() {
		List<Class> list = new ArrayList<Class>();
		list.add(App.class);
		list.add(Insurer.class);
		list.add(Region.class);
		list.add(Tenant.class);
		list.add(User.class);
		list.add(Employee.class);
		
		list.add(Version.class);
		return list;
	}
}
