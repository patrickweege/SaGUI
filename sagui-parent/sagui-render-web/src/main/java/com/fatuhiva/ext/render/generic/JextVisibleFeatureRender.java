package com.fatuhiva.ext.render.generic;

import java.io.IOException;

import com.fatuhiva.ext.common.render.ChangesManager;
import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.feature.IFatuEnabledFeature;
import com.fatuhiva.model.feature.IFatuVisibleFeature;
import com.pw.common.JextContext;

public class JextVisibleFeatureRender<T extends FatuComponent> implements IComponentRender<T> {

    @Override
    public boolean render(T component, RenderWriter out) throws RenderException {
        boolean rendered = false;
        if (component instanceof IFatuVisibleFeature) {
            IFatuVisibleFeature feature = (IFatuVisibleFeature) component;
            try {
                out.writeConfig("hidden", !feature.isVisible()).ln();
                rendered = true;
            } catch (IOException e) {
                throw new RenderException(e);
            }
        }
        return rendered;
    }

    @Override
    public void update(T component, RenderWriter out) throws RenderException {
        FatuComponent cmp = (FatuComponent) component;
        ChangesManager changeManager = JextContext.getValue(ChangesManager.CHANGED_PROPERTIES_KEY);
        if(!changeManager.hasChanges(cmp.getId(), "visible")) return;
        if (component instanceof IFatuEnabledFeature) {
            IFatuVisibleFeature feature = (IFatuVisibleFeature) component;
            try {
                out.append("var cmp = Ext.getCmp('").append(cmp.getId()).append("');").ln();
                out.append("if(cmp != null) cmp.").append("setVisible").format("(%1$s);", feature.isVisible()).ln();
            } catch (Exception e) {
                throw new RenderException(e);
            }
        }
    }
}
