package org.btkj.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.btkj.user.pojo.entity.App;
import org.btkj.user.pojo.entity.User;

import com.alibaba.dubbo.common.serialize.support.SerializationOptimizer;

@SuppressWarnings("rawtypes")
public class SerializationOptimizerImpl implements SerializationOptimizer {

	@Override
	public Collection<Class> getSerializableClasses() {
		List<Class> list = new ArrayList<Class>();
		list.add(User.class);
		list.add(App.class);
		return list;
	}
}
