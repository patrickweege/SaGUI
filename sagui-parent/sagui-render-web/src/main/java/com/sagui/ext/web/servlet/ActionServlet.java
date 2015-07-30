package com.sagui.ext.web.servlet;

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

import org.apache.commons.collections.Transformer;
import org.slf4j.Logger;

import com.pw.common.JextContext;
import com.sagui.dataset.commons.log.FatuLoggerFactory;
import com.sagui.ext.common.render.ChangesManager;
import com.sagui.ext.common.render.IRender;
import com.sagui.ext.common.render.IRenderManager;
import com.sagui.ext.common.render.ChangesManager.ComponentChange;
import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.ext.controller.ControllerManager;
import com.sagui.ext.controller.IJextAction;
import com.sagui.ext.render.container.page.JextPageRender;
import com.sagui.ext.render.listener.WebRenderPropertyChangedListener;
import com.sagui.ext.render.manager.WebRenderManager;
import com.sagui.ext.web.JextWebUtil;
import com.sagui.model.DefaultPropertyChangeListener;
import com.sagui.model.FatuComponent;
import com.sagui.model.container.page.FatuPage;
import com.sagui.model.util.FatuPageManager;
import com.sagui.model.util.FatuUtil;

@SuppressWarnings("unchecked")
public class ActionServlet extends HttpServlet {

    private static final long serialVersionUID = -3801919358435506790L;

    private static final Logger log = FatuLoggerFactory.create(ActionServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doAction(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doAction(request, response);
    }

    @SuppressWarnings("rawtypes")
    private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(log.isDebugEnabled()) log.debug("Start doAction...");
        Writer writer = response.getWriter();
        try {
            JextContext.initialize();
            FatuPageManager pageManager = JextWebUtil.getPageManager(request);
            JextContext.setValue(FatuPageManager.PAGE_MANAGER_KEY, pageManager);
            JextContext.setValue(ChangesManager.CHANGED_PROPERTIES_KEY, new ChangesManager());
            JextContext.setValue(DefaultPropertyChangeListener.PROPERTY_CHANGE_LISTENER_KEY, new WebRenderPropertyChangedListener());
            JextContext.setValue(IRenderManager.RENDER_MANAGER_KEY, WebRenderManager.getInstance());

            IRenderManager renderManager = WebRenderManager.getInstance();

            IJextAction<FatuComponent> action = getAction(pageManager, request);

            FatuPage page = action.getPage();
            JextContext.setValue("CURRENT_PAGE", page);

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
                writer.write(sWriter.getBuffer().toString());
            }
        } finally {
            JextContext.release();
            writer.flush();
            if(log.isDebugEnabled()) log.debug("End doAction...");
        }
    }

    private IJextAction<FatuComponent> getAction(FatuPageManager pageManager, HttpServletRequest request) {
        InternalJSONWebAction action = new InternalJSONWebAction(pageManager, request);
        return action;
    }

    @SuppressWarnings("rawtypes")
    private class InternalJSONWebAction implements IJextAction<FatuComponent> {

        private final FatuPage page;
        private final FatuComponent target;
        private final String eventID;
        private final Map<String, Object> params;

        private InternalJSONWebAction(FatuPageManager pageManager, HttpServletRequest request) {
            String pageID = request.getParameter("PAGE_ID");
            this.page = pageManager.getPageByID(pageID);

            String componentID = request.getParameter("COMPONENT_ID");
            this.target = FatuUtil.getCmp(componentID, page);

            this.eventID = request.getParameter("EVENT");

            this.params = new HashMap<String, Object>();
            Enumeration<String> params = request.getParameterNames();
            while (params.hasMoreElements()) {
                String pName = params.nextElement();
                this.setParam(pName, request.getParameter(pName));
            }
        }

        @Override
        public FatuPage getPage() {
            return this.page;
        }

        @Override
        public FatuComponent getTarget() {
            return this.target;
        }

        @Override
        public String getEvent() {
            return this.eventID;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <V> V getParameter(String parameter) {
            V value = null;
            if (params.containsKey(parameter)) {
                value = (V) params.get(parameter);
            }
            return value;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <V> V getParameter(String name, Transformer transformer) {
            Object value = getParameter(name);
            return (V) transformer.transform(value);
        }

        private void setParam(String name, Object value) {
            this.params.put(name, value);
        }
    }

}
