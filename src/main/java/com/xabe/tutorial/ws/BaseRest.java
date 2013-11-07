package com.xabe.tutorial.ws;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseRest {
	protected Logger logger = LoggerFactory.getLogger(getClass().getName());
	public static final String GET_IP_REMOTE = "X-FORWARDED-FOR";

	@Context
	protected HttpServletRequest httpServletRequest;

	@Context
	protected UriInfo uriInfo;

	@Context
	protected ResourceContext rc;

	public HttpServletRequest getHttpServletRequest() {
		return httpServletRequest;
	}

	public URI getAbsolutePath() {
		return uriInfo.getAbsolutePath();
	}

	public String getIp() {
		HttpServletRequest request = getHttpServletRequest();
		String sIP = request.getHeader(GET_IP_REMOTE);
		if (sIP == null || sIP.length() == 0) {
			sIP = request.getRemoteAddr();
		}
		return sIP;
	}
}
