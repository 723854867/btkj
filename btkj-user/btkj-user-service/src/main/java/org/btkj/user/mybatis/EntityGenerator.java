package org.btkj.user.mybatis;

import java.util.LinkedList;

import org.btkj.pojo.bo.EmployeeForm;
import org.btkj.pojo.config.GlobalConfigContainer;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.Banner;
import org.btkj.pojo.po.Customer;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.Region;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.pojo.vo.ApplyInfo;
import org.rapid.util.common.enums.REGION_TYPE;
import org.rapid.util.lang.DateUtil;

public class EntityGenerator {
	
	public static final UserPO newUser(int appId, String mobile) { 
		UserPO user = new UserPO();
		user.setAppId(appId);
		user.setMobile(mobile);
		
		int time = DateUtil.currentTime();
		user.setCreated(time);
		user.setUpdated(time);
		return user;
	} 
	
	public static final EmployeePO newEmployee(UserPO user, TenantPO tenant, EmployeePO parent) {
		EmployeePO employee = new EmployeePO();
		employee.setUid(user.getUid());
		employee.setTid(tenant.getTid());
		employee.setParentId(null == parent ? 0 : parent.getId());
		employee.setLevel(null == parent ? 1 : parent.getLevel() + 1);
		employee.setLeft(null == parent ? 1 : parent.getRight());
		employee.setRight(employee.getLeft() + 1);
		
		int time = DateUtil.currentTime();
		employee.setCreated(time);
		employee.setUpdated(time);
		return employee;
	}
	
	public static final AppPO newApp(int region, String name, int maxTenantsCount, int maxArticlesCount) {
		AppPO app = new AppPO();
		app.setRegion(region);
		app.setName(name);
		app.setMaxTenantsCount(maxTenantsCount);
		app.setMaxArticlesCount(maxArticlesCount);

		int time = DateUtil.currentTime();
		app.setCreated(time);
		app.setUpdated(time);
		return app;
	}
	
	public static final TenantPO newTenant(int region, int appId, String name, String licenseFace, String licenseBack, String servicePhone) {
		TenantPO tenant = new TenantPO();
		tenant.setName(name);
		tenant.setAppId(appId);
		tenant.setRegion(region);
		tenant.setTeamDepth(GlobalConfigContainer.getGlobalConfig().getTeamDepth());
		tenant.setLicenseFace(licenseFace);
		tenant.setLicenseBack(licenseBack);
		tenant.setServicePhone(servicePhone);
		
		int time = DateUtil.currentTime();
		tenant.setCreated(time);
		tenant.setUpdated(time);
		tenant.setJianJieFetchTime(time);
		return tenant;
	}
	
	public static final ApplyInfo newApply(TenantPO tenant, UserPO user, EmployeeForm chief) { 
		ApplyInfo ai = new ApplyInfo();
		ai.setTid(tenant.getTid());
		ai.setUid(user.getUid());
		ai.setChief(chief.getEmployee().getId());
		ai.setChiefUid(chief.getUser().getUid());
		ai.setTime(DateUtil.currentTime());
		return ai;
	}
	
	public static final Banner newBanner(int appId, int tid, int idx, String icon, String link) {
		Banner banner = new Banner();
		banner.setAppId(appId);
		banner.setTid(tid);
		banner.setId(idx);
		banner.setImage(icon);
		banner.setLink(link);
		
		int time = DateUtil.currentTime();
		banner.setCreated(time);
		banner.setUpdated(time);
		return banner;
	}
	
	public static final Customer newCustomer(int uid, String name, String identity, String mobile, String license, LinkedList<Region> regions, String address, String memo) {
		Customer customer = new Customer();
		customer.setUid(uid);
		customer.setName(name);
		customer.setAddress(address);
		customer.setIdentity(identity);
		customer.setMemo(memo);
		customer.setMobile(mobile);
		customer.setLicense(license);
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
		return customer;
	}
}
