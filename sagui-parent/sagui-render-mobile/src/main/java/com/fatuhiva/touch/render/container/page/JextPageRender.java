package com.fatuhiva.touch.render.container.page;

import java.io.IOException;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.generic.FatuExtFixedValuePropertyRender;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.container.page.FatuPage;
import com.fatuhiva.touch.render.container.JextChildrenRender;
import com.fatuhiva.touch.render.generic.CompositeRender;
import com.fatuhiva.touch.render.generic.JextIDRender;

@SuppressWarnings("unchecked")
public class JextPageRender implements IComponentRender<FatuPage> {

    private final CompositeRender<FatuPage> composite;

    public JextPageRender() {
        this.composite = new CompositeRender<FatuPage>();
        composite.addRender(new JextIDRender<FatuPage>());
        composite.addRender(new FatuExtFixedValuePropertyRender<FatuPage>("fullscreen", Boolean.TRUE));
        composite.addRender(new FatuExtFixedValuePropertyRender<FatuPage>("layout", "fit"));
        composite.addRender(new JextChildrenRender<FatuPage>());
    }

    @Override
    public boolean render(FatuPage component, RenderWriter out) throws RenderException {
        try {
            out.appendLn("Ext.application({");
            {
                out.ident();
                out.writeConfigAsString("name", "Page_" + component.getId()).pushComma().ln();
                out.appendLn("launch: function() {");
                {
                    out.ident();
                    out.appendLn("Ext.create('Ext.form.Panel', {");
                    {
                        out.ident();
                        composite.render(component, out);
                        out.udent();
                    }
                    out.udent();
                    out.appendLn("});");
                }
                out.udent();
                out.append("}");
            }
            out.append("});");
        } catch (IOException e) {
            throw new RenderException(e);
        }
        return true;
    }

    @Override
    public void update(FatuPage component, RenderWriter out) throws RenderException {
        // TODO Auto-generated method stub

    }

}
