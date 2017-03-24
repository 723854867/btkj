package org.btkj.manager.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.btkj.web.util.request.HttpRequest;

public class Request extends HttpRequest {

	public Request(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
}
