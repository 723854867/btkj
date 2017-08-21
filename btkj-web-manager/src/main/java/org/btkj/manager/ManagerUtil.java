package org.btkj.manager;

import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.entity.UserPO.Mod;

public class ManagerUtil {

	public static final boolean hasFullPrivileges(UserPO user) {
		return (user.getMod() & Mod.FULL_PRIVILEGES.mark()) == Mod.FULL_PRIVILEGES.mark();
	}
}
