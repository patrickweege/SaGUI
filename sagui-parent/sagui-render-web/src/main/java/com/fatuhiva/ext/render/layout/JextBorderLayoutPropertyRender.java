package com.fatuhiva.ext.render.layout;

import java.io.IOException;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.FatuComponent;
import com.fatuhiva.model.feature.IFatuLayoutFeature;
import com.fatuhiva.model.layout.IFatuLayoutManager;
import com.fatuhiva.model.layout.border.FatuBorderLayout;

public class JextBorderLayoutPropertyRender<T extends FatuComponent & IFatuLayoutFeature<?>> implements IComponentRender<T> {

    @Override
    public boolean render(T component, RenderWriter out) throws RenderException {
        boolean rendered = false;
        try {
            IFatuLayoutManager<?> layout = component.getLayout();
            if (layout != null && layout instanceof FatuBorderLayout) {
                out.tab().writeConfigAsString("layout", "border").ln();
                rendered = true;
            }
        } catch (IOException e) {
            throw new RenderException(e);
        }
        return rendered;
    }

    @Override
    public void update(T component, RenderWriter out) throws RenderException {
    }
}
