package org.btkj.manager;

import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.entity.UserPO.Mod;
import org.rapid.util.math.tree.Node;

public class ManagerUtil {

	public static final boolean hasFullPrivileges(UserPO user) {
		return (user.getMod() & Mod.FULL_PRIVILEGES.mark()) == Mod.FULL_PRIVILEGES.mark();
	}
	
	public static final boolean hasFullPrivileges(EmployeePO employee) {
		return employee.getLevel() == Node.ROOT_LAYER;
	}
}
