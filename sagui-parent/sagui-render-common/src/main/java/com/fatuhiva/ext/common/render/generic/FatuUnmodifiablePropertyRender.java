package com.fatuhiva.ext.common.render.generic;

import com.fatuhiva.ext.common.render.IComponentRender;
import com.fatuhiva.ext.common.render.RenderException;
import com.fatuhiva.ext.common.render.util.RenderWriter;
import com.fatuhiva.model.FatuComponent;


public class FatuUnmodifiablePropertyRender<T extends FatuComponent> implements IComponentRender<T> {

    private String extProperty;
    private Object toRender;

    public FatuUnmodifiablePropertyRender(String extProperty, Object toRender) {
        this.extProperty = extProperty;
        this.toRender = toRender;
    }

    @Override
    public boolean render(T component, RenderWriter out) throws RenderException {
        try {
            out.writeConfig(extProperty, toRender).ln();
        } catch (Exception e) {
            throw new RenderException(e);
        }
        return true;
    }

    @Override
    public void update(T component, RenderWriter out) throws RenderException {
        // nothing todo here.
    }

}
