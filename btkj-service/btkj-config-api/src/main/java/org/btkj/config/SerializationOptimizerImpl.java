package org.btkj.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.alibaba.dubbo.common.serialize.support.SerializationOptimizer;

@SuppressWarnings("rawtypes")
public class SerializationOptimizerImpl implements SerializationOptimizer {

	@Override
	public Collection<Class> getSerializableClasses() {
		List<Class> list = new ArrayList<Class>();
		return list;
	}
}
