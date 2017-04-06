package org.btkj.master.action;

import org.btkj.master.Beans;
import org.btkj.pojo.model.Version;
import org.btkj.web.util.action.Action;

public abstract class MasterAction implements Action, Beans {

	@Override
	public Version version() {
		return Version.V_1_0;
	}
}
