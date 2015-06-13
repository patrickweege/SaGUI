package com.fatuhiva.ext.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fatuhiva.ext.common.render.IRenderManager;
import com.fatuhiva.ext.render.manager.WebRenderManager;
import com.fatuhiva.ext.web.JextWebUtil;
import com.fatuhiva.model.container.page.FatuPage;
import com.fatuhiva.model.util.FatuPageManager;
import com.pw.common.JextContext;

/**
 * Servlet implementation class PageServlet
 */
public class OpenServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public OpenServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

            JextContext.initialize();
            FatuPageManager pageManager = JextWebUtil.getPageManager(request);
			JextContext.setValue(FatuPageManager.PAGE_MANAGER_KEY, pageManager);
            JextContext.setValue(IRenderManager.RENDER_MANAGER_KEY, WebRenderManager.getInstance());

			String requestedClassName = JextWebUtil.getRequestedPageClassName(request);
			FatuPage<?> thePage = pageManager.openPage(requestedClassName);

			response.sendRedirect("../Render/teste?page="+thePage.getId());
					
		} finally {
			JextContext.release();
		}
	}


}
