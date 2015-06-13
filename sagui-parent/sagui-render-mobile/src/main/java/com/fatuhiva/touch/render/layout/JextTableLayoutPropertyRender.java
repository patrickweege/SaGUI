package com.fatuhiva.touch.render.layout;

import java.io.IOException;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.container.FatuLayoutContainer;
import com.fatuhiva.model.layout.IFatuLayoutManager;
import com.fatuhiva.model.layout.table.FatuTableLayout;

@SuppressWarnings("rawtypes")
public class JextTableLayoutPropertyRender<T extends FatuLayoutContainer> implements IComponentRender<T> {

    @Override
    public boolean render(T component, RenderWriter out) throws RenderException {
    	boolean rendered = false;
        try {
            IFatuLayoutManager layout = component.getLayout();
            if (layout instanceof FatuTableLayout) {
                FatuTableLayout tLayout = (FatuTableLayout) layout;
                out.tab().append("layout : {").ln();
                out.tab(1).append("type : 'table',").ln();
                out.tab(1).format("columns : %1$s", tLayout.getColumns()).ln();
                out.tab().append("}").ln();
                rendered = true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return rendered;
    }

    @Override
    public void update(T component, RenderWriter out) throws RenderException {
    }

}
