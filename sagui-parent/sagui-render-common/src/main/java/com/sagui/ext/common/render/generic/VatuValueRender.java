package com.sagui.ext.common.render.generic;

import com.sagui.ext.common.render.IRender;
import com.sagui.ext.common.render.RenderException;
import com.sagui.ext.common.render.util.RenderWriter;


public abstract class VatuValueRender<T> implements IRender<T> {

    private final String extProperty;
    
    public VatuValueRender(String extProperty) {
        this.extProperty = extProperty;
    }
    
    @Override
    public final boolean render(T component, RenderWriter out) throws RenderException {
        boolean rendered = false;
        try {
            Object toRender = getValueToRender(component);
            if(toRender != null) {
                out.writeConfig(extProperty, toRender).ln();
                rendered = true;
            }
        } catch (Exception e) {
            throw new RenderException(e);
        }
        return rendered;
    }

    @Override
    public void update(T component, RenderWriter out) throws RenderException {
        // NOTHING to do here
    }
    
    protected abstract Object getValueToRender(T component) throws RenderException;
    
}
