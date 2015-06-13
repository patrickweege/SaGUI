package com.fatuhiva.touch.render.layout;

import java.io.IOException;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.container.FatuLayoutContainer;
import com.fatuhiva.model.layout.IFatuLayoutManager;
import com.fatuhiva.model.layout.auto.FatuAutoLayout;

@SuppressWarnings("rawtypes")
public class JextAutoLayoutPropertyRender<T extends FatuLayoutContainer> implements IComponentRender<T> {

    @Override
    public boolean render(T component, RenderWriter out) throws RenderException {
        boolean rendered = false;
        try {
            IFatuLayoutManager<?> layout = component.getLayout();
            if (layout != null && layout instanceof FatuAutoLayout) {
                out.tab().writeConfigAsString("layout", "auto").ln();
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
