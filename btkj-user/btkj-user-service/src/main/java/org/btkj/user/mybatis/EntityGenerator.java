package org.btkj.user.mybatis;

import java.util.LinkedList;

import org.btkj.pojo.config.GlobalConfigContainer;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.Banner;
import org.btkj.pojo.entity.Customer;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.EmployeePO.Mod;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.info.ApplyInfo;
import org.btkj.pojo.info.EmployeeTip;
import org.btkj.pojo.model.identity.Employee;
import org.btkj.pojo.model.identity.User;
import org.btkj.user.pojo.param.BannerEditParam;
import org.btkj.user.pojo.param.TenantAddParam;
import org.rapid.util.common.Consts;
import org.rapid.util.common.enums.REGION_TYPE;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.lang.StringUtil;
import org.rapid.util.math.tree.Node;

public class EntityGenerator {
	
	public static final ThreadLocal<Employee> EMPLOYEE_HOLDER	= new ThreadLocal<Employee>();
	public static final ThreadLocal<EmployeeTip> EMPLOYEETIP_HOLDER	= new ThreadLocal<EmployeeTip>();

	
	public static final UserPO newUser(int appId, String mobile) { 
		UserPO user = new UserPO();
		user.setAppId(appId);
		user.setMobile(mobile);
		
		int time = DateUtil.currentTime();
		user.setCreated(time);
		user.setUpdated(time);
		return user;
	} 
	
	public static final EmployeePO newEmployee(int uid, TenantPO tenant, EmployeePO parent) {
		EmployeePO employee = new EmployeePO();
		employee.setUid(uid);
		employee.setTid(tenant.getTid());
		employee.setAppId(tenant.getAppId());
		employee.setParentId(null == parent ? 0 : parent.getId());
		employee.setLevel(null == parent ? Node.ROOT_LAYER : parent.getLevel() + 1);
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
	
	public static final AppPO newApp(int region, String name, int maxTenantsCount, int maxArticlesCount) {
		AppPO app = new AppPO();
		app.setRegion(region);
		app.setName(name);
		app.setMaxTenantsCount(Math.max(0, maxTenantsCount));
		app.setMaxArticlesCount(Math.max(0, maxArticlesCount));

		int time = DateUtil.currentTime();
		app.setCreated(time);
		app.setUpdated(time);
		return app;
	}
	
	public static final TenantPO newTenant(int appId, TenantAddParam param) {
		TenantPO tenant = new TenantPO();
		tenant.setName(param.getTname());
		tenant.setAppId(appId);
		tenant.setTeamDepth(GlobalConfigContainer.getGlobalConfig().getTeamDepth());
		tenant.setLicense(param.getLicense());
		tenant.setLicenseImage(param.getLicenseImage());
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
	
	public static final ApplyInfo newApply(TenantPO tenant, User user, Employee chief) { 
		ApplyInfo ai = new ApplyInfo();
		ai.setTid(tenant.getTid());
		ai.setUid(user.getUid());
		ai.setChief(chief.getId());
		ai.setChiefUid(chief.getUser().getUid());
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
	
	public static final Customer newCustomer(int uid, String name, String identity, String mobile, String license, LinkedList<Region> regions, String address, String memo) {
		Customer customer = new Customer();
		customer.setUid(uid);
		updateCustomer(customer, name, identity, mobile, license, regions, address, memo);
		int time = DateUtil.currentTime();
		customer.setCreated(time);
		customer.setUpdated(time);
		return customer;
	}
	
	public static final void updateCustomer(Customer customer, String name, String identity, String mobile, String license, LinkedList<Region> regions, String address, String memo) {
		if (null != name)
			customer.setName(name);
		if (null != address)
			customer.setAddress(address);
		if (null != identity)
			customer.setIdentity(identity);
		if (null != memo)
			customer.setMemo(memo);
		if (null != mobile)
			customer.setMobile(mobile);
		if (null != license)
			customer.setLicense(license);
		if (null != regions) {
			while (null != regions.peek()) {
				Region region = regions.poll();
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
