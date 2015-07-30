package com.sagui.ext.render.layout;

import java.io.IOException;

import com.sagui.ext.common.render.IComponentRender;
import com.sagui.ext.common.render.RenderException;
import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.model.FatuComponent;
import com.sagui.model.feature.IFatuLayoutFeature;
import com.sagui.model.layout.IFatuLayoutManager;
import com.sagui.model.layout.fit.FatuFitLayout;

public class JextFitLayoutPropertyRender<T extends FatuComponent & IFatuLayoutFeature<?>> implements IComponentRender<T> {

    @Override
    public boolean render(T component, RenderWriter out) throws RenderException {
        boolean rendered = false;
        try {
            IFatuLayoutManager<?> layout = component.getLayout();
            if (layout != null && layout instanceof FatuFitLayout) {
                out.tab().writeConfigAsString("layout", "fit").ln();
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
