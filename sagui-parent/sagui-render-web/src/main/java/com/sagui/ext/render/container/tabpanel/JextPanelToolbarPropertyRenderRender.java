package com.sagui.ext.render.container.tabpanel;

import java.io.StringWriter;

import org.apache.commons.io.IOUtils;

import com.pw.common.JextContext;
import com.sagui.ext.common.render.IComponentRender;
import com.sagui.ext.common.render.IRender;
import com.sagui.ext.common.render.IRenderManager;
import com.sagui.ext.common.render.RenderException;
import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.model.FatuComponent;
import com.sagui.model.container.panel.FatuPanel;
import com.sagui.model.container.toolbar.FatuToolbar;

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
