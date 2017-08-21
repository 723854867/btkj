package org.btkj.master;

import org.btkj.master.pojo.entity.Admin;
import org.btkj.master.pojo.entity.Admin.Mod;

public class MasterUtil {

	public static final boolean hasFullPrivileges(Admin admin) { 
		return (admin.getMod() & Mod.FULL_PRIVILEGES.mark()) == Mod.FULL_PRIVILEGES.mark();
	}
}
