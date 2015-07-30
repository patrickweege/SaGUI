package com.sagui.ext.render.layout;

import java.io.IOException;

import com.sagui.ext.common.render.IComponentRender;
import com.sagui.ext.common.render.RenderException;
import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.model.FatuComponent;
import com.sagui.model.feature.IFatuLayoutFeature;
import com.sagui.model.layout.IFatuLayoutManager;
import com.sagui.model.layout.table.FatuTableLayout;

@SuppressWarnings("rawtypes")
public class JextTableLayoutPropertyRender<T extends FatuComponent & IFatuLayoutFeature<?>> implements IComponentRender<T> {

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
