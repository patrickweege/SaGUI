package com.sagui.ext.common.render.generic;

import com.sagui.ext.common.render.IComponentRender;
import com.sagui.ext.common.render.RenderException;
import com.sagui.ext.common.render.util.RenderWriter;
import com.sagui.model.FatuComponent;


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
