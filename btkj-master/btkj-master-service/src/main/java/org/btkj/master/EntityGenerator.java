package org.btkj.master;

import org.btkj.pojo.po.Administrator;
import org.rapid.util.lang.DateUtil;

public class EntityGenerator {

	public static final Administrator newAdministrator(String name, String pwd) {
		Administrator administrator = new Administrator();
		administrator.setPwd(pwd);
		administrator.setName(name);
		
		int time = DateUtil.currentTime();
		administrator.setUpdated(time);
		administrator.setCreated(time);
		return administrator;
	}
}
