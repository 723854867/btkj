package org.btkj.common.web.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Tenant;
import org.btkj.web.util.session.HttpSession;

public class UserSession extends HttpSession {
	
	private App app;				// 所属 app
	private Tenant tenant;			// 所属 租户

	public UserSession(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
}
