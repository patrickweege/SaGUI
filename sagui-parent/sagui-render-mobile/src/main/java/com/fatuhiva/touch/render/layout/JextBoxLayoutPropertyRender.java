package com.fatuhiva.touch.render.layout;

import java.io.IOException;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.container.FatuLayoutContainer;
import com.fatuhiva.model.layout.IFatuLayoutManager;
import com.fatuhiva.model.layout.box.FatuHBoxLayout;
import com.fatuhiva.model.layout.box.FatuVBoxLayout;

@SuppressWarnings("rawtypes")
public class JextBoxLayoutPropertyRender<T extends FatuLayoutContainer> implements IComponentRender<T> {

    @Override
    public boolean render(T component, RenderWriter out) throws RenderException {
        boolean rendered = false;
        IFatuLayoutManager<?> layout = component.getLayout();
        if (layout != null && layout instanceof FatuHBoxLayout) {
            return render(component, out, (FatuHBoxLayout) layout);
        } else if (layout != null && layout instanceof FatuVBoxLayout) {
            return render(component, out, (FatuVBoxLayout) layout);
        }
        return rendered;
    }

    @Override
    public void update(T component, RenderWriter out) throws RenderException {
    }

    private boolean render(T component, RenderWriter out, FatuHBoxLayout vBox) {
        try {
            out.tab().append("layout : {").ln();
            out.ident();
            out.tab().writeConfigAsString("type", "hbox").ln().pushComma();
            out.tab().writeConfigAsString("align", "stretch").ln().pushComma();
            out.tab().writeConfigAsString("pack", "start").ln();
            out.udent();
            out.tab().append("}").ln();
            return true;
        } catch (IOException e) {
            throw new RenderException(e);
        }
    }

    private boolean render(T component, RenderWriter out, FatuVBoxLayout vBox) {
        try {
            out.tab().append("layout : {").ln();
            out.ident();
            out.tab().writeConfigAsString("type", "vbox").ln().pushComma();
            out.tab().writeConfigAsString("align", "stretch").ln().pushComma();
            out.tab().writeConfigAsString("pack", "start").ln();
            out.udent();
            out.tab().append("}").ln();
            return true;
        } catch (IOException e) {
            throw new RenderException(e);
        }
    }

}
