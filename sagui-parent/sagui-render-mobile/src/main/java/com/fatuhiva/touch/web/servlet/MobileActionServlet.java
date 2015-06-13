package com.fatuhiva.touch.web.servlet;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

import com.fatuhiva.ext.common.render.ChangesManager;
import com.fatuhiva.ext.common.render.ChangesManager.ComponentChange;
import com.fatuhiva.ext.common.render.IRender;
import com.fatuhiva.ext.common.render.IRenderManager;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.gui.aspects.FatuPropertyChangeListener;
import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.container.page.FatuPage;
import com.fatuhiva.model.util.FatuPageManager;
import com.fatuhiva.model.util.FatuUtil;
import com.fatuhiva.touch.controller.ControllerManager;
import com.fatuhiva.touch.controller.IJextAction;
import com.fatuhiva.touch.render.container.page.JextPageRender;
import com.fatuhiva.touch.render.listener.WebRenderPropertyChangedListener;
import com.fatuhiva.touch.render.manager.MobileRenderManager;
import com.fatuhiva.touch.web.JextWebUtil;
import com.pw.common.JextContext;
import com.tuamotu.commons.log.FatuLoggerFactory;

public class MobileActionServlet extends HttpServlet {

    private static final Logger log = FatuLoggerFactory.create();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.x(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.x(request, response);
    }

    protected void x(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Writer writer = response.getWriter();
        try {

            JextContext.initialize();
            JextContext.setValue(FatuPageManager.PAGE_MANAGER_KEY, JextWebUtil.getPageManager(request));
            JextContext.setValue(ChangesManager.CHANGED_PROPERTIES_KEY, new ChangesManager());
            JextContext.setValue(FatuPropertyChangeListener.PROPERTY_CHANGE_LISTENER_KEY, new WebRenderPropertyChangedListener());
            JextContext.setValue(IRenderManager.RENDER_MANAGER_KEY, MobileRenderManager.getInstance());

            IRenderManager renderManager = MobileRenderManager.getInstance();

            InternalWebAction action = getAction(request);
            synchronized(action.getPage()) {
                StringWriter sWriter = new StringWriter();
                RenderWriter out = new RenderWriter(sWriter, 0);

                ControllerManager mgr = new ControllerManager();
                Object result = mgr.executeAction(action);
                if (result != null) {
                    IRender<Object> resultRender = renderManager.getRender(result);
                    resultRender.render(result, out);
                } else {
                    ChangesManager changeManager = JextContext.getValue(ChangesManager.CHANGED_PROPERTIES_KEY);

                    JextPageRender pageRender = (JextPageRender) renderManager.getRender(FatuPage.class);
                    pageRender.update(action.getPage(), out);

                    // Render First the Children Changes
                    Collection<ComponentChange> changes = changeManager.getChanges();
                    for (ComponentChange change : changes) {
                        if (change.getPropertyChanges("children") == null) continue;
                        FatuComponent cmp = FatuUtil.getCmp(change.getComponentID(), action.getPage());
                        if (cmp == null) continue;
                        IRender<FatuComponent> render = renderManager.getRender(cmp);
                        if (render != null) {
                            render.update(cmp, out);
                        }
                        changeManager.removeChanges(change.getComponentID());
                    }

                    changes = changeManager.getChanges();
                    for (ComponentChange change : changes) {
                        FatuComponent cmp = FatuUtil.getCmp(change.getComponentID(), action.getPage());
                        if (cmp == null) continue;
                        IRender<FatuComponent> render = renderManager.getRender(cmp);
                        if (render != null) {
                            render.update(cmp, out);
                        }
                        changeManager.removeChanges(change.getComponentID());
                    }
                }
                if (log.isTraceEnabled()) log.trace(sWriter.getBuffer().toString());
                writer.write(sWriter.getBuffer().toString());
            }
        } finally {
            JextContext.release();
            writer.flush();
        }
    }

    private InternalWebAction getAction(HttpServletRequest request) {
        String pageID = request.getParameter("PAGE_ID");
        String compID = request.getParameter("COMPONENT_ID");
        String evt = request.getParameter("EVENT");
        FatuPageManager pManager = JextContext.getValue(FatuPageManager.PAGE_MANAGER_KEY);

        FatuPage<?> page = pManager.getPageByID(pageID);
        JextContext.setValue("CURRENT_PAGE", page);
        FatuComponent target = FatuUtil.getCmp(compID, page);

        InternalWebAction action = new InternalWebAction(page, target, evt);
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String pName = params.nextElement();
            action.setParam(pName, request.getParameter(pName));
        }
        return action;
    }

    private class InternalWebAction implements IJextAction<FatuComponent> {

        private final FatuPage<?> page;
        private final FatuComponent target;
        private final Map<String, Object> params;
        private final String event;

        public InternalWebAction(FatuPage<?> page, FatuComponent target, String event) {
            this.page = page;
            this.target = target;
            this.event = event;
            this.params = new HashMap<String, Object>();
        }

        @Override
        public FatuPage<?> getPage() {
            return this.page;
        }

        @Override
        public FatuComponent getTarget() {
            return this.target;
        }

        @Override
        public String getEvent() {
            return this.event;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <V> V getParameter(String parameter) {
            return (V) params.get(parameter);
        }

        private void setParam(String name, Object value) {
            this.params.put(name, value);
        }

    }

}
