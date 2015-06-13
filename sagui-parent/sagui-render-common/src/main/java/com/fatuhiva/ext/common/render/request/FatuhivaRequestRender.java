package com.fatuhiva.ext.common.render.request;

import java.util.Map.Entry;

import com.fatuhiva.ext.common.render.IRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;

public class FatuhivaRequestRender implements IRender<FatuhivaRequest> {

    @Override
    public boolean render(FatuhivaRequest component, RenderWriter out) throws RenderException {
        try {
            out.ident();
            out.append("var parameters = {").ln();
            out.ident();
            for (Entry<String, Object> entry : component.getParameters().entrySet()) {
                out.writeConfig(entry.getKey(), entry.getValue()).commaLn();
            }
            out.udent().popComma();
            out.append("};").ln();
            out.format("Fatuhiva.executeAction(parameters);").ln();
            out.udent();
            return true;
        } catch (Exception e) {
            throw new RenderException(e);
        }
    }

    @Override
    public void update(FatuhivaRequest component, RenderWriter out) throws RenderException {

    }
}
