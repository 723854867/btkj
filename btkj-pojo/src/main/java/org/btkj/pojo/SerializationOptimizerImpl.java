package org.btkj.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Insurance;
import org.btkj.pojo.entity.RegionCity;
import org.btkj.pojo.entity.RegionDistrict;
import org.btkj.pojo.entity.RegionProvince;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.entity.UserRelation;
import org.btkj.pojo.model.CaptchaReceiver;
import org.btkj.pojo.model.CaptchaVerifier;
import org.btkj.pojo.model.Identity;
import org.btkj.pojo.model.InviterModel;
import org.btkj.pojo.model.TenantJoinApplyType;
import org.btkj.pojo.model.UserLoginModel;
import org.btkj.pojo.model.Version;

import com.alibaba.dubbo.common.serialize.support.SerializationOptimizer;

@SuppressWarnings("rawtypes")
public class SerializationOptimizerImpl implements SerializationOptimizer {

	@Override
	public Collection<Class> getSerializableClasses() {
		List<Class> list = new ArrayList<Class>();
		list.add(App.class);
		list.add(Insurance.class);
		list.add(RegionCity.class);
		list.add(RegionDistrict.class);
		list.add(RegionProvince.class);
		list.add(Tenant.class);
		list.add(User.class);
		list.add(UserRelation.class);
		
		list.add(CaptchaReceiver.class);
		list.add(CaptchaVerifier.class);
		list.add(Identity.class);
		list.add(InviterModel.class);
		list.add(TenantJoinApplyType.class);
		list.add(UserLoginModel.class);
		list.add(Version.class);
		return list;
	}
}
