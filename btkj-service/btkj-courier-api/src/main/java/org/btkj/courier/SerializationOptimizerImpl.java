package org.btkj.courier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.btkj.courier.pojo.model.CaptchaReceiver;

import com.alibaba.dubbo.common.serialize.support.SerializationOptimizer;

@SuppressWarnings("rawtypes")
public class SerializationOptimizerImpl implements SerializationOptimizer {

	@Override
	public Collection<Class> getSerializableClasses() {
		List<Class> list = new ArrayList<Class>();
		list.add(CaptchaReceiver.class);
		return list;
	}
}
