package com.sagui.ext.web.servlet;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pw.common.JextContext;
import com.sagui.ext.common.render.IRender;
import com.sagui.ext.common.render.IRenderManager;
import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.ext.render.manager.WebRenderManager;
import com.sagui.ext.web.JextWebUtil;
import com.sagui.model.container.page.FatuPage;
import com.sagui.model.util.FatuPageManager;

/**
 * Servlet implementation class PageServlet
 */
public class PageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public PageServlet() {
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

            renderHtmlPage(thePage, request, response);

        } finally {
            JextContext.release();
        }
    }

    @SuppressWarnings("rawtypes")
    private void renderHtmlPage(FatuPage<?> page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringWriter writer = new StringWriter();

        IRenderManager renderManager = JextContext.getValue(IRenderManager.RENDER_MANAGER_KEY);

        IRender<FatuPage> render = renderManager.getRender(FatuPage.class);
        render.render(page, new RenderWriter(writer, 0));

        Writer w = response.getWriter();

        w.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
        w.write("<html>\n");
        w.write("<head>\n");
        w.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\n");
        w.write("<script type='text/javascript' src='../resource/ext-4.1.1a-gpl/ext-all-debug.js'></script>\n");

        w.write("<script type='text/javascript' src='../resource/javascript/fatuhiva/commons.js'></script>\n");
        w.write("<script type='text/javascript' src='../resource/javascript/fatuhiva/Fatuhiva.js'></script>\n");
        w.write("<script type='text/javascript' src='../resource/javascript/fatuhiva/FatuhivaComboBoxSelectionListener.js'></script>\n");
        w.write("<script type='text/javascript' src='../resource/javascript/fatuhiva/FatuhivaTextBoxOnBlurListener.js'></script>\n");
        w.write("<script type='text/javascript' src='../resource/javascript/fatuhiva/FatuhivaButtonClickListener.js'></script>\n");
        w.write("<script type='text/javascript' src='../resource/javascript/fatuhiva/FatuhivaGridStoreOnLoadListener.js'></script>\n");
        w.write("<script type='text/javascript' src='../resource/javascript/fatuhiva/FatuhivaCheckboxSelectionModel.js'></script>\n");
        w.write("<script type='text/javascript' src='../resource/javascript/fatuhiva/action/FatuhivaBaseAction.js'></script>\n");
        w.write("<script type='text/javascript' src='../resource/javascript/fatuhiva/action/FatuhivaRefreshAction.js'></script>\n");
        
        w.write("<link rel='stylesheet' href='../resource/ext-4.1.1a-gpl/resources/css/ext-all.css' type='text/css' />\n");

        w.write("<title id='the-title'>Page Template</title>\n");
        w.write("</head>\n");
        w.write("<body id='ext-content'>\n");

        w.write("<script type='text/javascript'>\n");
        w.write("Ext.require(['Ext.*']);\n");
        w.write("Ext.onReady(function(){\n");
        w.write("Ext.QuickTips.init();\n");

        w.write(writer.getBuffer().toString());

        w.write("});\n");
        w.write("</script>\n");

        w.write("</body>\n");
        w.write("</html>\n");

        w.flush();

    }

}
