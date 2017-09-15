package org.btkj.user.mybatis;

import org.btkj.pojo.config.GlobalConfigContainer;
import org.btkj.pojo.entity.config.Region;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Banner;
import org.btkj.pojo.entity.user.Customer;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Employee.Mod;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.info.ApplyInfo;
import org.btkj.pojo.info.EmployeeTip;
import org.btkj.pojo.param.user.BannerEditParam;
import org.btkj.pojo.param.user.CustomerEditParam;
import org.btkj.pojo.param.user.TenantAddParam;
import org.rapid.util.common.Consts;
import org.rapid.util.common.enums.REGION_TYPE;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.lang.StringUtil;
import org.rapid.util.math.tree.Node;

public class EntityGenerator {
	
	public static final ThreadLocal<EmployeeTip> EMPLOYEETIP_HOLDER	= new ThreadLocal<EmployeeTip>();

	
	public static final User newUser(int appId, String mobile) { 
		User user = new User();
		user.setAppId(appId);
		user.setMobile(mobile);
		
		int time = DateUtil.currentTime();
		user.setCreated(time);
		user.setUpdated(time);
		return user;
	} 
	
	public static final Employee newEmployee(int uid, Tenant tenant, Employee parent) {
		Employee employee = new Employee();
		employee.setUid(uid);
		employee.setTid(tenant.getTid());
		employee.setAppId(tenant.getAppId());
		employee.setParentId(null == parent ? 0 : parent.getId());
		employee.setLayer(null == parent ? Node.ROOT_LAYER : parent.getLayer() + 1);
		employee.setLeft(null == parent ? 1 : parent.getRight());
		employee.setRight(employee.getLeft() + 1);
		employee.setMod(Mod.PAY_FULL.mark());
		if (null == parent)
			employee.setRelationPath(StringUtil.EMPTY);
		else {
			String relationPath = parent.getRelationPath();
			if (relationPath.isEmpty())
				employee.setRelationPath(String.valueOf(parent.getId()));
			else
				employee.setRelationPath(relationPath + Consts.SYMBOL_UNDERLINE + parent.getId());
		}
		
		int time = DateUtil.currentTime();
		employee.setCreated(time);
		employee.setUpdated(time);
		return employee;
	}
	
	public static final App newApp(int region, String name, int maxTenantsCount, int maxArticlesCount) {
		App app = new App();
		app.setRegion(region);
		app.setName(name);
		app.setMaxTenantsCount(Math.max(0, maxTenantsCount));
		app.setMaxArticlesCount(Math.max(0, maxArticlesCount));

		int time = DateUtil.currentTime();
		app.setCreated(time);
		app.setUpdated(time);
		return app;
	}
	
	public static final Tenant newTenant(int appId, TenantAddParam param) {
		Tenant tenant = new Tenant();
		tenant.setName(param.getTname());
		tenant.setAppId(appId);
		tenant.setTeamDepth(GlobalConfigContainer.getGlobalConfig().getTeamDepth());
		tenant.setLicense(param.getLicense());
		tenant.setServicePhone(param.getServicePhone());
		tenant.setExpire(param.getExpire());
		tenant.setContacts(param.getContacts());
		tenant.setContactsMobile(param.getContactsMobile());
		tenant.setRegion(param.getRegion());
		
		int time = DateUtil.currentTime();
		tenant.setCreated(time);
		tenant.setUpdated(time);
		tenant.setJianJieFetchTime(time);
		return tenant;
	}
	
	public static final ApplyInfo newApply(User user, EmployeeTip chief) { 
		ApplyInfo ai = new ApplyInfo();
		ai.setTid(chief.getTid());
		ai.setUid(user.getUid());
		ai.setChief(chief.getId());
		ai.setChiefUid(chief.getUid());
		ai.setTime(DateUtil.currentTime());
		return ai;
	}
	
	public static final Banner newBanner(BannerEditParam param) {
		Banner banner = new Banner();
		banner.setAppId(param.getAppId());
		banner.setTid(param.getTid());
		banner.setIdx(param.getIdx());
		banner.setImage(param.getIcon());
		banner.setLink(param.getLink());
		
		int time = DateUtil.currentTime();
		banner.setCreated(time);
		banner.setUpdated(time);
		return banner;
	}
	
	public static final Customer newCustomer(CustomerEditParam param) {
		Customer customer = new Customer();
		customer.setUid(param.getUid());
		updateCustomer(customer, param);
		int time = DateUtil.currentTime();
		customer.setCreated(time);
		customer.setUpdated(time);
		return customer;
	}
	
	public static final void updateCustomer(Customer customer, CustomerEditParam param) {
		if (null != param.getName())
			customer.setName(param.getName());
		if (null != param.getAddress())
			customer.setAddress(param.getAddress());
		if (null != param.getIdentity())
			customer.setIdentity(param.getIdentity());
		if (null != param.getMemo())
			customer.setMemo(param.getMemo());
		if (null != param.getMobile())
			customer.setMobile(param.getMobile());
		if (null != param.getLicense())
			customer.setLicense(param.getLicense());
		if (null != param.getRegions()) {
			while (null != param.getRegions().peek()) {
				Region region = param.getRegions().poll();
				REGION_TYPE type = REGION_TYPE.match(region.getLevel());
				switch (type) {
				case PROVINCE:
					customer.setProvince(region.getName());
					break;
				case CITY:
					customer.setCity(region.getName());
					break;
				case COUNTY:
					customer.setCounty(region.getName());
					break;
				default:
					break;
				}
			}
		}
		customer.setUpdated(DateUtil.currentTime());
	}
}
