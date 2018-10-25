package com.sagui.ext.web.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

import com.pw.common.JextContext;
import com.sagui.commons.util.Resources;
import com.sagui.dataset.commons.log.FatuLoggerFactory;
import com.sagui.ext.common.render.IRender;
import com.sagui.ext.common.render.IRenderManager;
import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.ext.render.manager.WebRenderManager;
import com.sagui.ext.web.JextWebUtil;
import com.sagui.model.container.page.FatuPage;
import com.sagui.model.util.FatuPageManager;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class PageServlet
 */
public class RenderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger log = FatuLoggerFactory.create();

    public RenderServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            JextContext.initialize();
            JextContext.setValue("FATUHIVA.ENCODING", "UTF-8");
            String pageID = request.getParameter("page");
            FatuPageManager pageManager = JextWebUtil.getPageManager(request);
            JextContext.setValue(IRenderManager.RENDER_MANAGER_KEY, WebRenderManager.getInstance());

            FatuPage<?> thePage = pageManager.getPageByID(pageID);
            JextContext.setValue("CURRENT_PAGE", thePage);

            log.debug("Start Render...");
            renderHtmlPage(thePage, request, response);
            log.debug("End Render...");

        } catch (Exception e) {
            throw new ServletException(e);
        } finally {
            JextContext.release();
        }
    }

    @SuppressWarnings("rawtypes")
    private void renderHtmlPage(FatuPage<?> page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, TemplateException {
        String encoding = JextContext.getValue("FATUHIVA.ENCODING");
        response.setContentType("text/html");
        response.setCharacterEncoding(encoding);

        StringWriter writer = new StringWriter();
        IRenderManager renderManager = JextContext.getValue(IRenderManager.RENDER_MANAGER_KEY);
        IRender<FatuPage> render = renderManager.getRender(FatuPage.class);
        render.render(page, new RenderWriter(writer, 0));

        //String EXT_VERSION = "ext-4.2.1.883";
        //String EXT_VERSION = "ext-5.0.1-cdn";
        //String EXT_VERSION = "ext-5.0.1";
        String EXT_VERSION = "6.6.0";
        //String THEME = "classic";
        String THEME = "neptune";
        

        //Resources templateResource = new Resources(EXT_VERSION + ".ftl");
        Resources templateResource = new Resources("ext-" + EXT_VERSION + ".ftl");
        Template theTemplate = new Template("Page", new InputStreamReader(new ByteArrayInputStream(templateResource.asBinary())), new Configuration());

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("EXT_VERSION", EXT_VERSION);
        variables.put("THEME", THEME);
        variables.put("ENCODING", encoding);
        variables.put("EXT_SCRIPT", writer.getBuffer().toString());

        Writer w = response.getWriter();
        theTemplate.process(variables, w);

        //        w.write("<!DOCTYPE HTML>\n");
        //        w.write("<html>\n");
        //        w.write("<head>\n");
        //
        //        w.write(String.format("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=%1$s\">\n", encoding));
        //
        //        w.write("<link rel='stylesheet' href='../resource/" + extVersion + "/build/packages/ext-theme-" + theme + "/build/resources/ext-theme-" + theme + "-all.css' type='text/css' />\n");
        //        w.write("<script type='text/javascript' src='../resource/" + extVersion + "/build/ext-all-debug.js'></script>\n");
        //        w.write("<script type='text/javascript' src='../resource/" + extVersion + "/build/packages/ext-theme-" + theme + "/build/ext-theme-" + theme + ".js'></script>\n");
        //
        //        // Own Resources 
        //        w.write("<script type='text/javascript' src='../resource/javascript/fatuhiva/commons.js'></script>\n");
        //        w.write("<script type='text/javascript' src='../resource/javascript/fatuhiva/Fatuhiva.js'></script>\n");
        //        w.write("<script type='text/javascript' src='../resource/javascript/fatuhiva/FatuhivaComboBoxSelectionListener.js'></script>\n");
        //        w.write("<script type='text/javascript' src='../resource/javascript/fatuhiva/FatuhivaTextBoxOnBlurListener.js'></script>\n");
        //        w.write("<script type='text/javascript' src='../resource/javascript/fatuhiva/FatuhivaButtonClickListener.js'></script>\n");
        //
        //        w.write("<script type='text/javascript' src='../resource/javascript/fatuhiva/action/FatuhivaBaseAction.js'></script>\n");
        //        w.write("<script type='text/javascript' src='../resource/javascript/fatuhiva/action/FatuhivaRefreshAction.js'></script>\n");
        //        w.write("<script type='text/javascript' src='../resource/javascript/fatuhiva/action/FatuhivaRequestAction.js'></script>\n");
        //
        //        // For Fatuhiva Grid
        //        w.write("<script type='text/javascript' src='../resource/javascript/fatuhiva/gridpanel/FatuhivaDataStoreListeners.js'></script>\n");
        //        w.write("<script type='text/javascript' src='../resource/javascript/fatuhiva/gridpanel/FatuhivaSelectionModelListeners.js'></script>\n");
        //        w.write("<script type='text/javascript' src='../resource/javascript/fatuhiva/gridpanel/FatuhivaCheckboxSelectionModel.js'></script>\n");
        //        w.write("<script type='text/javascript' src='../resource/javascript/fatuhiva/gridpanel/FatuhivaGridPanel.js'></script>\n");
        //        w.write("<script type='text/javascript' src='../resource/javascript/fatuhiva/gridpanel/FatuhivaDataStore.js'></script>\n");
        //
        //        w.write("<title id='the-title'>Page Template</title>\n");
        //        w.write("</head>\n");
        //        w.write("<body id='ext-content'>\n");
        //
        //        w.write("<script type='text/javascript'>\n");
        //        w.write("Ext.require(['Ext.*']);\n");
        //        w.write("Ext.onReady(function(){\n");
        //        w.write("Ext.QuickTips.init();\n");
        //
        //        w.write(writer.getBuffer().toString());
        //
        //        w.write("});\n");
        //        w.write("</script>\n");
        //
        //        w.write("</body>\n");
        //        w.write("</html>\n");

        w.flush();

        //log.warn(writer.getBuffer().toString());
    }

}
