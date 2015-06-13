package com.fatuhiva.ext.web;

import javax.servlet.http.HttpServletRequest;

import com.fatuhiva.model.util.FatuPageManager;

public class JextWebUtil {

	public static String getRequestedPageClassName(HttpServletRequest request) {
		String pathInfo = request.getPathInfo();
		int lastSlash = pathInfo.lastIndexOf("/");
		String pageClassName = pathInfo.substring(lastSlash + 1);
		return pageClassName;
	}
	
	
	public static FatuPageManager getPageManager(HttpServletRequest request) {
		FatuPageManager pManager = (FatuPageManager) request.getSession().getAttribute(FatuPageManager.PAGE_MANAGER_KEY);
		if (pManager == null) {
			synchronized (FatuPageManager.PAGE_MANAGER_KEY) {
				pManager = (FatuPageManager) request.getSession().getAttribute(FatuPageManager.PAGE_MANAGER_KEY);
				if (pManager == null) {
					pManager = new FatuPageManager();
					request.getSession().setAttribute(FatuPageManager.PAGE_MANAGER_KEY, pManager);
				}
			}
		}
		return pManager;
	}

}
