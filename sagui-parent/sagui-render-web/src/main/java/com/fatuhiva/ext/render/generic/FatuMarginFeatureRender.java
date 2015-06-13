package com.fatuhiva.ext.render.generic;

import java.awt.Insets;
import java.io.IOException;

import com.fatuhiva.ext.common.render.ChangesManager;
import com.fatuhiva.ext.common.render.IRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.IFatuElement;
import com.fatuhiva.model.feature.IFatuMarginFeature;
import com.pw.common.JextContext;

public class FatuMarginFeatureRender<T extends IFatuMarginFeature & IFatuElement> implements IRender<T> {

    @Override
    public boolean render(T component, RenderWriter out) throws RenderException {
        boolean rendered = false;
        if (component instanceof IFatuMarginFeature) {
            try {
                Insets margins = component.getMargins();
                if (margins != null) {
                    out.formatLn("margin : '%1$s %2$s %3$s %4$s'", margins.top, margins.right, margins.bottom, margins.left);
                    rendered = true;
                }
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
            try {
                Insets margins = component.getMargins();
                if (margins != null) {
                    out.append("var cmp = Ext.getCmp('").append(component.getId()).append("');").ln();
                    out.append("if(cmp != null) cmp.").append("setMargin").formatLn("('%1$s %2$s %3$s %4$s');", margins.top, margins.right, margins.bottom, margins.left);
                }
            } catch (Exception e) {
                throw new RenderException(e);
            }
        }
    }
}
