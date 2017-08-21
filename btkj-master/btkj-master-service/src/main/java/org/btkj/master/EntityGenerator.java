package org.btkj.master;

import org.apache.commons.codec.digest.DigestUtils;
import org.btkj.master.pojo.entity.Admin;
import org.rapid.util.lang.DateUtil;

public class EntityGenerator {

	public static final Admin newAdministrator(String name, String pwd) {
		Admin temp = new Admin();
		temp.setPwd(DigestUtils.md5Hex(pwd));
		temp.setName(name);
		
		int time = DateUtil.currentTime();
		temp.setUpdated(time);
		temp.setCreated(time);
		return temp;
	}
}
