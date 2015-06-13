package com.fatuhiva.ext.render.generic;

import java.io.IOException;

import com.fatuhiva.ext.common.render.IRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.IFatuElement;
import com.fatuhiva.model.feature.FatuSize;
import com.fatuhiva.model.feature.IFatuSizeFeature;

public class JextSizeFeatureRender<T extends IFatuSizeFeature & IFatuElement> implements IRender<T> {

    @Override
    public boolean render(T component, RenderWriter out) throws RenderException {
        boolean rendered = false;
        if (component instanceof IFatuSizeFeature) {
            IFatuSizeFeature feature = (IFatuSizeFeature) component;
            try {
                FatuSize size = feature.getSize();
                if (size != null) {
                    if (size.getWidth() != FatuSize.NOT_ESPECIFIED || size.getHeight() != FatuSize.NOT_ESPECIFIED) {
                        out.tab();
                        if (size.getWidth() > FatuSize.NOT_ESPECIFIED) {
                            out.writeConfig("width", size.getWidth());
                            rendered = true;
                        }
                        if (size.getHeight() > FatuSize.NOT_ESPECIFIED) {
                            if (rendered) out.pushComma();
                            out.writeConfig("height", size.getHeight());
                            rendered = true;
                        }
                        out.ln();
                    }
                }
            } catch (IOException e) {
                throw new RenderException(e);
            }
        }
        return rendered;
    }

    @Override
    public void update(T component, RenderWriter out) throws RenderException {
        if (component instanceof IFatuSizeFeature) {
            IFatuSizeFeature support = (IFatuSizeFeature) component;
            try {
                FatuSize size = support.getSize();
                boolean hasSize = size != null;
                hasSize = hasSize && (size.getWidth() > FatuSize.NOT_ESPECIFIED || size.getHeight() > FatuSize.NOT_ESPECIFIED);
                if (hasSize) {
                    out.append("var cmp = Ext.getCmp('").append(component.getId()).append("');").ln();
                    if (size.getWidth() > FatuSize.NOT_ESPECIFIED) {
                        out.append("if(cmp != null) cmp.").append("setWidth").format("(%1$s);", size.getWidth()).ln();
                    }
                    if (size.getHeight() > FatuSize.NOT_ESPECIFIED) {
                        out.append("if(cmp != null) cmp.").append("setHeight").format("(%1$s);", size.getHeight()).ln();
                    }
                }
            } catch (Exception e) {
                throw new RenderException(e);
            }
        }
    }
}
