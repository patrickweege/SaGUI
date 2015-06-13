package com.fatuhiva.touch.render.generic;

import java.io.IOException;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.feature.IFatuSizeFeature;

public class JextSizeFeatureRender<T extends FatuComponent> implements IComponentRender<T> {

    @Override
    public boolean render(T component, RenderWriter out) throws RenderException {
        boolean rendered = false;
        if (component instanceof IFatuSizeFeature) {
            IFatuSizeFeature feature = (IFatuSizeFeature) component;
            try {
                FatuSizeFeature size = feature.getSize();
                if(size != null) {
                    if(size.getWidth() != null || size.getHeight() != null) {
                        out.tab();
                        if (size.getWidth() != null) {
                            out.writeConfig("width", Integer.toString(size.getWidth()));
                            rendered = true;
                        }
                        if (size.getHeight() != null) {
                            if (rendered) out.pushComma();
                            out.writeConfig("height", Integer.toString(size.getHeight()));
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
                FatuSizeFeature feature = support.getSize();
                out.append("var cmp = Ext.getCmp('").append(component.getId()).append("');").ln();
                out.append("if(cmp != null) cmp.").append("setWidth").format("(%1$s);", feature.getWidth()).ln();
                out.append("if(cmp != null) cmp.").append("setHeight").format("(%1$s);", feature.getHeight()).ln();
            } catch (Exception e) {
                throw new RenderException(e);
            }
        }
    }
}
