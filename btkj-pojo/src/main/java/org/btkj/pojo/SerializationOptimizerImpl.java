package org.btkj.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.Insurer;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.model.BtkjUserLoginModel;
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
		
		list.add(BtkjUserLoginModel.class);
		list.add(Version.class);
		return list;
	}
}
