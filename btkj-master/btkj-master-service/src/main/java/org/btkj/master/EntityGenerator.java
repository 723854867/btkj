package org.btkj.master;

import org.apache.commons.codec.digest.DigestUtils;
import org.btkj.master.pojo.entity.Administrator;
import org.rapid.util.lang.DateUtil;

public class EntityGenerator {

	public static final Administrator newAdministrator(String name, String pwd) {
		Administrator administrator = new Administrator();
		administrator.setPwd(DigestUtils.md5Hex(pwd));
		administrator.setName(name);
		
		int time = DateUtil.currentTime();
		administrator.setUpdated(time);
		administrator.setCreated(time);
		return administrator;
	}
}
