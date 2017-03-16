package org.btkj.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Tenant;
import org.btkj.web.util.request.HttpRequest;

public class Request extends HttpRequest {

	private App app;				// 所属 app
	private Tenant tenant;			// 所属 租户

	public Request(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
}
