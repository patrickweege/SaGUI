package com.fatuhiva.ext.render.container.tabpanel;

import java.io.StringWriter;

import org.apache.commons.io.IOUtils;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.IRender;
import com.fatuhiva.ext.common.render.IRenderManager;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.container.panel.FatuPanel;
import com.fatuhiva.model.container.toolbar.FatuToolbar;
import com.pw.common.JextContext;

public class JextPanelToolbarPropertyRenderRender implements IComponentRender<FatuPanel<?>> {

    @Override
    public boolean render(FatuPanel<?> component, RenderWriter out) throws RenderException {
        IRenderManager renderManager = JextContext.getValue(IRenderManager.RENDER_MANAGER_KEY);
        StringWriter writer = null;
        try {
            FatuToolbar<FatuComponent> toolbar = component.getToolbar();
            if (toolbar != null) {
                writer = new StringWriter();
                RenderWriter renderWriter = new RenderWriter(writer, out.getPadding());

                IRender<FatuToolbar<FatuComponent>> tbarRender = renderManager.getRender(toolbar);
                tbarRender.render(toolbar, renderWriter);
                out.appendLn("tbar : " + writer.getBuffer().toString());
                return true;
            }
        } catch (Exception e) {
            throw new RenderException(e);
        } finally {
            IOUtils.closeQuietly(writer);
        }
        return false;
    }

    @Override
    public void update(FatuPanel<?> component, RenderWriter out) throws RenderException {
        // TODO Auto-generated method stub
    }

}
