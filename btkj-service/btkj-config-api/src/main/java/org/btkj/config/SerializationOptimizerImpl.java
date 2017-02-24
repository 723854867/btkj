package org.btkj.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.btkj.config.pojo.entity.App;
import org.btkj.config.pojo.entity.Insurance;
import org.btkj.config.pojo.entity.RegionCity;
import org.btkj.config.pojo.entity.RegionDistrict;
import org.btkj.config.pojo.entity.RegionProvince;
import org.rapid.util.common.message.Result;

import com.alibaba.dubbo.common.serialize.support.SerializationOptimizer;

@SuppressWarnings("rawtypes")
public class SerializationOptimizerImpl implements SerializationOptimizer {

	@Override
	public Collection<Class> getSerializableClasses() {
		List<Class> list = new ArrayList<Class>();
		list.add(Result.class);
		list.add(App.class);
		list.add(Insurance.class);
		list.add(RegionCity.class);
		list.add(RegionDistrict.class);
		list.add(RegionProvince.class);
		return list;
	}
}
