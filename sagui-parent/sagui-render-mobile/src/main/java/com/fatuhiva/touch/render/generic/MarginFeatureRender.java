package com.fatuhiva.touch.render.generic;

import java.io.IOException;

import com.fatuhiva.ext.common.render.ChangesManager;
import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.feature.IFatuMarginFeature;
import com.pw.common.JextContext;

public class MarginFeatureRender<T extends FatuComponent> implements IComponentRender<T> {

    @Override
    public boolean render(T component, RenderWriter out) throws RenderException {
        boolean rendered = false;
        if (component instanceof IFatuMarginFeature) {
            IFatuMarginFeature feature = (IFatuMarginFeature) component;
            try {
                FatuMarginFeature margin = feature.getMargins();
                out.formatLn("margin : '%1$s %2$s %3$s %4$s'", margin.getTop(), margin.getRight(), margin.getBottom(), margin.getLeft());
                rendered = true;
            } catch (IOException e) {
                throw new RenderException(e);
            }
        }
        return rendered;
    }

    @Override
    public void update(T component, RenderWriter out) throws RenderException {
        if (component instanceof IFatuMarginFeature) {
            ChangesManager changeManager = JextContext.getValue(ChangesManager.CHANGED_PROPERTIES_KEY);
            if (!changeManager.hasChanges(component.getId(), "margin")) return;
            IFatuMarginFeature cmp = (IFatuMarginFeature) component;
            try {
                FatuMarginFeature margin = cmp.getMargins();
                out.append("var cmp = Ext.getCmp('").append(component.getId()).append("');").ln();
                out.append("if(cmp != null) cmp.").append("setMargin").formatLn("('%1$s %2$s %3$s %4$s');", margin.getTop(), margin.getRight(), margin.getBottom(), margin.getLeft());
            } catch (Exception e) {
                throw new RenderException(e);
            }
        }
    }
}
